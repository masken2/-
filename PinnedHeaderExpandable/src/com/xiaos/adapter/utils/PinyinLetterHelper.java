package com.xiaos.adapter.utils;

import net.sourceforge.pinyin4j.PinyinHelper;


public class PinyinLetterHelper {
	/**
	 * ��ȡ������ƴ������ĸ���Ҵ�д��
	 * 
	 * @param name
	 *            �����ַ���������ͷ����Ӣ�����ֺ��»���
	 * @return
	 */
	public static String getPinyinFirstLetter(String name) {
		String firstletter = "#";
		if (name != null) {
			// ת��ǰ���������Ļ���Ӣ��ֱ��ȡ��ͷ�ַ�
			char initial = name.charAt(0);
			System.out.print(initial + "  ---From:  " + name);
			if (!((initial >= 'A' && initial <= 'Z')
					|| (initial >= 'a' && initial <= 'z')
					|| (initial >= '0' && initial <= '9') || initial == '_')) {
				firstletter = (PinyinHelper.toHanyuPinyinStringArray(initial))[0];
			} else {
				firstletter = initial + "";
			}

			firstletter = firstletter.toUpperCase();
			System.out.print("  -- " + firstletter);
			char[] firstChar = new char[1];
			firstChar[0] = firstletter.charAt(0);
			firstletter = new String(firstChar);
			System.out.println("    --" + firstletter);
		}
		return firstletter;
	}
}
