package biz.webgate.xpages.thispage.content;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.xpages.thispage.DocStatus;

import com.ibm.commons.util.StringUtil;

public class CategoryStorageService extends AbstractStorageService<Category> {

	private static CategoryStorageService m_Service = new CategoryStorageService();

	@Override
	protected Category createObject() {
		return new Category();
	}

	public static CategoryStorageService getInstance() {
		return m_Service;
	}

	public Category getByDocKey(String strDocKey, DocStatus[] status) {
		Category catCurrent = null;
		List<Category> lstCat = getObjectsByForeignId(strDocKey, "lupAllEditableByDocKey");
		if (lstCat == null || lstCat.size() == 0) {
			return null;
		}
		if (status != null) {
			List<DocStatus> ds = Arrays.asList(status);
			for (Iterator<Category> itCat = lstCat.iterator(); itCat.hasNext();) {
				Category catCheck = itCat.next();
				if (!ds.contains(catCheck.getStatus())) {
					itCat.remove();
				}
			}
			if (lstCat.size() > 0) {
				catCurrent = lstCat.get(0);
			}
		}
		return catCurrent;
	}

	public String buildCategoryPath(String strKey) {
		return _pBuildCategoryPath(strKey, "");
	}

	private String _pBuildCategoryPath(String strKey, String strCatPath) {
		StringBuilder sb = new StringBuilder();
		Category cat = getByDocKey(strKey, null);
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
