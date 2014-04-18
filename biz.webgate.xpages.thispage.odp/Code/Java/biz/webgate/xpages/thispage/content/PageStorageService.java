package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class PageStorageService extends AbstractStorageService<Page> {

	@Override
	protected Page createObject() {
		return new Page();
	}

}
