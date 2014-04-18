package biz.webgate.xpages.api;

import javax.faces.context.FacesContext;

public class DesignSessionFacade {
	private static final String BEAN_NAME = "designBean";

	public static DesignSessionFacade get(FacesContext context) {
		DesignSessionFacade bean = (DesignSessionFacade) context.getApplication().getVariableResolver().resolveVariable(context,
				BEAN_NAME);
		return bean;
	}

	public static DesignSessionFacade get() {
		return get(FacesContext.getCurrentInstance());
	}

}
