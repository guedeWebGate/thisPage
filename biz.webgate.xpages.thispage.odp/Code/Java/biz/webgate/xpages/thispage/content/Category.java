package biz.webgate.xpages.thispage.content;

import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.rest.IElements;

@JSONObject
@DominoStore(Form = "frmCategory", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "LUPCategoryByID")
public class Category extends AbstractBase implements IElements {

	@JSONEntity(jsonproperty = "title")
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "ParentCategory")
	private String m_ParentCateogry;
	@DominoEntity(FieldName = "DefaultDesignKey")
	private String m_DefaultDesignKey;

	/**
	 * FirstDocumentURL is filled during the process of generation of JSON
	 * Result
	 */
	@JSONEntity(jsonproperty = "url")
	private String m_FirstDocumentURL;

	/**
	 * Subcategories is filled during the process of generation of the JSON
	 * Result
	 */
	@JSONEntity(jsonproperty = "url")
	private List<Category> m_SubCategories;
	public List<Category> getSubCategories() {
		return m_SubCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		m_SubCategories = subCategories;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		Category cat = (Category) obj;
		cat.m_DefaultDesignKey = m_DefaultDesignKey;
		cat.m_ParentCateogry = m_ParentCateogry;
		cat.m_Title = m_Title;
		return cat;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setParentCateogry(String parentCateogry) {
		m_ParentCateogry = parentCateogry;
	}

	public String getParentCateogry() {
		return m_ParentCateogry;
	}

	public void setDefaultDesignKey(String defaultDesignKey) {
		m_DefaultDesignKey = defaultDesignKey;
	}

	public String getDefaultDesignKey() {
		return m_DefaultDesignKey;
	}

	public void setFirstDocumentURL(String firstDocumentURL) {
		m_FirstDocumentURL = firstDocumentURL;
	}

	public String getFirstDocumentURL() {
		return m_FirstDocumentURL;
	}

}
