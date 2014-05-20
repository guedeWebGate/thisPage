package biz.webgate.xpages.thispage.content;

import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;
import org.openntf.xpt.core.dss.binding.util.FileHelper;

import com.ibm.xsp.component.UIFileuploadEx.UploadedFile;

import biz.webgate.xpages.thispage.AbstractBase;
import biz.webgate.xpages.thispage.IPicture;

@DominoStore(Form = "frmPicture", PrimaryFieldClass = String.class, PrimaryKeyField = "ID", View = "lupPicturesByID")
public class Picture extends AbstractBase implements IPicture {

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
		Picture pic = (Picture) obj;
		pic.m_AltText = m_AltText;
		pic.m_File = m_File;
		pic.m_Height = m_Height;
		pic.m_Title = m_Title;
		pic.m_Type = m_Type;
		pic.m_Width = m_Width;
		return pic;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getTitle()
	 */
	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getAltText()
	 */
	public String getAltText() {
		return m_AltText;
	}

	public void setAltText(String altText) {
		m_AltText = altText;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getWidth()
	 */
	public int getWidth() {
		return m_Width;
	}

	public void setWidth(int width) {
		m_Width = width;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getHeight()
	 */
	public int getHeight() {
		return m_Height;
	}

	public void setHeight(int height) {
		m_Height = height;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getType()
	 */
	public String getType() {
		return m_Type;
	}

	public void setType(String type) {
		m_Type = type;
	}

	public void setFile(List<FileHelper> file) {
		m_File = file;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.content.IPicture#getFile()
	 */
	public List<FileHelper> getFile() {
		return m_File;
	}

	public UploadedFile getUploadFile() {
		return m_UploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		m_UploadFile = uploadFile;
	}
}
