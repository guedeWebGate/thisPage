package biz.webgate.xpages.thispage.content;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.dss.binding.util.FileHelper;

import biz.webgate.xpages.thispage.AbstractBase;

@DominoStore(Form = "frmPicture", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupPicturesByID")
public class Picture extends AbstractBase {

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
	private FileHelper m_File;

	@Override
	protected AbstractBase buildNewVersion(AbstractBase obj) {
		Picture pic = (Picture) obj;
		pic.m_AltText = m_AltText;
		pic.m_File = m_File;
		pic.m_Height = m_Height;
		pic.m_Title = m_Title;
		pic.m_Type = m_Type;
		pic.m_Width = m_Width;
		return pic;
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

	public void setFile(FileHelper file) {
		m_File = file;
	}

	public FileHelper getFile() {
		return m_File;
	}

}
