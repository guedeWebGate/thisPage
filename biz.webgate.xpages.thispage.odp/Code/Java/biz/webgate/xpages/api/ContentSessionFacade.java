package biz.webgate.xpages.api;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.openntf.xpt.core.dss.SingleObjectStore;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.Category;
import biz.webgate.xpages.thispage.content.CategoryStorageService;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.services.UserNameProvider;

public class ContentSessionFacade {
	private static final String FRM_CATEGORY = "frmCategory";
	private static final String FRM_PAGE = "frmPage";
	private static final String LUP_ALL_EDITABLE_BY_FORM = "lupAllEditableByForm";
	private static final String BEAN_NAME = "contentBean";

	public static ContentSessionFacade get(FacesContext context) {
		ContentSessionFacade bean = (ContentSessionFacade) context.getApplication().getVariableResolver().resolveVariable(context,
				BEAN_NAME);
		return bean;
	}

	public static ContentSessionFacade get() {
		return get(FacesContext.getCurrentInstance());
	}

	public List<Page> getAllPages4Edit() {
		return PageStorageService.getInstance().getObjectsByForeignId(FRM_PAGE, LUP_ALL_EDITABLE_BY_FORM);
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

	public List<String> getCategoryList() {
		List<Category> lstCat = CategoryStorageService.getInstance().getObjectsByForeignId(FRM_CATEGORY, LUP_ALL_EDITABLE_BY_FORM);
		List<String> lstRC = new ArrayList<String>(lstCat.size());
		for (Category cat : lstCat) {
			lstRC.add(CategoryStorageService.getInstance().buildCategoryPath(cat.getDocKey()) + "|" + cat.getDocKey());
		}
		return lstRC;
	}
}
