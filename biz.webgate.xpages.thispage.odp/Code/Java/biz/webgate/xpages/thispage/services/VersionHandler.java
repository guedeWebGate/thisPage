package biz.webgate.xpages.thispage.services;

import com.ibm.xsp.extlib.util.ExtLibUtil;

import lotus.domino.View;
import lotus.domino.ViewEntryCollection;

public enum VersionHandler {
	INSTANCE;

	public int getNewVersion(String docKey) {
		int nResult = -1;
		try {
			View lupVersion = ExtLibUtil.getCurrentDatabase().getView("lupVersion");
			ViewEntryCollection vecVersion = lupVersion.getAllEntriesByKey(docKey, true);
			if (vecVersion != null) {
				Double nVersion = (Double) vecVersion.getFirstEntry().getColumnValues().elementAt(1);
				nResult = nVersion.intValue()+1;
			} else {
				nResult = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nResult;
	}
}
