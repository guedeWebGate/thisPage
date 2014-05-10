package biz.webgate.xpages.thispage;

public enum DocStatus {
	DRAFT, PUBLISHED, ARCHIVED, OFFLINE;

	public DocStatus getRegularNextDocStatus() {
		if (this == DRAFT) {
			return DocStatus.PUBLISHED;
		}
		if (this == PUBLISHED) {
			return DocStatus.ARCHIVED;
		}
		if (this == OFFLINE) {
			return DocStatus.PUBLISHED;
		}
		return null;
	}
}
