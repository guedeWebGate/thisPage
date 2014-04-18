package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmDesignContent", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupDesignContentByID")
public class DesignContent extends AbstractBase {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "Type")
	private String m_Type;
	@DominoEntity(FieldName = "Content")
	private String m_Content;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		DesignContent designContent = (DesignContent) obj;
		designContent.m_Title = m_Title;
		designContent.m_Type = m_Type;
		designContent.m_Content = m_Content;
		return designContent;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getType() {
		return m_Type;
	}

	public void setType(String type) {
		m_Type = type;
	}

	public String getContent() {
		return m_Content;
	}

	public void setContent(String content) {
		m_Content = content;
	}

}
