package biz.webgate.xpages.thispage.renderer;

import java.util.Map;

import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.Result;

public class HTMLStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final HTMLStrategyRenderer m_Renderer = new HTMLStrategyRenderer();

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		throw new UnsupportedOperationException("HTMLStrategieRendere has no JSON Call");
	}

	public static HTMLStrategyRenderer getInstance() {
		return m_Renderer;
	}

	@Override
	public String buildHTMLTag(DesignBlock db, Page page) {
		return db.getMainContent();
	}

}
