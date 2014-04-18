package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class DesignContentStorageService extends AbstractStorageService<DesignContent> {

	@Override
	protected DesignContent createObject() {
		return new DesignContent();
	}

}
