package biz.webgate.xpages.thispage.services.html;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import biz.webgate.xpages.thispage.api.DesignSessionFacade;
import biz.webgate.xpages.thispage.content.Page;
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
				if (htmlCode.contains(db.getTitle())) {
					htmlCode = StringUtil.replace(htmlCode, db.getTitle(), db.getStrategie().getRenderer().buildHTMLTag(db, pageCurrent));
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
			htmlCode = StringUtil.replace(htmlCode, "###CONTENT###", pageCurrent.getContent().getHTML());
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
}
