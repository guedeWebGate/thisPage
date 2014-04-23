package biz.webgate.xpages.thispage.content;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class PictureStorageService extends AbstractContentStorageService<Picture> {
	private static final PictureStorageService m_Service = new PictureStorageService();

	public static PictureStorageService getInstance() {
		return m_Service;
	}

	@Override
	protected Picture createObject() {
		return new Picture();
	}

}
