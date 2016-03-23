package com.example.spinnerdemo;

import java.util.ArrayList;
import java.util.List;

import com.spinerwindow.SpinerPopWindow;
import com.spinerwindow.AbstractSpinerAdapter.IOnItemSelectListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private EditText et;
	private ImageView iv_show;
	public List<String> tag = new ArrayList<String>();
	public SpinerPopWindow mPopWindow;
	private RelativeLayout parent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parent=(RelativeLayout) findViewById(R.id.parent);
		et = (EditText) findViewById(R.id.et);
		iv_show=(ImageView) findViewById(R.id.iv_show);
		tag.add("北京");
		tag.add("上海");
		tag.add("重庆");
		tag.add("天津");
		mPopWindow = new SpinerPopWindow(this);
		mPopWindow.refreshData(tag, 0);
		mPopWindow.setItemListener(new IOnItemSelectListener() {
			@Override
			public void onItemClick(int pos) {
				// TODO Auto-generated method stub
				selectMenu(pos);
			}
		});
		iv_show.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_show:
			showTagView();
			break;

		default:
			break;
		}
	}
	private void showTagView(){
		mPopWindow.setWidth(et.getWidth());
		mPopWindow.showAsDropDown(et);
//		mPopWindow.showAtLocation(parent, Gravity.BOTTOM|Gravity.LEFT, 10, mll_menu.getHeight());
	}
	private void selectMenu(int pos) {
		List<String> list = null;
		list = tag;
		String s1 = list.get(pos);
		et.setText(s1);

	}
}
