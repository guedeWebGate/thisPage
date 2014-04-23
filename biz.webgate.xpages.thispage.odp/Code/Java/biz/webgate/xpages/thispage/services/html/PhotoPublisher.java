package biz.webgate.xpages.thispage.services.html;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openntf.xpt.core.utils.FileService;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.Picture;
import biz.webgate.xpages.thispage.content.PictureStorageService;

import com.ibm.commons.util.io.StreamUtil;

public enum PhotoPublisher {
	INSTANCE;

	public void processToStreamByDocKey(HttpServletRequest request, HttpServletResponse response, String strDocKey) {
		InputStream is = null;
		Picture pic = PictureStorageService.getInstance().getByDocKey(strDocKey, DocStatus.PUBLISHED);
		boolean blThumbnail = "1".equalsIgnoreCase(request.getParameter("thumbnail"));
		try {
			OutputStream os = response.getOutputStream();
			if (pic != null) {
				is = FileService.INSTANCE.getFileStream(pic.getFile().get(0));
				StreamUtil.copyStream(is, os);
				is.close();
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void processToStreamByID(HttpServletRequest request, HttpServletResponse response, String strID) {
		InputStream is = null;
		Picture pic = PictureStorageService.getInstance().getById(strID);
		boolean blThumbnail = "1".equalsIgnoreCase(request.getParameter("thumbnail"));
		try {
			OutputStream os = response.getOutputStream();
			if (pic != null) {
				is = FileService.INSTANCE.getFileStream(pic.getFile().get(0));
				StreamUtil.copyStream(is, os);
				is.close();
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
