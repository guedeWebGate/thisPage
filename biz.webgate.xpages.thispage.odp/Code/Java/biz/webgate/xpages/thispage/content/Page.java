package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

import com.ibm.xsp.http.MimeMultipart;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.rest.IElements;
@JSONObject
@DominoStore(Form="frmPage", PrimaryFieldClass=String.class, PrimaryKeyField="ID", View="lupPagesByID")
public class Page extends AbstractBase implements IElements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@DominoEntity(FieldName = "Content")
	private MimeMultipart m_Content;
	@JSONEntity(jsonproperty="title")
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "BrowserTitle")
	private String m_BrowserTitle;
	@JSONEntity(jsonproperty="navigationtitle")
	@DominoEntity(FieldName = "NavigationTitle")
	private String m_NavigationTitle;
	@DominoEntity(FieldName = "doNotShowInNavigation")
	private boolean m_doNotShowInNavigation;
	@DominoEntity(FieldName = "CategoryKey")
	private String m_CategoryKey;
	
	//Some lazyloaded Values
	private String m_CategoryPath;
	
	@JSONEntity(jsonproperty="url")
	private String m_DocumentURL;
	
	@JSONEntity(jsonproperty="abstract")
	private String m_Abstract;
	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		Page pgNew = (Page)obj;
		pgNew.m_BrowserTitle = m_BrowserTitle;
		pgNew.m_Content = m_Content;
		pgNew.m_doNotShowInNavigation = m_doNotShowInNavigation;
		pgNew.m_NavigationTitle = m_NavigationTitle;
		pgNew.m_Title = m_Title;
		pgNew.m_CategoryKey = m_CategoryKey;
		return pgNew;
	}

	public void setContent(MimeMultipart content) {
		m_Content = content;
	}

	public MimeMultipart getContent() {
		return m_Content;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setBrowserTitle(String browserTitle) {
		m_BrowserTitle = browserTitle;
	}

	public String getBrowserTitle() {
		return m_BrowserTitle;
	}

	public void setNavigationTitle(String navigationTitle) {
		m_NavigationTitle = navigationTitle;
	}

	public String getNavigationTitle() {
		return m_NavigationTitle;
	}

	public void setDoNotShowInNavigation(boolean doNotShowInNavigation) {
		m_doNotShowInNavigation = doNotShowInNavigation;
	}

	public boolean isDoNotShowInNavigation() {
		return m_doNotShowInNavigation;
	}

	public void setCategoryKey(String categoryKey) {
		m_CategoryKey = categoryKey;
	}

	public String getCategoryKey() {
		return m_CategoryKey;
	}

	public void setCategoryPath(String categoryPath) {
		m_CategoryPath = categoryPath;
	}

	public String getCategoryPath() {
		if (m_CategoryPath == null) {
			m_CategoryPath = CategoryStorageService.getInstance().buildCategoryPath(m_CategoryKey);
		}
		return m_CategoryPath;
	}

	public void setDocumentURL(String documentURL) {
		m_DocumentURL = documentURL;
	}

	public String getDocumentURL() {
		return m_DocumentURL;
	}

	public void setAbstract(String _abstract) {
		m_Abstract = _abstract;
	}

	public String getAbstract() {
		return m_Abstract;
	}

}
