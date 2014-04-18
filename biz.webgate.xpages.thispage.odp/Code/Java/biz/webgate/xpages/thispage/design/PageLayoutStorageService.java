package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class PageLayoutStorageService extends AbstractStorageService<PageLayout> {

	@Override
	protected PageLayout createObject() {
		return new PageLayout();
	}

}
