package biz.webgate.xpages.thispage.content;

import java.util.List;

import biz.webgate.xpages.thispage.AbstractContentStorageService;
import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.api.ContentSessionFacade;

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
			return StringUtil.isEmpty(strCatPath) ? strKey : strCatPath;
		}
		sb.append(cat.getTitle());
		sb.append("/");
		sb.append(strCatPath);
		if (!StringUtil.isEmpty(cat.getParentCategory()) && !"<noparent>".equals(cat.getParentCategory())) {
			return _pBuildCategoryPath(cat.getParentCategory(), sb.toString());
		}
		return sb.toString();
	}

	public List<Category> getCategoryTree() {
		List<Category> lstRC = getObjectsByForeignId("<noparent>", ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
		for (Category cat : lstRC) {
			checkSubCategories(cat);
		}
		return lstRC;
	}

	private void checkSubCategories(Category cat) {
		List<Category> lstSub = getObjectsByForeignId(cat.getDocKey(), ContentSessionFacade.LUP_ALL_EDITABLE_BY_FORM);
		if (lstSub != null && lstSub.size() > 0) {
			cat.setSubCategories(lstSub);
			for (Category catSub : lstSub) {
				checkSubCategories(catSub);
			}
		}
	}
}
