package biz.webgate.xpages.thispage.design;

import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.dss.binding.util.FileHelper;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmDesignPicture", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupDesignPicturesByID")
public class DesignPicture extends AbstractBase {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "AltText")
	private String m_AltText;
	@DominoEntity(FieldName = "Width")
	private int m_Width;
	@DominoEntity(FieldName = "Height")
	private int m_Height;
	@DominoEntity(FieldName = "Type")
	private String m_Type;

	@DominoEntity(FieldName = "File")
	private UploadedFile m_UploadFile;

	@DominoEntity(FieldName = "File")
	private List<FileHelper> m_File;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		DesignPicture designPic = (DesignPicture) obj;
		designPic.m_AltText = m_AltText;
		designPic.m_File = m_File;
		designPic.m_Height = m_Height;
		designPic.m_Title = m_Title;
		designPic.m_Type = m_Type;
		designPic.m_Width = m_Width;
		return designPic;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getAltText() {
		return m_AltText;
	}

	public void setAltText(String altText) {
		m_AltText = altText;
	}

	public int getWidth() {
		return m_Width;
	}

	public void setWidth(int width) {
		m_Width = width;
	}

	public int getHeight() {
		return m_Height;
	}

	public void setHeight(int height) {
		m_Height = height;
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

	public void setUploadFile(UploadedFile uploadFile) {
		m_UploadFile = uploadFile;
	}

	public UploadedFile getUploadFile() {
		return m_UploadFile;
	}

}
