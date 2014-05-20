package biz.webgate.xpages.thispage.content;

import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.dss.binding.util.FileHelper;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmFile", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupFileByID")
public class File extends AbstractBase {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "Type")
	private String m_Type;
	@DominoEntity(FieldName = "File")
	private UploadedFile m_UploadFile;

	@DominoEntity(FieldName = "File")
	private List<FileHelper> m_File;
	@DominoEntity(FieldName = "CategoryKey")
	private String m_CategoryKey;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		File pic = (File) obj;
		pic.m_File = m_File;
		pic.m_Title = m_Title;
		pic.m_Type = m_Type;
		return pic;
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

	public void setFile(List<FileHelper> file) {
		m_File = file;
	}

	public List<FileHelper> getFile() {
		return m_File;
	}

	public void setCategoryKey(String categoryKey) {
		m_CategoryKey = categoryKey;
	}

	public String getCategoryKey() {
		return m_CategoryKey;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		m_UploadFile = uploadFile;
	}

	public UploadedFile getUploadFile() {
		return m_UploadFile;
	}

}
