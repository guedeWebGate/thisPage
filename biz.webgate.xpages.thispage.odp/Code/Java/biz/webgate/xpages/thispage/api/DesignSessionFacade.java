package biz.webgate.xpages.thispage.api;

import java.util.List;

import javax.faces.context.FacesContext;

import org.openntf.xpt.core.dss.SingleObjectStore;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.design.DesignBlockStorageService;
import biz.webgate.xpages.thispage.design.DesignContent;
import biz.webgate.xpages.thispage.design.DesignContentStorageService;
import biz.webgate.xpages.thispage.design.DesignFile;
import biz.webgate.xpages.thispage.design.DesignFileStorageService;
import biz.webgate.xpages.thispage.design.DesignPicture;
import biz.webgate.xpages.thispage.design.DesignPictureStorageService;
import biz.webgate.xpages.thispage.design.PageLayout;
import biz.webgate.xpages.thispage.design.PageLayoutStorageService;
import biz.webgate.xpages.thispage.services.UserNameProvider;

public class DesignSessionFacade {
	private static final String FRM_PAGE_LAYOUT = "frmPageLayout";
	private static final String FRM_DESIGN_CONTENT = "frmDesignContent";
	private static final String FRM_DESIGN_BLOCK = "frmDesignBlock";
	private static final String BEAN_NAME = "designBean";

	public static DesignSessionFacade get(FacesContext context) {
		DesignSessionFacade bean = (DesignSessionFacade) context.getApplication().getVariableResolver().resolveVariable(context, BEAN_NAME);
		return bean;
	}

	public static DesignSessionFacade get() {
		return get(FacesContext.getCurrentInstance());
	}

