package biz.webgate.xpages.thispage.services.html;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imgscalr.Scalr;
import org.openntf.xpt.core.utils.FileService;

import biz.webgate.xpages.thispage.AbstractContentStorageService;
import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.IPicture;
import biz.webgate.xpages.thispage.content.PictureStorageService;
import biz.webgate.xpages.thispage.design.DesignPicture;
import biz.webgate.xpages.thispage.design.DesignPictureStorageService;

import com.ibm.commons.util.io.StreamUtil;

public enum PhotoPublisher {
	INSTANCE;

	public void processToStreamByDocKey(HttpServletRequest request, HttpServletResponse response, String strDocKey, boolean useDesign) {
		InputStream is = null;
		IPicture pic = getPicture(strDocKey,true,useDesign);
		int nTumbnail = 0;
		if ("1".equalsIgnoreCase(request.getParameter("thumbnail"))) {
			nTumbnail = 1;
		}
		if ("2".equalsIgnoreCase(request.getParameter("thumbnail"))) {
			nTumbnail = 2;
		}
		try {
			OutputStream os = response.getOutputStream();
			if (pic != null) {
				is = FileService.INSTANCE.getFileStream(pic.getFile().get(0));
				switch (nTumbnail) {
				case 0:
					StreamUtil.copyStream(is, os);
					is.close();
					break;
				case 1:
					InputStream is2 = doResize(is, pic.getType(), 150);
					is.close();
					StreamUtil.copyStream(is2, os);
					is2.close();
					break;
				case 2:
					InputStream is3 = doResize(is, pic.getType(), 75);
					is.close();
					StreamUtil.copyStream(is3, os);
					is3.close();
					break;
				default:
					StreamUtil.copyStream(is, os);
					is.close();
					break;
				}
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processToStreamByID(HttpServletRequest request, HttpServletResponse response, String strID, boolean useDesign) {
		InputStream is = null;
		IPicture pic = getPicture(strID,false,useDesign);
		int nTumbnail = 0;
		if ("1".equalsIgnoreCase(request.getParameter("thumbnail"))) {
			nTumbnail = 1;
		}
		if ("2".equalsIgnoreCase(request.getParameter("thumbnail"))) {
			nTumbnail = 2;
		}
		try {
			OutputStream os = response.getOutputStream();
			if (pic != null) {
				is = FileService.INSTANCE.getFileStream(pic.getFile().get(0));
				switch (nTumbnail) {
				case 0:
					StreamUtil.copyStream(is, os);
					is.close();
					break;
				case 1:
					InputStream is2 = doResize(is, pic.getType(), 150);
					is.close();
					StreamUtil.copyStream(is2, os);
					is2.close();
					break;
				case 2:
					InputStream is3 = doResize(is, pic.getType(), 75);
					is.close();
					StreamUtil.copyStream(is3, os);
					is3.close();
					break;
				default:
					StreamUtil.copyStream(is, os);
					is.close();
					break;
				}
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream doResize(InputStream is, String picType, int size) {
		try {
			BufferedImage bfi = ImageIO.read(is);
			BufferedImage bfiTumb = Scalr.resize(bfi, size);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bfiTumb, picType, baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IPicture getPicture(String strID, boolean useDocKey, boolean useDesign) {
		AbstractContentStorageService<?> store = PictureStorageService.getInstance();
		if (useDesign) {
			store = DesignPictureStorageService.getInstance();
		}
		return (IPicture) (useDocKey ? store.getByDocKey(strID, DocStatus.PUBLISHED) : store.getById(strID));
	}
}
