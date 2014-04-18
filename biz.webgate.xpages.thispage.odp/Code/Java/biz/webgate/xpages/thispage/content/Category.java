package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form="frmCategory", PrimaryFieldClass=String.class, PrimaryKeyField="ID", View="LUPCategoryByID")
public class Category extends AbstractBase {

	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "ParentCategory")
	private String m_ParentCateogry;
	@DominoEntity(FieldName = "DefaultDesignKey")
	private String m_DefaultDesignKey;
	
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

}
