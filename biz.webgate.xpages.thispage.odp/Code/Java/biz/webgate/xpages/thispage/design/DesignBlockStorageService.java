package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class DesignBlockStorageService extends AbstractStorageService<DesignBlock> {

	@Override
	protected DesignBlock createObject() {
		return new DesignBlock();
	}

}
