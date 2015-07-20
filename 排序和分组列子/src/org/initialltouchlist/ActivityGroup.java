package org.initialltouchlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.listsildedel.R;

public class ActivityGroup extends Activity {
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
	tv_top.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ActivityGroup.this,MainActivity.class);
				startActivity(intent);
			}
		});
		tv_down.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent = new Intent(ActivityGroup.this,GroupLayout.class);
				startActivity(intent);
			}
		});
	}
}
