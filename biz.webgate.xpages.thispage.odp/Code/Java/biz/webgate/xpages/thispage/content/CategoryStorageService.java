package biz.webgate.xpages.thispage.content;

import biz.webgate.xpages.thispage.AbstractContentStorageService;
import biz.webgate.xpages.thispage.DocStatus;

import com.ibm.commons.util.StringUtil;

public class CategoryStorageService extends AbstractContentStorageService<Category> {

	private static CategoryStorageService m_Service = new CategoryStorageService();

	@Override
	public Category createObject() {
		return new Category();
	}

	public static CategoryStorageService getInstance() {
		return m_Service;
	}

	public String buildCategoryPath(String strKey) {
		return _pBuildCategoryPath(strKey, "");
	}

	private String _pBuildCategoryPath(String strKey, String strCatPath) {
		StringBuilder sb = new StringBuilder();
		Category cat = getByDocKey(strKey, DocStatus.PUBLISHED, DocStatus.DRAFT, DocStatus.OFFLINE);
		if (cat == null) {
			return strCatPath;
		}
		sb.append(cat.getTitle());
		sb.append("/");
		sb.append(strCatPath);
		if (!StringUtil.isEmpty(cat.getParentCateogry())) {
			return _pBuildCategoryPath(cat.getParentCateogry(), sb.toString());
		}
		return sb.toString();
	}
}
