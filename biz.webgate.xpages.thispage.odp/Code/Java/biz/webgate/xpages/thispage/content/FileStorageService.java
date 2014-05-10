package biz.webgate.xpages.thispage.content;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class FileStorageService extends AbstractContentStorageService<File> {

	private final static FileStorageService m_Service = new FileStorageService();

	private FileStorageService() {

	}

	@Override
	public File createObject() {
		return new File();
	}

	public static FileStorageService getInstance() {
		return m_Service;
	}

}
