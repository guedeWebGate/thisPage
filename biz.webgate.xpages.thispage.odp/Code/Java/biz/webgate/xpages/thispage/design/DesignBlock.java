package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.DesignBlockStrategie;

@DominoStore(Form="frmDesignBlock", PrimaryFieldClass=String.class, PrimaryKeyField="ID", View="lupDesignBlockByID")
public class DesignBlock extends AbstractBase {

	private static final long serialVersionUID = 1L;

	private String m_Title;
	private DesignBlockStrategie m_Strategie;
	private String m_MainContent;
	private String m_SubContent;
	private String m_PreMainContent;
	private String m_PostMainContent;
	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		DesignBlock block = (DesignBlock) obj;
		block.m_MainContent = m_MainContent;
		block.m_PostMainContent = m_PostMainContent;
		block.m_PreMainContent = m_PreMainContent;
		block.m_Strategie = m_Strategie;
		block.m_SubContent = m_SubContent;
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
	public String getSubContent() {
		return m_SubContent;
	}
	public void setSubContent(String subContent) {
		m_SubContent = subContent;
	}
	public String getPreMainContent() {
		return m_PreMainContent;
	}
	public void setPreMainContent(String preMainContent) {
		m_PreMainContent = preMainContent;
	}
	public String getPostMainContent() {
		return m_PostMainContent;
	}
	public void setPostMainContent(String postMainContent) {
		m_PostMainContent = postMainContent;
	}

}
