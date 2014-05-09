package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class DesignFileStorageService extends AbstractContentStorageService<DesignFile> {

	private static DesignFileStorageService m_Service = new DesignFileStorageService();

	private DesignFileStorageService() {

	}

	@Override
	public DesignFile createObject() {
		return new DesignFile();
	}

	public static DesignFileStorageService getInstance() {
		return m_Service;
	}

}
