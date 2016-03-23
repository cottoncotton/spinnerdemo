package com.spinerwindow;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.spinnerdemo.R;
import com.spinerwindow.AbstractSpinerAdapter.IOnItemSelectListener;

public class SpinerPopWindow extends PopupWindow implements OnItemClickListener{

	private Context mContext;
	private ListView mListView;
	private NormalSpinerAdapter mAdapter;
	private IOnItemSelectListener mItemSelectListener;
	
	public SpinerPopWindow(Context context)
	{
		super(context);
		mContext = context;
		init();
	}
	
	public void setItemListener(IOnItemSelectListener listener){
		mItemSelectListener = listener;
	}

	
	private void init()
	{
		View view = LayoutInflater.from(mContext).inflate(R.layout.spiner_window_layout, null);
		setContentView(view);		
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		
		setFocusable(true);
    	ColorDrawable dw = new ColorDrawable(0x00);
		setBackgroundDrawable(dw);
	
		
		mListView = (ListView) view.findViewById(R.id.listview);
		

		mAdapter = new NormalSpinerAdapter(mContext);	
		mListView.setAdapter(mAdapter);	
		mListView.setOnItemClickListener(this);
	}
	
	
	public void refreshData(List<String> list, int selIndex)
	{
		if (list != null && selIndex  != -1)
		{
//			int siz=list.size();
//			String s=list.get(siz-1);
			//判断是否登录 已经登录显示个人中心
//			if(s.contains("/")){
//				String s1="";
//				if(isLogin){
//					s1=s.substring(3);
//				}else{
//					s1=s.substring(0, 2);
//				}
//				List<String> list1=new ArrayList<String>();
//				for(int x=0;x<list.size()-1;x++){
//					list1.add(list.get(x));
//				}
//				list1.add(s1);
//				mAdapter.refreshData(list1, selIndex,type);
//			}else{ 
				mAdapter.refreshData(list, selIndex);
//			}
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		dismiss();
		if (mItemSelectListener != null){
			mItemSelectListener.onItemClick(pos);
		}
	}


	
}
