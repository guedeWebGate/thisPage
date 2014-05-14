package biz.webgate.xpages.thispage.renderer;

import biz.webgate.xpages.thispage.IDesignBlockStrategyRenderer;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;

public abstract class AbstractDesignBlockStrategyRenderer implements IDesignBlockStrategyRenderer {

	public String buildHTMLTag(DesignBlock db, Page page) {
		String id = db.getDocKey();
		return "<div id=\"" + id + "\"></div>";
	}

	public String buildJSLoader(DesignBlock db, Page page) {
		String id = db.getDocKey();
		String jsID = id.replace("-", "_");
		return "var db" + jsID + " = new thispage.container( {containerid: '" + id + "',pageid: '" + page.getDocKey()
				+ "', templateString : dojo.cache('xsp/this.page/db/template.html?id=" + id + "&pageid=" + page.getDocKey() + "')}).placeAt('" + id + "');";
	}

	public String getDjangoTemplate(DesignBlock db, Page page) {
		return db.getMainContent();
	}

}
