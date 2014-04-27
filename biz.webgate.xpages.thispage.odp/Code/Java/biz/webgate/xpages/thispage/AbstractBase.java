package biz.webgate.xpages.thispage;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.openntf.xpt.core.dss.annotations.DominoEntity;

import biz.webgate.xpages.thispage.services.DocKeyProvider;
import biz.webgate.xpages.thispage.services.UserNameProvider;
import biz.webgate.xpages.thispage.services.VersionHandler;

public abstract class AbstractBase implements Serializable {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "ID")
	private String m_ID;
	@DominoEntity(FieldName = "DocKey")
	private String m_DocKey;
	@DominoEntity(FieldName = "Created")
	private Date m_Created;
	@DominoEntity(FieldName = "Published")
	private Date m_Published;
	@DominoEntity(FieldName = "Creator")
	private String m_Creator;
	@DominoEntity(FieldName = "Version")
	private int m_Version = 1;
	@DominoEntity(FieldName = "Status")
	private DocStatus m_Status;

	public AbstractBase() {
		m_ID = UUID.randomUUID().toString();
		m_DocKey = DocKeyProvider.INSTANCE.createDocKey();
		m_Status = DocStatus.DRAFT;
		m_Creator = UserNameProvider.INSTANCE.getUserName();
		m_Created = new Date();
	}

	public String getID() {
		return m_ID;
	}

	public void setID(String id) {
		m_ID = id;
	}

	public String getDocKey() {
		return m_DocKey;
	}

	public void setDocKey(String docKey) {
		m_DocKey = docKey;
	}

	public Date getCreated() {
		return m_Created;
	}

	public void setCreated(Date created) {
		m_Created = created;
	}

	public Date getPublished() {
		return m_Published;
	}

	public void setPublished(Date published) {
		m_Published = published;
	}

	public String getCreator() {
		return m_Creator;
	}

	public void setCreator(String creator) {
		m_Creator = creator;
	}

	public int getVersion() {
		return m_Version;
	}

	public void setVersion(int version) {
		m_Version = version;
	}

	public DocStatus getStatus() {
		return m_Status;
	}

	public void setStatus(DocStatus status) {
		m_Status = status;
	}

	public AbstractBase newVersion(String strCreator) {
		AbstractBase abs = null;
		try {
			abs = this.getClass().newInstance();
			abs.m_ID = UUID.randomUUID().toString();
			abs.m_DocKey = m_DocKey;
			abs.m_Created = new Date();
			abs.m_Creator = strCreator;
			abs.m_Version = VersionHandler.INSTANCE.getNewVersion(m_DocKey);
			abs = buildNewVersion(abs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return abs;
	}

	protected abstract AbstractBase buildNewVersion(AbstractBase obj);
}
