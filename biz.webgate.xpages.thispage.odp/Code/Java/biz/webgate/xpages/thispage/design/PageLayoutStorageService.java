package biz.webgate.xpages.thispage.design;

import biz.webgate.xpages.thispage.AbstractContentStorageService;

public class PageLayoutStorageService extends AbstractContentStorageService<PageLayout> {

	private static final PageLayoutStorageService m_Service = new PageLayoutStorageService();

	public static PageLayoutStorageService getInstance() {
		return m_Service;
	}

	@Override
	public PageLayout createObject() {
		return defaultHTML(new PageLayout());
	}

	private PageLayout defaultHTML(PageLayout pl) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
		sb.append("<html lang=\"en\">\n");
		sb.append("<head>\n");
		sb.append("<title>###BROWSER_TITLE</title>\n");
		sb.append("###SYSTEM_JS###");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("###SYSTEM_LOADER###\n");
		sb.append("###CONTENT###\n");
		sb.append("</body>\n");
		sb.append("</html>\n");
		pl.setLayout(sb.toString());
		return pl;
	}
}
