package biz.webgate.xpages.thispage.renderer;

import java.util.Map;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.ibm.xsp.page.compiled.ExpressionEvaluatorImpl;

import biz.webgate.xpages.thispage.content.Page;
import biz.webgate.xpages.thispage.design.DesignBlock;
import biz.webgate.xpages.thispage.rest.Result;

public class SSJSStrategyRenderer extends AbstractDesignBlockStrategyRenderer {

	private static final SSJSStrategyRenderer m_Renderer = new SSJSStrategyRenderer();

	public Result buildJSONResult(DesignBlock db, Page page, Map<String, String> values) {
		throw new UnsupportedOperationException("SSJSStrategyRenderer has no JSON Call");
	}

	public static SSJSStrategyRenderer getInstance() {
		return m_Renderer;
	}

	@Override
	public String buildHTMLTag(DesignBlock db, Page page) {
		String ssjsCode = db.getMainContent();
		try {
			String valueExpr = "#{javascript:" + ssjsCode + "}";
			FacesContext fc = FacesContext.getCurrentInstance();
			ExpressionEvaluatorImpl evaluator = new ExpressionEvaluatorImpl(fc);
			ValueBinding vb = evaluator.createValueBinding(fc.getViewRoot(), valueExpr, null, null);
			return (String) vb.getValue(fc);
		} catch (Exception e) {
			throw new FacesException("Error in executing: " + ssjsCode, e);
		}
	}

}
