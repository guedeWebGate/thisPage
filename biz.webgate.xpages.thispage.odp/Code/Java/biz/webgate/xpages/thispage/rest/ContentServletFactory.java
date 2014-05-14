package biz.webgate.xpages.thispage.rest;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.ibm.designer.runtime.domino.adapter.ComponentModule;
import com.ibm.designer.runtime.domino.adapter.IServletFactory;
import com.ibm.designer.runtime.domino.adapter.ServletMatch;

public class ContentServletFactory implements IServletFactory {
	private ComponentModule m_Module;
	public static final String SERVLET_PATH = "/xsp/this.page";

	public ServletMatch getServletMatch(String contextPath, String path) throws ServletException {
		if (path.startsWith(SERVLET_PATH)) { // $NON-NLS-1$
			int len = SERVLET_PATH.length(); // $NON-NLS-1$
			String servletPath = path.substring(0, len);
			String pathInfo = path.substring(len);
			return new ServletMatch(getContentServlet(), servletPath, pathInfo);
		}
		return null;	}

	private Servlet getContentServlet() throws ServletException {
		return m_Module.createServlet("biz.webgate.xpages.thispage.rest.ContentServlet", "ThisPage ContentService", null);
	}

	public void init(ComponentModule arg0) {
		m_Module = arg0;

	}

}
