package biz.webgate.xpages.thispage.rest;

import biz.webgate.xpages.thispage.services.IDDockKeyExtractor;
import biz.webgate.xpages.thispage.services.html.PhotoPublisher;

import com.ibm.domino.services.ServiceException;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.xsp.extlib.component.rest.CustomService;
import com.ibm.xsp.extlib.component.rest.CustomServiceBean;

public class RESTBean extends CustomServiceBean {

	private final static String PREVIEW_IMG = "/preview/img/";
	private final static String PREVIEW_DIMG = "/preview/d-img/";

	@Override
	public void renderService(CustomService cs, RestServiceEngine rest) throws ServiceException {

		String strCall = rest.getHttpRequest().getPathInfo();
		if (strCall.startsWith(PREVIEW_IMG)) {
			handleIMG(cut(PREVIEW_IMG, strCall), rest,true, false);
			return;
		}

		if (strCall.startsWith(PREVIEW_DIMG)) {
			handleIMG(cut(PREVIEW_DIMG, strCall), rest,true, true);
			return;
		}

	}


	private String cut(String prefix, String strCall) {
		return strCall.substring(prefix.length());
	}

	private void handleIMG(String strCall, RestServiceEngine rest, boolean prev, boolean useDesign) {
		String strID = IDDockKeyExtractor.INSTANCE.getIDDocKey(strCall);
		if (prev) {
			PhotoPublisher.INSTANCE.processToStreamByID(rest.getHttpRequest(), rest.getHttpResponse(), strID, useDesign);
		} else {
			PhotoPublisher.INSTANCE.processToStreamByDocKey(rest.getHttpRequest(), rest.getHttpResponse(), strID, useDesign);

		}
	}

}
