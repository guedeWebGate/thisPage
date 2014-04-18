package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class PictureStorageService extends AbstractStorageService<Picture> {

	@Override
	protected Picture createObject() {
		return new Picture();
	}

}
