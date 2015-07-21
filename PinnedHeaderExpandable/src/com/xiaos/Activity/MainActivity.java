package com.xiaos.Activity;

import com.xiaos.pinnedheaderexpandable.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 主界面
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private TextView tv_top, tv_down;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		tv_top = (TextView) findViewById(R.id.tv_top);
		tv_down = (TextView) findViewById(R.id.tv_down);
		tv_top.setOnClickListener(new OnClickListener() {// 跳转到字母检索排序界面

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						ListViewLayout.class);
				startActivity(intent);
			}
		});
		tv_down.setOnClickListener(new OnClickListener() {// 跳转到分组界面

			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, GroupLayout.class);
				startActivity(intent);
			}
		});
	}
}
