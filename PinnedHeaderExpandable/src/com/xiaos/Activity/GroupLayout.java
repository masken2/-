package com.xiaos.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.xiaos.adapter.PinnedHeaderExpandableAdapter;
import com.xiaos.pinnedheaderexpandable.R;
import com.xiaos.values.ImageUrl;
import com.xiaos.view.PinnedHeaderExpandableListView;

/**
 * 仿QQ分组界面
 * @author Administrator
 *
 */
public class GroupLayout extends Activity {
	protected static final String TAG = "GroupLayout";
	private PinnedHeaderExpandableListView explistview;
	private String[][] childrenData = new String[10][10];
	private String[] groupData = new String[10];
	private int expandFlag = -1;// 控制列表的展开
	private PinnedHeaderExpandableAdapter adapter;
	private String[] url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		initView();
		initData();
	}

	/**
	 * 初始化VIEW
	 */
	private void initView() {
		   url=ImageUrl.IMAGES;
		explistview = (PinnedHeaderExpandableListView) findViewById(R.id.explistview);
		explistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GroupLayout.this,
						ShowOtherActivity.class);
				startActivity(intent);
			Log.i(TAG, "你点击了我，为甚么没有反应");
			}

		});
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		for (int i = 0; i < 10; i++) {
			groupData[i] = "分组" + i;
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				childrenData[i][j] = "好友" + i + "-" + j;
			}
		}
		// 设置悬浮头部VIEW
		explistview.setHeaderView(getLayoutInflater().inflate(
				R.layout.group_head, explistview, false));
		adapter = new PinnedHeaderExpandableAdapter(childrenData, groupData,
				getApplicationContext(),url, explistview);
		explistview.setAdapter(adapter);

		// 设置单个分组展开
		// explistview.setOnGroupClickListener(new GroupClickListener());
	}

	class GroupClickListener implements OnGroupClickListener {
		@Override
		public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
			if (expandFlag == -1) {
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			} else if (expandFlag == groupPosition) {
				explistview.collapseGroup(expandFlag);
				expandFlag = -1;
			} else {
				explistview.collapseGroup(expandFlag);
				// 展开被选的group
				explistview.expandGroup(groupPosition);
				// 设置被选中的group置于顶端
				explistview.setSelectedGroup(groupPosition);
				expandFlag = groupPosition;
			}
			return true;
		}
	}
}
