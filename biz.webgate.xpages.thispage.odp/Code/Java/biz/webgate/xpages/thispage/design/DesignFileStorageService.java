package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class DesignFileStorageService extends AbstractStorageService<DesignFile> {

	@Override
	protected DesignFile createObject() {
		return new DesignFile();
	}

}
