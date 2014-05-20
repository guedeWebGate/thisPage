package biz.webgate.xpages.thispage.services;

public enum IDDockKeyExtractor {
	INSTANCE;

	public String getIDDocKey(String pathElement) {
		int nPos = -1;
		if (pathElement.contains("/")) {
			nPos = pathElement.indexOf("/");
		} else {
			nPos = pathElement.lastIndexOf(".");
		}
		if (nPos == -1) {
			return pathElement;
		}
		return pathElement.substring(0, nPos);
	}

}
