package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class DesignPictureStorageService extends AbstractContentStorageService<DesignPicture> {

	private static final DesignPictureStorageService m_Service = new DesignPictureStorageService();

	public static DesignPictureStorageService getInstance() {
		return m_Service;
	}

	private DesignPictureStorageService() {
	}

	@Override
	public DesignPicture createObject() {
		return new DesignPicture();
	}

}
