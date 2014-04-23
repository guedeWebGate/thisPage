package biz.webgate.xpages.thispage.content;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class PageStorageService extends AbstractContentStorageService<Page> {

	private static final PageStorageService m_Service = new PageStorageService();

	public static PageStorageService getInstance() {
		return m_Service;
	}

	private PageStorageService() {

	}

	@Override
	protected Page createObject() {
		return new Page();
	}

}
