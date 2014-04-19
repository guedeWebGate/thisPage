package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class CategoryStorageService extends AbstractStorageService<Category> {

	private static CategoryStorageService m_Service = new CategoryStorageService();

	@Override
	protected Category createObject() {
		return new Category();
	}

	public static CategoryStorageService getInstance() {
		return m_Service;
	}

	public String buildCategoryPath(String strKey) {
		return strKey;
	}
}
