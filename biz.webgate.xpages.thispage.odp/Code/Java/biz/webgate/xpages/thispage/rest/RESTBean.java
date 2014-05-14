package biz.webgate.xpages.thispage.rest;

import java.io.PrintWriter;

import javax.faces.FacesException;

import org.openntf.xpt.core.json.JSONService;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.design.DesignBlockStorageService;
import biz.webgate.xpages.thispage.services.html.PageHTMLBuilder;
import biz.webgate.xpages.thispage.services.html.PhotoPublisher;

import com.ibm.domino.services.ServiceException;
import com.ibm.domino.services.rest.RestServiceEngine;
import com.ibm.domino.services.util.JsonWriter;
import com.ibm.xsp.extlib.component.rest.CustomService;
import com.ibm.xsp.extlib.component.rest.CustomServiceBean;

public class RESTBean extends CustomServiceBean {

	private final static String CONTENT = "/content/";
	private final static String CONTENT_IMG = CONTENT + "img/";
	private final static String CONTENT_DIMG = CONTENT + "d-img/";
	private final static String CONTENT_FILE = CONTENT + "file/";
	private final static String CONTENT_DFILE = CONTENT + "d-file/";
	private final static String CONTENT_CSS = CONTENT + "css/";
	private final static String CONTENT_JS = CONTENT + "js/";

	private final static String CONTENT_DB_HTLM = CONTENT + "db/template.html";
	private final static String CONTENT_DB_JSON = CONTENT + "db/feed.json";

	private final static String IMG = "/img/";
	private final static String PREVIEW_IMG = "/preview/img/";
	private final static String PREVIEW_DIMG = "/preview/d-img/";
	private final static String FILE = "/file/";

	@Override
	public void renderService(CustomService cs, RestServiceEngine rest) throws ServiceException {

		String strCall = rest.getHttpRequest().getPathInfo();
		System.out.println("REST CALL: " + strCall);
		if (strCall.startsWith(IMG)) {
			handleIMG(cut(IMG, strCall), rest, false);
			return;
		}
		if (strCall.startsWith(PREVIEW_IMG)) {
			handleIMG(cut(PREVIEW_IMG, strCall), rest,true);
			return;
		}

		if (strCall.startsWith(CONTENT_IMG)) {
			handleIMG(cut(CONTENT_IMG, strCall), rest, false);
			return;
		}

		if (strCall.startsWith(CONTENT_DB_HTLM)) {
			String id = rest.getHttpRequest().getParameter("id");
			String pageid = rest.getHttpRequest().getParameter("pageid");
			handleHTMLContent(id, pageid, rest);
		}

		if (strCall.startsWith(CONTENT_DB_JSON)) {
			String id = rest.getHttpRequest().getParameter("id");
			String pageid = rest.getHttpRequest().getParameter("pageid");
			handleJSONFeed(id, pageid, rest);
		}

		if (strCall.startsWith(CONTENT) && strCall.endsWith(".html")) {
			System.out.println("YES Content");
			handleContent(cut(CONTENT, strCall), rest);
		}
	}

	@SuppressWarnings("unchecked")
	private void handleJSONFeed(String id, String pageid, RestServiceEngine rest) {
		Page page = PageStorageService.getInstance().getByDocKey(pageid, DocStatus.PUBLISHED);
		DesignBlock db = DesignBlockStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
		IResult res = db.getStrategie().getRenderer().buildJSONResult(db, page, rest.getHttpRequest().getParameterMap());
		try {
			JsonWriter jsWriter = new JsonWriter(rest.getHttpResponse().getWriter(), true);
			JSONService.getInstance().process2JSON(jsWriter, res);
			jsWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void handleHTMLContent(String id, String pageid, RestServiceEngine rest) {
		Page page = PageStorageService.getInstance().getByDocKey(pageid, DocStatus.PUBLISHED);
		DesignBlock db = DesignBlockStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
		try {
			PrintWriter pw = rest.getHttpResponse().getWriter();
			if (db != null) {
				pw.print(db.getStrategie().getRenderer().getDjangoTemplate(db, page));
			} else {
				pw.print(id + " cant be found!");
			}
			pw.close();
		} catch (Exception e) {
			throw new FacesException("Error during RESTBean.handleContent!", e);
		}

	}

	private void handleContent(String document, RestServiceEngine rest) {
		int nPos = document.indexOf(".html");
		String strDocKey = document.substring(0, nPos);
		System.out.println("DocKey ->" + strDocKey);
		Page page = PageStorageService.getInstance().getByDocKey(strDocKey, DocStatus.PUBLISHED);;
		try {
			PrintWriter pw = rest.getHttpResponse().getWriter();
			if (page != null) {
				pw.print(PageHTMLBuilder.getInstance().buildHTMLContent(page, rest.getHttpRequest()));
			} else {
				pw.print(document + " cant be found!");
			}
			pw.close();
		} catch (Exception e) {
			throw new FacesException("Error during RESTBean.handleContent!", e);
		}

	}

	private String cut(String prefix, String strCall) {
		return strCall.substring(prefix.length());
	}

	private void handleIMG(String strCall, RestServiceEngine rest, boolean prev) {
		int nPos = strCall.indexOf("/");
		String strID = strCall.substring(0, nPos);
		System.out.println(strID);
		if (prev) {
			PhotoPublisher.INSTANCE.processToStreamByID(rest.getHttpRequest(), rest.getHttpResponse(), strID);
		} else {
			PhotoPublisher.INSTANCE.processToStreamByDocKey(rest.getHttpRequest(), rest.getHttpResponse(), strID);

		}
	}

}
