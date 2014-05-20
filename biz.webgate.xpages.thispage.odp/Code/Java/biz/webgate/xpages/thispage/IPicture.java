package biz.webgate.xpages.thispage;

import java.util.List;

import org.openntf.xpt.core.dss.binding.util.FileHelper;

public interface IPicture {

	public abstract String getTitle();

	public abstract String getAltText();

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract String getType();

	public abstract List<FileHelper> getFile();

}