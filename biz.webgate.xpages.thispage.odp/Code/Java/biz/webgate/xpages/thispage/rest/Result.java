package biz.webgate.xpages.thispage.rest;

import java.io.Serializable;
import java.util.List;

import org.openntf.xpt.core.json.annotations.JSONEntity;
import org.openntf.xpt.core.json.annotations.JSONObject;

@JSONObject()
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONEntity(jsonproperty = "status")
	private String m_Status;
	@JSONEntity(jsonproperty = "values")
	private List<IElements> m_Elements;

	public void setStatus(String status) {
		m_Status = status;
	}

	public String getStatus() {
		return m_Status;
	}

	public void setElements(List<IElements> elements) {
		m_Elements = elements;
	}

	public List<IElements> getElements() {
		return m_Elements;
	}
}
