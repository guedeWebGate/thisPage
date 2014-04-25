package biz.webgate.xpages.api;

import java.util.List;

import javax.faces.context.FacesContext;

import org.openntf.xpt.core.dss.SingleObjectStore;

import biz.webgate.xpages.thispage.design.PageLayout;
import biz.webgate.xpages.thispage.design.PageLayoutStorageService;

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

	public SingleObjectStore<PageLayout> getPageLayoutSOS() {
		return new SingleObjectStore<PageLayout>(PageLayoutStorageService.getInstance());
	}
}
