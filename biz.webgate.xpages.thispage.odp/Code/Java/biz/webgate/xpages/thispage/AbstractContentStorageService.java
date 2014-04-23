package biz.webgate.xpages.thispage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lotus.domino.Document;
import lotus.domino.View;

import org.openntf.xpt.core.dss.AbstractStorageService;
import org.openntf.xpt.core.dss.DominoStorageService;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public abstract class AbstractContentStorageService<T> extends AbstractStorageService<T> {


	
	public void publish(T Content) {
		try {
			View viwAllOnline = ExtLibUtil.getCurrentDatabase().getView("lupAllOnlineByDocKey");
			Document docPage = viwAllOnline.getDocumentByKey(((AbstractBase)Content).getDocKey(), true);
			if (docPage != null) {
				T contentOnline = createObject();
				DominoStorageService.getInstance().getObjectWithDocument(contentOnline, docPage);
				docPage.recycle();
				viwAllOnline.recycle();
				((AbstractBase)contentOnline).setStatus(DocStatus.ARCHIVED);
				save(contentOnline);
			} else {
				T contentOffline = getByDocKey(((AbstractBase)Content).getDocKey(), DocStatus.OFFLINE);
				if (contentOffline != null) {
					((AbstractBase)contentOffline).setStatus(DocStatus.ARCHIVED);
					save(contentOffline);
				}
			}
			((AbstractBase)Content).setStatus(DocStatus.PUBLISHED);
			save(Content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public T getByDocKey(String strDocKey, DocStatus... status) {
		T contentCurrent = null;
		List<T> lstContent = getObjectsByForeignId(strDocKey, "lupAllEditableByDocKey");
		if (lstContent == null || lstContent.size() == 0) {
			return null;
		}
		if (status != null) {
			List<DocStatus> ds = Arrays.asList(status);
			for (Iterator<T> itContent = lstContent.iterator(); itContent.hasNext();) {
				T contentCheck = itContent.next();
				if (!ds.contains(((AbstractBase)contentCheck).getStatus())) {
					itContent.remove();
				}
			}
		}
		if (lstContent.size() > 0) {
			contentCurrent = lstContent.get(0);
		}
		return contentCurrent;
	}
}
