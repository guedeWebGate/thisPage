package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.DesignBlockStrategie;

@DominoStore(Form = "frmDesignBlock", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupDesignBlockByID")
public class DesignBlock extends AbstractBase {

	private static final long serialVersionUID = 1L;

	@DominoEntity(FieldName = "Title")
	private String m_Title;
	private DesignBlockStrategie m_Strategie;
	@DominoEntity(FieldName = "StrategieName")
	private String m_StrategieName;
	@DominoEntity(FieldName = "MainContent")
	private String m_MainContent;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		DesignBlock block = (DesignBlock) obj;
		block.m_MainContent = m_MainContent;
		block.m_Strategie = m_Strategie;
		block.m_StrategieName = m_StrategieName;
		block.m_Title = m_Title;
		return block;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public DesignBlockStrategie getStrategie() {
		if (m_Strategie == null) {
			m_Strategie = DesignBlockStrategie.valueOf(m_StrategieName);
		}
		return m_Strategie;
	}

	public void setStrategie(DesignBlockStrategie strategie) {
		m_Strategie = strategie;
	}

	public String getMainContent() {
		return m_MainContent;
	}

	public void setMainContent(String mainContent) {
		m_MainContent = mainContent;
	}

	public String getStrategieName() {
		return m_StrategieName;
	}

	public void setStrategieName(String strategieName) {
		m_StrategieName = strategieName;
	}

}
