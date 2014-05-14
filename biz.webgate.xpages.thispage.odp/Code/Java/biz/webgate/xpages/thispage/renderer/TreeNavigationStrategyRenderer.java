package biz.webgate.xpages.thispage.renderer;

import java.util.List;
import java.util.Map;

import biz.webgate.xpages.thispage.content.Category;
import biz.webgate.xpages.thispage.content.CategoryStorageService;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.CategoryResult;
import biz.webgate.xpages.thispage.rest.IResult;

public class TreeNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final TreeNavigationStrategyRenderer m_Renderer = new TreeNavigationStrategyRenderer();

	public IResult<?> buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		CategoryResult result = new CategoryResult();
		List<Category> categories = CategoryStorageService.getInstance().getCategoryTree();
		result.setElements(categories);
		return result;
	}

	public static TreeNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
