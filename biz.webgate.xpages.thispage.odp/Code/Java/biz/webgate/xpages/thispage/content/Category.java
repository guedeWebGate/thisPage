package biz.webgate.xpages.thispage.content;

import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

import com.ibm.commons.util.StringUtil;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.rest.IElements;

@JSONObject
@DominoStore(Form = "frmCategory", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "LUPCategoryByID")
public class Category extends AbstractBase implements IElements {

	@JSONEntity(jsonproperty = "title")
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "ParentCategory")
	private String m_ParentCategory;
	@DominoEntity(FieldName = "DefaultDesignKey")
	private String m_DefaultDesignKey;

	@DominoEntity(FieldName = "StartPage")
	private String m_StartPage;

	@DominoEntity(FieldName = "SortOrder")
	private double m_SortOrder = 0;

	// Some lazyloaded Values
	private String m_CategoryPath;

	/**
	 * FirstDocumentURL is filled during the process of generation of JSON
	 * Result
	 */
	@JSONEntity(jsonproperty = "url")
	private String m_FirstDocumentURL;

	@JSONEntity(jsonproperty = "active")
	private boolean m_Selected;

	public boolean isSelected() {
		return m_Selected;
	}

	public void setSelected(boolean selected) {
		m_Selected = selected;
	}

	/**
	 * Subcategories is filled during the process of generation of the JSON
	 * Result
	 */
	@JSONEntity(jsonproperty = "categories")
	private List<Category> m_SubCategories;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		Category cat = (Category) obj;
		cat.m_DefaultDesignKey = m_DefaultDesignKey;
		cat.m_ParentCategory = m_ParentCategory;
		cat.m_Title = m_Title;
		cat.m_StartPage = m_StartPage;
		return cat;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setParentCategory(String parentCateogry) {
		m_ParentCategory = parentCateogry;
	}

	public String getParentCategory() {
		return m_ParentCategory;
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
		if (m_FirstDocumentURL == null) {
			m_FirstDocumentURL = "thispage.xsp?id=" + m_StartPage + ".html";
		}
		return m_FirstDocumentURL;
	}

	public String getCategoryPath() {
		if (m_CategoryPath == null) {
			if (!StringUtil.isEmpty(m_ParentCategory)) {
				m_CategoryPath = CategoryStorageService.getInstance().buildCategoryPath(m_ParentCategory);
			} else {
				m_CategoryPath = "<no parent>";
			}
		}
		return m_CategoryPath;
	}

	public List<Category> getSubCategories() {
		return m_SubCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		m_SubCategories = subCategories;
	}

	public String getStartPage() {
		return m_StartPage;
	}

	public void setStartPage(String startPage) {
		m_StartPage = startPage;
	}

	public void setSortOrder(double sortOrder) {
		m_SortOrder = sortOrder;
	}

	public double getSortOrder() {
		return m_SortOrder;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Category && getID().equals(((Category) obj).getID());
	}

	@Override
	public int hashCode() {
		return this.getID().hashCode();
	}
}
