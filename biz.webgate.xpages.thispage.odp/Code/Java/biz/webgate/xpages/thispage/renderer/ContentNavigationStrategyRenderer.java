package biz.webgate.xpages.thispage.renderer;

import java.util.List;
import java.util.Map;

import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.content.PageStorageService;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.ContentResult;
import biz.webgate.xpages.thispage.rest.IResult;

public class ContentNavigationStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final ContentNavigationStrategyRenderer m_Renderer = new ContentNavigationStrategyRenderer();

	public IResult<?> buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		ContentResult result = new ContentResult();
		List<Page> pages = PageStorageService.getInstance().getObjectsByForeignId(page.getCategoryKey(), "lupPagesOnlineByCatKey");
		result.setElements(pages);
		return result;
	}

	public static ContentNavigationStrategyRenderer getInstance() {
		return m_Renderer;
	}

}
