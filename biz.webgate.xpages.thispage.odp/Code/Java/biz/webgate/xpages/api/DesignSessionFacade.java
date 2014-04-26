package biz.webgate.xpages.api;

import java.util.List;

import javax.faces.context.FacesContext;

import org.openntf.xpt.core.dss.SingleObjectStore;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.design.DesignContent;
import biz.webgate.xpages.thispage.design.DesignContentStorageService;
import biz.webgate.xpages.thispage.design.PageLayout;
import biz.webgate.xpages.thispage.design.PageLayoutStorageService;
import biz.webgate.xpages.thispage.services.UserNameProvider;

public class DesignSessionFacade {
	private static final String BEAN_NAME = "designBean";

	public static DesignSessionFacade get(FacesContext context) {
		DesignSessionFacade bean = (DesignSessionFacade) context.getApplication().getVariableResolver().resolveVariable(context, BEAN_NAME);
		return bean;
	}

	public static DesignSessionFacade get() {
		return get(FacesContext.getCurrentInstance());
	}

	public List<PageLayout> allPageLayout4Edit() {
		return PageLayoutStorageService.getInstance().getObjectsByForeignId("frmPageLayout", ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
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
		return DesignContentStorageService.getInstance().getObjectsByForeignId("frmDesignContent",
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
}
