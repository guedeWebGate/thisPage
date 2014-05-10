package biz.webgate.xpages.thispage.renderer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import biz.webgate.xpages.thispage.api.ContentSessionFacade;
import biz.webgate.xpages.thispage.content.Category;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.IElements;
import biz.webgate.xpages.thispage.rest.Result;

public class ContentNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final ContentNavigationStrategyRenderer m_Renderer = new ContentNavigationStrategyRenderer();

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		Result result = new Result();
		List<Page> pages = ContentSessionFacade.get().getPagesByCategory(page.getCategoryKey());
		List<IElements> elements = new LinkedList<IElements>();
		elements.addAll(pages);
		result.setElements(elements);
		return result;
	}

	public static ContentNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
