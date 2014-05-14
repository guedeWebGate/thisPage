package biz.webgate.xpages.thispage.services.html;

import java.io.PrintWriter;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.api.DesignSessionFacade;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.design.PageLayout;

import com.ibm.commons.util.StringUtil;

public class PageHTMLBuilder {

	private static final PageHTMLBuilder m_Builder = new PageHTMLBuilder();

	private PageHTMLBuilder() {
	}

	public static final PageHTMLBuilder getInstance() {
		return m_Builder;
	}

	public String buildHTMLContent(Page pageCurrent, HttpServletRequest request) {
		System.out.println("Search pl...");
		PageLayout pl = DesignSessionFacade.get().getPageLayoutForPage(pageCurrent);
		String htmlCode = pl.getLayout();
		System.out.println("HTML-CODE:" + htmlCode);
		try {
			StringBuilder sbJSCode = buildInternalJS(request);
			StringBuilder sbLoaderCode = new StringBuilder();
			sbLoaderCode.append("<script type=\"text/javascript\">");
			sbLoaderCode.append("XSP.addOnLoad(function() {\n");

			List<DesignBlock> lstDB = DesignSessionFacade.get().allDesignBlockPublished();
			for (DesignBlock db : lstDB) {
				String markup = "###"+db.getTitle()+"###";
				if (htmlCode.contains(markup)) {
					htmlCode = StringUtil.replace(htmlCode, markup, db.getStrategie().getRenderer().buildHTMLTag(db, pageCurrent));
					sbLoaderCode.append(db.getStrategie().getRenderer().buildJSLoader(db, pageCurrent));
					sbLoaderCode.append("\n");
				}
			}
			sbLoaderCode.append("});\n");
			sbLoaderCode.append("</script>");
			htmlCode = StringUtil.replace(htmlCode, "###SYSTEM_JS###", sbJSCode.toString());
			htmlCode = StringUtil.replace(htmlCode, "###SYSTEM_LOADER###", sbLoaderCode.toString());
			htmlCode = StringUtil.replace(htmlCode, "###BROWSER_TITLE###", pageCurrent.getBrowserTitle());
			htmlCode = StringUtil.replace(htmlCode, "###DOCUMENT_TITLE###", pageCurrent.getTitle());
			if (pageCurrent.getContent() != null) {
				htmlCode = StringUtil.replace(htmlCode, "###CONTENT###", pageCurrent.getContent().getHTML());
			} else {
				htmlCode = StringUtil.replace(htmlCode, "###CONTENT###", "");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlCode;
	}

	private StringBuilder buildInternalJS(HttpServletRequest request) {
		StringBuilder sbRC = new StringBuilder();
		String url = request.getContextPath();
		sbRC.append("<script type=\"text/javascript\">var dojoConfig = {locale: 'en-us'};</script>\n");
		sbRC.append("<script type=\"text/javascript\" src=\"/xsp/.ibmxspres/dojoroot-1.8.3/dojo/dojo.js\"></script>\n");
		sbRC.append("<script type=\"text/javascript\">dojo.registerModulePath(\"thispage.container\", \"" + url
				+ "/thispage.container\");</script>\n");
		sbRC.append("<script type=\"text/javascript\" src=\"/xsp/.ibmxspres/.mini/dojo/.en-us/@Iq.js\"></script>\n");
		sbRC.append("<script type=\"text/javascript\">dojo.require(\"ibm.xsp.widget.layout.xspClientDojo\")</script>\n");
		sbRC.append("<script type=\"text/javascript\">dojo.require(\"thispage.container\")</script>\n");
		return sbRC;
	}
	
	public void processHTTPCall() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpServletResponse res = (HttpServletResponse)context.getExternalContext().getResponse();
			String id = req.getParameter("id");
			if (id.endsWith(".html")) {
				int nPos = id.indexOf(".html");
				id = id.substring(0, nPos);
			}
			Page pg = PageStorageService.getInstance().getByDocKey(id, DocStatus.PUBLISHED);
			PrintWriter pw = res.getWriter();
			pw.println(buildHTMLContent(pg, req));
			context.responseComplete();
			//pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacesException("HTML Builder Error: ", e);
		}
	}
}
