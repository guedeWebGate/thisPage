package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class DesignContentStorageService extends AbstractContentStorageService<DesignContent> {
	private static DesignContentStorageService m_Service = new DesignContentStorageService();

	public static DesignContentStorageService getInstance() {
		return m_Service;
	}

	@Override
	public DesignContent createObject() {
		return new DesignContent();
	}

}
