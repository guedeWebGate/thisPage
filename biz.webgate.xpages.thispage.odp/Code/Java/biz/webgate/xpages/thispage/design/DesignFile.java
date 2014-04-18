package biz.webgate.xpages.thispage.design;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.dss.binding.util.FileHelper;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmDesignFile", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupDesignFileByID")
public class DesignFile extends AbstractBase {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "Type")
	private String m_Type;
	@DominoEntity(FieldName = "File")
	private FileHelper m_File;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		DesignFile designFile = (DesignFile) obj;
		designFile.m_File = m_File;
		designFile.m_Title = m_Title;
		designFile.m_Type = m_Type;
		return designFile;
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

	public void setFile(FileHelper file) {
		m_File = file;
	}

	public FileHelper getFile() {
		return m_File;
	}

}
