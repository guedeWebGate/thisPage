package biz.webgate.xpages.thispage.rest;

import java.util.List;

public interface IResult<T> {

	public abstract String getStatus();

	public abstract List<T> getElements();
	public abstract void setElements(List<T> elements);

}