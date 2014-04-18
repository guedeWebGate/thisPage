package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class DesignPicureStorageService extends AbstractStorageService<DesignPicture> {

	@Override
	protected DesignPicture createObject() {
		return new DesignPicture();
	}

}
