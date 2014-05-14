package biz.webgate.xpages.thispage.renderer;

import java.util.List;
import java.util.Map;

import biz.webgate.xpages.thispage.DocStatus;
import biz.webgate.xpages.thispage.api.ContentSessionFacade;
import biz.webgate.xpages.thispage.content.Category;
import biz.webgate.xpages.thispage.content.CategoryStorageService;
import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.CategoryResult;
import biz.webgate.xpages.thispage.rest.IResult;

public class FlatNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final FlatNavigationStrategyRenderer m_Renderer = new FlatNavigationStrategyRenderer();

	public IResult<?> buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		System.out.println("NAVREND");
		CategoryResult result = new CategoryResult();
		try {
			Category cat = CategoryStorageService.getInstance().getByDocKey(page.getCategoryKey(), DocStatus.PUBLISHED);
			List<Category> categories = CategoryStorageService.getInstance().getObjectsByForeignId(cat.getParentCategory(), ContentSessionFacade.LUP_CATEGORY_ONLINE_BY_PARENT_KEY);
			result.setElements(categories);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("nok");
		}
		return result;
	}

	public static FlatNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
