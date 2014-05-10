package biz.webgate.xpages.thispage.api;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.openntf.xpt.core.dss.SingleObjectStore;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.Category;
import biz.webgate.xpages.thispage.content.CategoryStorageService;
import biz.webgate.xpages.thispage.content.File;
import biz.webgate.xpages.thispage.content.FileStorageService;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.content.Picture;
import biz.webgate.xpages.thispage.content.PictureStorageService;
import biz.webgate.xpages.thispage.services.UserNameProvider;

public class ContentSessionFacade {
	private static final String FRM_PICTURE = "frmPicture";
	private static final String FRM_CATEGORY = "frmCategory";
	private static final String FRM_PAGE = "frmPage";
	public static final String LUP_ALL_EDITABLE_BY_FORM = "lupAllEditableByForm";
	private static final String BEAN_NAME = "contentBean";

	public static ContentSessionFacade get(FacesContext context) {
		ContentSessionFacade bean = (ContentSessionFacade) context.getApplication().getVariableResolver().resolveVariable(context,
				BEAN_NAME);
		return bean;
	}

	public static ContentSessionFacade get() {
		return get(FacesContext.getCurrentInstance());
	}

	public List<Page> allPages4Edit() {
		return PageStorageService.getInstance().getObjectsByForeignId(FRM_PAGE, LUP_ALL_EDITABLE_BY_FORM);
	}

	public Page createPage() {
		return PageStorageService.getInstance().createObject();
	}

	public String savePage(Page page) {
		PageStorageService.getInstance().save(page);
		return page.getID();
	}

	public String editPage(Page page) {
		if (page.getStatus().equals(DocStatus.DRAFT)) {
			return page.getID();
		}
		Page pageDraft = PageStorageService.getInstance().getByDocKey(page.getDocKey(), DocStatus.DRAFT);
		if (pageDraft != null) {
			return pageDraft.getID();
		}
		pageDraft = (Page) page.newVersion(UserNameProvider.INSTANCE.getUserName());
		PageStorageService.getInstance().save(pageDraft);
		return pageDraft.getID();
	}

	public void setPageOffline(Page page) {
		page.setStatus(DocStatus.OFFLINE);
		PageStorageService.getInstance().save(page);
	}

	public SingleObjectStore<Page> getPageSOS() {
		return new SingleObjectStore<Page>(PageStorageService.getInstance());
	}

	public void publishPage(Page page) {
		PageStorageService.getInstance().publish(page);
	}

	public List<String> getCategoryKeyList() {
		List<Category> lstCat = CategoryStorageService.getInstance().getObjectsByForeignId(FRM_CATEGORY, LUP_ALL_EDITABLE_BY_FORM);
		List<String> lstRC = new ArrayList<String>(lstCat.size());
		for (Category cat : lstCat) {
			lstRC.add(CategoryStorageService.getInstance().buildCategoryPath(cat.getDocKey()) + "|" + cat.getDocKey());
		}
		return lstRC;
	}

	public String addCategory(String strCatName, String newParentCat) {
		Category catNew = new Category();
		// TODO: dEFAULT DESIGN!
		if (!"<noparent>".equals(newParentCat)) {
			catNew.setParentCateogry(newParentCat);
		}
		catNew.setTitle(strCatName);
		CategoryStorageService.getInstance().save(catNew);
		return catNew.getDocKey();
	}

	// PICTURES
	public List<Picture> allPictures4Edit() {
		return PictureStorageService.getInstance().getObjectsByForeignId(FRM_PICTURE, LUP_ALL_EDITABLE_BY_FORM);
	}

	public Picture createPicture() {
		return PictureStorageService.getInstance().createObject();
	}

	public Picture getNewPictureVersion(String strID) {
		Picture pic = PictureStorageService.getInstance().getById(strID);
		return (Picture) pic.newVersion(UserNameProvider.INSTANCE.getUserName());
	}

	public void processPicture(Picture pic) {
		if (pic.getUploadFile() != null) {
			String strFile = pic.getUploadFile().getFilename();
			pic.setTitle(strFile);
			int nPos = strFile.lastIndexOf(".");
			if (nPos > -1) {
				pic.setType(strFile.substring(nPos + 1));
			}
		}
		PictureStorageService.getInstance().save(pic);
	}

	public void deletePicture(Picture pic) {
		try {
			PictureStorageService.getInstance().hardDelete(pic, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publishPicture(Picture pic) {
		PictureStorageService.getInstance().publish(pic);
	}

	public void setPictureOffline(Picture picture) {
		picture.setStatus(DocStatus.OFFLINE);
		PictureStorageService.getInstance().save(picture);
	}

	// FILES
	public List<File> allFiles4Edit() {
		return FileStorageService.getInstance().getObjectsByForeignId("frmFile", LUP_ALL_EDITABLE_BY_FORM);
	}

	public File createFile() {
		return FileStorageService.getInstance().createObject();
	}

	public File getNewFileVersion(String strID) {
		File file = FileStorageService.getInstance().getById(strID);
		return (File) file.newVersion(UserNameProvider.INSTANCE.getUserName());
	}

	public void processFile(Picture file) {
		if (file.getUploadFile() != null) {
			String strFile = file.getUploadFile().getFilename();
			file.setTitle(strFile);
			int nPos = strFile.lastIndexOf(".");
			if (nPos > -1) {
				file.setType(strFile.substring(nPos + 1));
			}
		}
		PictureStorageService.getInstance().save(file);
	}

	public void deleteFile(File file) {
		try {
			FileStorageService.getInstance().hardDelete(file, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publishFile(File file) {
		FileStorageService.getInstance().publish(file);
	}

	public void setFileOffline(File file) {
		file.setStatus(DocStatus.OFFLINE);
		FileStorageService.getInstance().save(file);
	}

	public Category getCategoryByDocKey(String categoryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getSubCategoryByParent(String parentCateogry) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getCategoryTree() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Page> getPagesByCategory(String categoryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page getPageByDocKey(String strDocKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
