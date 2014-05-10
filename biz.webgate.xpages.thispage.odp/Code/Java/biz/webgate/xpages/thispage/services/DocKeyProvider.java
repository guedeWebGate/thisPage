package biz.webgate.xpages.thispage.services;

import java.util.UUID;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.extlib.util.ExtLibUtil;

public enum DocKeyProvider {
	INSTANCE;
	public String createDocKey() {
		String strRC = null;
		try {
			strRC = (String) ExtLibUtil.getCurrentSession().evaluate("@Unique").elementAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtil.isEmpty(strRC)) {
			strRC = UUID.randomUUID().toString();
		}
		return strRC;
	}
}
