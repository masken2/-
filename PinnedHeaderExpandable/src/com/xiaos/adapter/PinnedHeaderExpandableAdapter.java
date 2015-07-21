package com.xiaos.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xiaos.pinnedheaderexpandable.R;
import com.xiaos.view.PinnedHeaderExpandableListView;
import com.xiaos.view.PinnedHeaderExpandableListView.HeaderAdapter;

/**
 * 分组列表的item适配器
 * @author Administrator
 *
 */
public class PinnedHeaderExpandableAdapter extends BaseExpandableListAdapter
		implements HeaderAdapter {
	private String[][] childrenData;
	private String[] groupData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	private String url[];
	private DisplayImageOptions options;

	public PinnedHeaderExpandableAdapter(String[][] childrenData,
			String[] groupData, Context context, String url[],
			PinnedHeaderExpandableListView listView) {
		this.groupData = groupData;
		this.childrenData = childrenData;
		this.url = url;
		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(5)).build();
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childrenData[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;
		view = convertView;
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.child, null);
			holder.imageView = (ImageView) view.findViewById(R.id.groupIcon);
			holder.textView = (TextView) view.findViewById(R.id.childto);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
//	ImageLoader.getInstance().displayImage(url[childPosition],
//				holder.imageView, options);
//		ImageLoader.getInstance().displayImage(url[childPosition],
//				holder.imageView);

		TextView text = (TextView) view.findViewById(R.id.childto);
		text.setText(childrenData[groupPosition][childPosition]);
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childrenData[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return groupData.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = null;
		if (convertView != null) {
			view = convertView;
		} else {
			view = createGroupView();
		}

		ImageView iv = (ImageView) view.findViewById(R.id.groupIcon);

		if (isExpanded) {
			iv.setImageResource(R.drawable.btn_browser2);
		} else {
			iv.setImageResource(R.drawable.btn_browser);
		}

		TextView text = (TextView) view.findViewById(R.id.groupto);
		text.setText(groupData[groupPosition]);
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}


	private View createGroupView() {
		return inflater.inflate(R.layout.group, null);
	}

	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition,
			int childPosition, int alpha) {
		String groupData = this.groupData[groupPosition];
		((TextView) header.findViewById(R.id.groupto)).setText(groupData);

	}

	private SparseIntArray groupStatusMap = new SparseIntArray();

	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.keyAt(groupPosition) >= 0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}

	static class ViewHolder {
		ImageView imageView;
		TextView textView;
	}
}
