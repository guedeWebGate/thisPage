package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.AbstractStorageService;

public class CategoryStorageService extends AbstractStorageService<Category> {

	@Override
	protected Category createObject() {
		return new Category();
	}

}
