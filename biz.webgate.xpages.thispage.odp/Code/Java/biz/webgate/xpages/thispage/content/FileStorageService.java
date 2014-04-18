package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class FileStorageService extends AbstractStorageService<File> {

	@Override
	protected File createObject() {
		return new File();
	}

}
