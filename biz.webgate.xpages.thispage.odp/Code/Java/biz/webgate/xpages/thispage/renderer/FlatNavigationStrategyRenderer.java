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

public class FlatNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final FlatNavigationStrategyRenderer m_Renderer = new FlatNavigationStrategyRenderer();

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		System.out.println("NAVREND");
		Result result = new Result();
		try {
			Category cat = ContentSessionFacade.get().getCategoryByDocKey(page.getCategoryKey());
			List<Category> categories = ContentSessionFacade.get().getSubCategoryByParent(cat.getParentCategory());
			System.out.println(categories.size());
			List<IElements> elements = new LinkedList<IElements>();
			elements.addAll(categories);
			System.out.println(elements.size());
			result.setElements(categories);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("nok");
			//result.setElements(new LinkedList<IElements>());
		}
		return result;
	}

	public static FlatNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
