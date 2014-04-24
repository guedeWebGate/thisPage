package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class PageLayoutStorageService extends AbstractContentStorageService<PageLayout> {

	private static final PageLayoutStorageService m_Service = new PageLayoutStorageService();

	public static PageLayoutStorageService getInstance() {
		return m_Service;
	}

	@Override
	public PageLayout createObject() {
		return new PageLayout();
	}

}
