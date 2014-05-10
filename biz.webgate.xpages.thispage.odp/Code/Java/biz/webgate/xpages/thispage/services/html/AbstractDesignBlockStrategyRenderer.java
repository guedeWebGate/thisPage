package biz.webgate.xpages.thispage.services.html;

import biz.webgate.xpages.thispage.IDesignBlockStrategyRenderer;
import biz.webgate.xpages.thispage.design.DesignBlock;

public abstract class AbstractDesignBlockStrategyRenderer implements IDesignBlockStrategyRenderer {

	public String buildHTMLTag(String id) {
		return "<div id=\"" + id + "\"></div>";
	}
	public String buildJSLoader(String id) {
		return "var db"+id+" = new thispage.container(( {containerid: '"+id+"', templateString : dojo.cache('../content/db/template.html?id="+id+"')}).placeAt('"+id+"');";
	}
	public String getDjangoTemplate(DesignBlock db ) {
		return db.getMainContent();
	}

}
