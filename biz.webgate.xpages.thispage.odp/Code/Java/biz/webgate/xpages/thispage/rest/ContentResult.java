package biz.webgate.xpages.thispage.rest;

import java.util.List;

import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

import biz.webgate.xpages.thispage.content.Page;

@JSONObject
public class ContentResult extends AbstractResult<Page> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONEntity(jsonproperty = "values")
	private List<Page> m_Elements;

	public List<Page> getElements() {
		return m_Elements;
	}

	public void setElements(List<Page> elements) {
		m_Elements = elements;
	}

}
