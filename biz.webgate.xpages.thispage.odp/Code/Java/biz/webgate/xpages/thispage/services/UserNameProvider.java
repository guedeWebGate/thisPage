package biz.webgate.xpages.thispage.services;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public enum UserNameProvider {
	INSTANCE;
	public String getUserName() {
		String strRC = null;
		try {
			strRC = (String) ExtLibUtil.getCurrentSession().getEffectiveUserName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRC;
	}
}
