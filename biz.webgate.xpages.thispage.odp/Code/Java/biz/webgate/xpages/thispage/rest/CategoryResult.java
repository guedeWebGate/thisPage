package biz.webgate.xpages.thispage.rest;

import java.util.List;

import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

import biz.webgate.xpages.thispage.content.Category;

@JSONObject
public class CategoryResult extends AbstractResult<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONEntity(jsonproperty = "values")
	private List<Category> m_Elements;

	public void setElements(List<Category> elements) {
		m_Elements = elements;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.rest.IResult#getElements()
	 */
	public List<Category> getElements() {
		return m_Elements;
	}
}