	public List<PageLayout> allPageLayout4Edit() {
		return PageLayoutStorageService.getInstance().getObjectsByForeignId(FRM_PAGE_LAYOUT, ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
	}

	public String newPageLayout(String name) {
		PageLayout pl = PageLayoutStorageService.getInstance().createObject();
		pl.setTitle(name);
		PageLayoutStorageService.getInstance().save(pl);
		return pl.getID();
	}

	public String editPageLayout(PageLayout pagelayout) {
		if (pagelayout.getStatus().equals(DocStatus.DRAFT)) {
			return pagelayout.getID();
		}
		PageLayout pagelayoutDraft = PageLayoutStorageService.getInstance().getByDocKey(pagelayout.getDocKey(), DocStatus.DRAFT);
		if (pagelayoutDraft != null) {
			return pagelayoutDraft.getID();
		}
		pagelayoutDraft = (PageLayout) pagelayout.newVersion(UserNameProvider.INSTANCE.getUserName());
		PageLayoutStorageService.getInstance().save(pagelayoutDraft);
		return pagelayoutDraft.getID();
	}

	public void setPageLayoutOffline(PageLayout pagelayout) {
		pagelayout.setStatus(DocStatus.OFFLINE);
		PageLayoutStorageService.getInstance().save(pagelayout);
	}

	public void publishPageLayout(PageLayout pagelayout) {
		PageLayoutStorageService.getInstance().publish(pagelayout);
	}

	public SingleObjectStore<PageLayout> getPageLayoutSOS() {
		return new SingleObjectStore<PageLayout>(PageLayoutStorageService.getInstance());
	}

	// DESIGN CONTENT
	public List<DesignContent> allDesignContent4Edit() {
		return DesignContentStorageService.getInstance().getObjectsByForeignId(FRM_DESIGN_CONTENT,
				ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
	}

	public String newDesignContent(String name, String type) {
		DesignContent dc = DesignContentStorageService.getInstance().createObject();
		dc.setTitle(name);
		dc.setType(type);
		DesignContentStorageService.getInstance().save(dc);
		return dc.getID();
	}

	public String editDesignContent(DesignContent designContent) {
		if (designContent.getStatus().equals(DocStatus.DRAFT)) {
			return designContent.getID();
		}
		DesignContent designContentDraft = DesignContentStorageService.getInstance()
				.getByDocKey(designContent.getDocKey(), DocStatus.DRAFT);
		if (designContentDraft != null) {
			return designContentDraft.getID();
		}
		designContentDraft = (DesignContent) designContent.newVersion(UserNameProvider.INSTANCE.getUserName());
		DesignContentStorageService.getInstance().save(designContentDraft);
		return designContentDraft.getID();
	}

	public void setDesignContentOffline(DesignContent designContent) {
		designContent.setStatus(DocStatus.OFFLINE);
		DesignContentStorageService.getInstance().save(designContent);
	}

	public void publishDesignContent(DesignContent designContent) {
		DesignContentStorageService.getInstance().publish(designContent);
	}

	public SingleObjectStore<DesignContent> getDesignContentSOS() {
		return new SingleObjectStore<DesignContent>(DesignContentStorageService.getInstance());
	}

	// DESIGN Block
	public List<DesignBlock> allDesignBlock4Edit() {
		return DesignBlockStorageService.getInstance().getObjectsByForeignId(FRM_DESIGN_BLOCK,
				ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
	}

	public String newDesignBlock(String name, String strategieName) {
		DesignBlock db = DesignBlockStorageService.getInstance().createObject();
		db.setTitle(name);
		db.setStrategieName(strategieName);
		DesignBlockStorageService.getInstance().save(db);
		return db.getID();
	}

	public String editDesignBlock(DesignBlock designBlock) {
		if (designBlock.getStatus().equals(DocStatus.DRAFT)) {
			return designBlock.getID();
		}
		DesignBlock designBlockDraft = DesignBlockStorageService.getInstance().getByDocKey(designBlock.getDocKey(), DocStatus.DRAFT);
		if (designBlockDraft != null) {
			return designBlockDraft.getID();
		}
		designBlockDraft = (DesignBlock) designBlock.newVersion(UserNameProvider.INSTANCE.getUserName());
		DesignBlockStorageService.getInstance().save(designBlockDraft);
		return designBlockDraft.getID();
	}

	public void setDesignBlockOffline(DesignBlock designBlock) {
		designBlock.setStatus(DocStatus.OFFLINE);
		DesignBlockStorageService.getInstance().save(designBlock);
	}

	public void publishDesignBlock(DesignBlock designBlock) {
		DesignBlockStorageService.getInstance().publish(designBlock);
	}

	public SingleObjectStore<DesignBlock> getDesignBlockSOS() {
		return new SingleObjectStore<DesignBlock>(DesignBlockStorageService.getInstance());
	}
	
	//DESIGN PICTURE
	public List<DesignPicture> allDesignPictures4Edit() {
		return DesignPictureStorageService.getInstance().getObjectsByForeignId("frmDesignPicture", ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
	}

	public DesignPicture createDesignPicture() {
		return DesignPictureStorageService.getInstance().createObject();
	}

	public DesignPicture getNewDesignPictureVersion(String strID) {
		DesignPicture pic = DesignPictureStorageService.getInstance().getById(strID);
		return (DesignPicture) pic.newVersion(UserNameProvider.INSTANCE.getUserName());
	}

	public void processDesignPicture(DesignPicture pic) {
		if (pic.getUploadFile() != null) {
			String strFile = pic.getUploadFile().getFilename();
			pic.setTitle(strFile);
			int nPos = strFile.lastIndexOf(".");
			if (nPos > -1) {
				pic.setType(strFile.substring(nPos + 1));
			}
		}
		DesignPictureStorageService.getInstance().save(pic);
	}

	public void deleteDesignPicture(DesignPicture pic) {
		try {
			DesignPictureStorageService.getInstance().hardDelete(pic, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publishDesignPicture(DesignPicture pic) {
		DesignPictureStorageService.getInstance().publish(pic);
	}

	public void setDesignPictureOffline(DesignPicture picture) {
		picture.setStatus(DocStatus.OFFLINE);
		DesignPictureStorageService.getInstance().save(picture);
	}
	
	
	//DESIGN FILE
	public List<DesignFile> allDesignFile4Edit() {
		return DesignFileStorageService.getInstance().getObjectsByForeignId("frmDesignFile", ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
	}

	public DesignFile createDesignFile() {
		return DesignFileStorageService.getInstance().createObject();
	}

	public DesignFile getNewDesignFileVersion(String strID) {
		DesignFile file = DesignFileStorageService.getInstance().getById(strID);
		return (DesignFile) file.newVersion(UserNameProvider.INSTANCE.getUserName());
	}

	public void processDesignFile(DesignFile file) {
		if (file.getUploadFile() != null) {
			String strFile = file.getUploadFile().getFilename();
			file.setTitle(strFile);
			int nPos = strFile.lastIndexOf(".");
			if (nPos > -1) {
				file.setType(strFile.substring(nPos + 1));
			}
		}
		DesignFileStorageService.getInstance().save(file);
	}

	public void deleteDesignFile(DesignFile file) {
		try {
			DesignFileStorageService.getInstance().hardDelete(file, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publishDesignFile(DesignFile file) {
		DesignFileStorageService.getInstance().publish(file);
	}

	public void setDesignFileOffline(DesignFile file) {
		file.setStatus(DocStatus.OFFLINE);
		DesignFileStorageService.getInstance().save(file);
	}
}
