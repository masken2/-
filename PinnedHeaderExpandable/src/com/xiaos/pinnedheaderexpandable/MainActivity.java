package com.xiaos.pinnedheaderexpandable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaos.adapter.NameAdapter;
import com.xiaos.adapter.utils.PinyinComparator;
import com.xiaos.adapter.utils.PinyinLetterHelper;
import com.xiaos.view.SideBar;

public class MainActivity extends Activity {

	private ListView mListView;
	private SideBar indexBar;
	private WindowManager mWindowManager;
	private TextView mDialogText;
	private View head;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list);
		mListView = (ListView) this.findViewById(R.id.list);
		indexBar = (SideBar) findViewById(R.id.sideBar);
		mDialogText = (TextView) LayoutInflater.from(this).inflate(R.layout.list_position, null);
		
		head = LayoutInflater.from(this).inflate(R.layout.head, null);
		mListView.addHeaderView(head);
		mDialogText.setVisibility(View.INVISIBLE);
		
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
		mWindowManager.addView(mDialogText, lp);
		
		indexBar.setTextView(mDialogText);
		// ��ʼ������
		List<Content> list = new ArrayList<Content>();
		getDataIntoList(list);
		// ����a-z��������
		Collections.sort(list, new PinyinComparator());
		// ʵ�����Զ�������������
		NameAdapter adapter = new NameAdapter(this, list);
		// ΪlistView��������
		mListView.setAdapter(adapter);
		// ����SideBar��ListView����ʵ�ֵ��a-z������һ�����ж�λ
		indexBar.setListView(mListView);
	}

	/**
	 * ��������䵽list��
	 * 
	 * @param list
	 */
	public void getDataIntoList(List<Content> list) {
		String[] array = getResources().getStringArray(R.array.default_smiley_texts);
		for (int i = 0; i < array.length; i++) {
			String string = PinyinLetterHelper.getPinyinFirstLetter(array[i]);
			list.add(new Content(string, array[i]));
		}
	}

}
