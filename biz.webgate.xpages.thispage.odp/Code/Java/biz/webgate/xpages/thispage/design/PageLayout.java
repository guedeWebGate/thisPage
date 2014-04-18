package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmPageLayout", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupPageLayoutByID")
public class PageLayout extends AbstractBase {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "Layout")
	private String m_Layout;

	public String getLayout() {
		return m_Layout;
	}

	public void setLayout(String layout) {
		m_Layout = layout;
	}

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		PageLayout pic = (PageLayout) obj;
		pic.m_Title = m_Title;
		pic.m_Layout = m_Layout;
		return pic;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

}
