package biz.webgate.xpages.thispage;

import java.util.Map;

import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.Result;

public interface IDesignBlockStrategyRenderer {

	public String getDjangoTemplate(DesignBlock db);
	public Result buildJSONResult(Map<String,String> values);
	public String buildHTMLTag(String id);
	public String buildJSLoader(String id);
}
