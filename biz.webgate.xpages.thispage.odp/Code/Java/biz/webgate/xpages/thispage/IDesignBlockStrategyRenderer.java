package biz.webgate.xpages.thispage;

import java.util.Map;

import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.Result;

public interface IDesignBlockStrategyRenderer {

	public String getDjangoTemplate(DesignBlock db, Page page );

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values);

	public String buildHTMLTag(DesignBlock db,Page page);

	public String buildJSLoader(DesignBlock db, Page page);

}
