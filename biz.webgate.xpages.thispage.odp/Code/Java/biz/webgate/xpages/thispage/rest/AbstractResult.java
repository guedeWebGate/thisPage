package biz.webgate.xpages.thispage.rest;

import java.io.Serializable;

import org.openntf.xpt.core.json.annotations.JSONEntity;

public abstract class AbstractResult<T> implements Serializable, IResult<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JSONEntity(jsonproperty = "status")
	private String m_Status;

	public void setStatus(String status) {
		m_Status = status;
	}

	/* (non-Javadoc)
	 * @see biz.webgate.xpages.thispage.rest.IResult#getStatus()
	 */
	public String getStatus() {
		return m_Status;
	}


}
