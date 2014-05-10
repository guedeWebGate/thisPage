package biz.webgate.xpages.thispage;

import biz.webgate.xpages.thispage.renderer.ContentNavigationStrategyRenderer;
import biz.webgate.xpages.thispage.renderer.FlatNavigationStrategyRenderer;
import biz.webgate.xpages.thispage.renderer.HTMLStrategyRenderer;
import biz.webgate.xpages.thispage.renderer.SSJSStrategyRenderer;
import biz.webgate.xpages.thispage.renderer.TreeNavigationStrategyRenderer;

public enum DesignBlockStrategie {
	LIST(null), LIST_OF_FILES(null), DOCUMENT_NAVIGATION(ContentNavigationStrategyRenderer.getInstance()), FLAT_NAVIGATION(
			FlatNavigationStrategyRenderer.getInstance()), TREE_NAVIGATION(TreeNavigationStrategyRenderer.getInstance()), SSJSCODE(
			SSJSStrategyRenderer.getInstance()), HTML(HTMLStrategyRenderer.getInstance());

	private IDesignBlockStrategyRenderer m_Rendere;

	private DesignBlockStrategie(IDesignBlockStrategyRenderer renderer) {
		m_Rendere = renderer;
	}

	public IDesignBlockStrategyRenderer getRenderer() {
		return m_Rendere;
	}
}
