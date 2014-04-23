package biz.webgate.xpages.thispage.rest;

import biz.webgate.xpages.thispage.services.html.PhotoPublisher;

import com.ibm.domino.services.ServiceException;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.xsp.extlib.component.rest.CustomService;
import com.ibm.xsp.extlib.component.rest.CustomServiceBean;

public class RESTBean extends CustomServiceBean {

	private final static String CONTENT = "/content/";
	private final static String CONTENT_IMG = CONTENT +"img/";
	private final static String CONTENT_FILE = CONTENT +"file/";
	private final static String CONTENT_CSS = CONTENT +"css/";
	private final static String CONTENT_JS = CONTENT +"js/";

	private final static String IMG = "/img/";
	private final static String PREVIEW_IMG = "/preview/img/";
	private final static String FILE = "/file/";

	@Override
	public void renderService(CustomService cs, RestServiceEngine rest) throws ServiceException {
	
		String strCall = rest.getHttpRequest().getPathInfo();
		System.out.println("REST CALL: "+strCall);
		if (strCall.startsWith(IMG)) {
			handleIMG(cut(IMG,strCall), rest);
			return;
		}
		if (strCall.startsWith(PREVIEW_IMG)) {
			handleIMG(cut(PREVIEW_IMG,strCall), rest);
			return;
		}
	}

	private String cut(String prefix, String strCall) {
		return strCall.substring(prefix.length());
	}

	private void handleIMG(String strCall, RestServiceEngine rest) {
		int nPos = strCall.indexOf("/");
		String strID = strCall.substring(0, nPos);
		System.out.println(strID);
		PhotoPublisher.INSTANCE.processToStreamByID(rest.getHttpRequest(), rest.getHttpResponse(), strID);
	}
	
}
