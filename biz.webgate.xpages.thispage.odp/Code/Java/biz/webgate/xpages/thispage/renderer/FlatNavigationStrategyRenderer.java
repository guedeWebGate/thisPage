package biz.webgate.xpages.thispage.renderer;

import java.util.Collections;
import java.util.Comparator;
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

	private static final Comparator<Category> CAT_COMPARATOR = new Comparator<Category>() {
	
					public int compare(Category o1, Category o2) {
						return Double.valueOf(o1.getSortOrder()).compareTo(Double.valueOf(o2.getSortOrder()));
					}
				};
	private static final FlatNavigationStrategyRenderer m_Renderer = new FlatNavigationStrategyRenderer();

	public IResult<?> buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		CategoryResult result = new CategoryResult();
		try {
			Category cat = CategoryStorageService.getInstance().getByDocKey(page.getCategoryKey(), DocStatus.PUBLISHED);
			List<Category> categories = CategoryStorageService.getInstance().getObjectsByForeignId(cat.getParentCategory(),
					ContentSessionFacade.LUP_CATEGORY_ONLINE_BY_PARENT_KEY);
			for (Category catS : categories) {
				if (catS.equals(cat)) {
					catS.setSelected(true);
				}
			}
			Collections.sort(categories, CAT_COMPARATOR);
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
