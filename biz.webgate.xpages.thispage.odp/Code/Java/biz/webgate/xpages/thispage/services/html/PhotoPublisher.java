package biz.webgate.xpages.thispage.services.html;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openntf.xpt.core.utils.ErrorJSONBuilder;
import org.openntf.xpt.core.utils.FileService;
import org.openntf.xpt.core.utils.HttpResponseSupport;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.Picture;
import biz.webgate.xpages.thispage.content.PictureStorageService;

import com.ibm.commons.util.io.StreamUtil;

public enum PhotoPublisher {
INSTANCE;

public void processToStream(HttpServletRequest request, HttpServletResponse response, String strDocKey) {
	InputStream is = null;
	Picture pic = PictureStorageService.getInstance().getByDocKey(strDocKey, DocStatus.PUBLISHED);
	boolean blThumbnail = "1".equalsIgnoreCase(request.getParameter("thumbnail"));
	try {
		if (pic != null)
			OutputStream os = response.getOutputStream();
			is = FileService.INSTANCE.getFileStream(pic.getFile());
			StreamUtil.copyStream(is, os);
			os.close();
			is.close();
		} else {
			HttpResponseSupport.setJSONUTF8ContentType(engine);
			ErrorJSONBuilder.getInstance().processError2JSON(
					engine,
					7100,
					nResult + " is Result of getPhotoByID with "
							+ thisPhoto.getStoreID() + " / "
							+ thisPhoto.getID(), null);
		}
	} catch (Exception e) {
	}


}
