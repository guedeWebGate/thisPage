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

public class TreeNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final TreeNavigationStrategyRenderer m_Renderer = new TreeNavigationStrategyRenderer();

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		Result result = new Result();
		List<Category> categories = ContentSessionFacade.get().getCategoryTree();
		List<IElements> elements = new LinkedList<IElements>();
		elements.addAll(categories);
		result.setElements(elements);
		return result;
	}

	public static TreeNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
