package com.peony.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public final class SqlUtil {
	public static final int LIMITE_WORD_NUM = 300;

	public static List<String> madedString(Collection<String> ids, int num) {
		List<String> results = new ArrayList<String>();
		if ((ids != null) && (ids.size() > 0)) {
			List<String> idsList = new ArrayList<String>();
			idsList.addAll(ids);
			int len = ids.size();
			Iterator<String> iterator = ids.iterator();
			for (int i = 0; i < len; i += num) {
				StringBuffer sbuffer = new StringBuffer();

				int j = i;
				while ((iterator.hasNext()) && (j < i + num)) {
					String id = (String) iterator.next();
					if (j == i)
						sbuffer.append("'").append(id).append("'");
					else {
						sbuffer.append(",'").append(id).append("'");
					}
					j++;
				}
				results.add(sbuffer.toString());
			}
		}
		return results;
	}

}