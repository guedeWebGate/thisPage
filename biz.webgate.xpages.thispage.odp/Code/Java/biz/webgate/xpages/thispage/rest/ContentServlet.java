package biz.webgate.xpages.thispage.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openntf.xpt.core.json.JSONService;
import org.openntf.xpt.core.utils.FileService;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.content.File;
import biz.webgate.xpages.thispage.content.FileStorageService;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.design.DesignBlockStorageService;
import biz.webgate.xpages.thispage.design.DesignContent;
import biz.webgate.xpages.thispage.design.DesignContentStorageService;
import biz.webgate.xpages.thispage.design.DesignFile;
import biz.webgate.xpages.thispage.design.DesignFileStorageService;
import biz.webgate.xpages.thispage.design.DesignPicture;
import biz.webgate.xpages.thispage.design.DesignPictureStorageService;
import biz.webgate.xpages.thispage.services.IDDockKeyExtractor;
import biz.webgate.xpages.thispage.services.html.PhotoPublisher;

import com.ibm.commons.util.io.StreamUtil;
import com.ibm.domino.services.util.JsonWriter;

public class ContentServlet extends HttpServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String IMG = "/img/";
	public final static String FILE = "/file/";
	private final static String CONTENT_DB_HTML = "/db/template.html";
	private final static String CONTENT_DB_JSON = "/db/feed.json";
	private final static String DESIGN_JS = "/js/source.js";
	private final static String DESIGN_CSS = "/css/style.css";
	private final static String DESIGN_FILE = "/design/file/";
	private final static String DESIGN_IMG = "/design/img/";

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String strPath = req.getPathInfo();
			if (strPath.startsWith(IMG)) {
				handleIMG(cut(IMG, strPath), req, res, false);
			}
			if (strPath.startsWith(FILE)) {
				handleFile(cut(FILE, strPath), req, res);
			}
			if (strPath.startsWith(CONTENT_DB_HTML)) {
				String id = req.getParameter("id");
				String pageid = req.getParameter("pageid");
				handleHTMLContent(id, pageid, req, res);
			}

			if (strPath.startsWith(CONTENT_DB_JSON)) {
				String id = req.getParameter("id");
				String pageid = req.getParameter("pageid");
				handleJSONFeed(id, pageid, req, res);
			}
			if (strPath.startsWith(DESIGN_JS)) {
				String id = req.getParameter("id");
				handleDESIGNContent(id, req, res);
			}
			if (strPath.startsWith(DESIGN_CSS)) {
				String id = req.getParameter("id");
				handleDESIGNContent(id, req, res);
			}
			if (strPath.startsWith(DESIGN_FILE)) {
				handleDESIGNFile(cut(DESIGN_FILE, strPath), req, res);
			}
			if (strPath.startsWith(DESIGN_IMG)) {
				handleDESIGNPicture(cut(DESIGN_IMG, strPath), req, res);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException("Erroir in ContentServlet", e);
		} finally {
		}
	}

	private void handleDESIGNPicture(String cut, HttpServletRequest req, HttpServletResponse res) {
		String docKey = IDDockKeyExtractor.INSTANCE.getIDDocKey(cut);
		DesignPicture df = DesignPictureStorageService.getInstance().getByDocKey(docKey, DocStatus.PUBLISHED);
		if (df != null) {
			try {
				OutputStream os = res.getOutputStream();
				InputStream is = null;
				is = FileService.INSTANCE.getFileStream(df.getFile().get(0));
				StreamUtil.copyStream(is, os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	private void handleDESIGNFile(String cut, HttpServletRequest req, HttpServletResponse res) {
		String docKey = IDDockKeyExtractor.INSTANCE.getIDDocKey(cut);
		DesignFile df = DesignFileStorageService.getInstance().getByDocKey(docKey, DocStatus.PUBLISHED);
		if (df != null) {
			try {
				OutputStream os = res.getOutputStream();
				InputStream is = null;
				is = FileService.INSTANCE.getFileStream(df.getFile().get(0));
				StreamUtil.copyStream(is, os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	private void handleFile(String cut, HttpServletRequest req, HttpServletResponse res) {
		String docKey = IDDockKeyExtractor.INSTANCE.getIDDocKey(cut);
		File df = FileStorageService.getInstance().getByDocKey(docKey, DocStatus.PUBLISHED);
		if (df != null) {
			try {
				OutputStream os = res.getOutputStream();
				InputStream is = null;
				is = FileService.INSTANCE.getFileStream(df.getFile().get(0));
				StreamUtil.copyStream(is, os);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void handleDESIGNContent(String id, HttpServletRequest req, HttpServletResponse res) {
		DesignContent dc = DesignContentStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
		if (dc != null) {
			try {
				PrintWriter pw = res.getWriter();
				pw.println(dc.getContent());
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String cut(String prefix, String strCall) {
		return strCall.substring(prefix.length());
	}

	private void handleIMG(String strCall, HttpServletRequest req, HttpServletResponse res, boolean prev) {
		int nPos = strCall.indexOf("/");
		String strID = strCall.substring(0, nPos);
		System.out.println(strID);
		if (prev) {
			PhotoPublisher.INSTANCE.processToStreamByID(req, res, strID, false);
		} else {
			PhotoPublisher.INSTANCE.processToStreamByDocKey(req, res, strID, false);

		}
	}

	private void handleHTMLContent(String id, String pageid, HttpServletRequest req, HttpServletResponse res) {
		Page page = PageStorageService.getInstance().getByDocKey(pageid, DocStatus.PUBLISHED);
		DesignBlock db = DesignBlockStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
		try {
			PrintWriter pw = res.getWriter();
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

	@SuppressWarnings("unchecked")
	private void handleJSONFeed(String id, String pageid, HttpServletRequest req, HttpServletResponse res) {
		Page page = PageStorageService.getInstance().getByDocKey(pageid, DocStatus.PUBLISHED);
		DesignBlock db = DesignBlockStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
		IResult<?> result = db.getStrategie().getRenderer().buildJSONResult(db, page, req.getParameterMap());
		try {
			JsonWriter jsWriter = new JsonWriter(res.getWriter(), true);
			JSONService.getInstance().process2JSON(jsWriter, result);
			jsWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
