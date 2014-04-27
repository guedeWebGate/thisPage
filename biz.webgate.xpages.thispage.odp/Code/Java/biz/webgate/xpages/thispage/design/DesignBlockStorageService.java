package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class DesignBlockStorageService extends AbstractContentStorageService<DesignBlock> {

	private static final DesignBlockStorageService m_Service = new DesignBlockStorageService();

	public static DesignBlockStorageService getInstance() {
		return m_Service;
	}

	private DesignBlockStorageService() {
	}

	@Override
	public DesignBlock createObject() {
		return new DesignBlock();
	}
}
