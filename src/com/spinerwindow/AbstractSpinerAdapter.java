package com.spinerwindow;

import java.util.ArrayList;
import java.util.List;

import com.example.spinnerdemo.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public abstract class AbstractSpinerAdapter<T> extends BaseAdapter {

	public static interface IOnItemSelectListener {
		public void onItemClick(int pos);
	};

	private Context mContext;
	private List<T> mObjects = new ArrayList<T>();
	private int mSelectItem = 0;
//	private int mType = 0;
	private LayoutInflater mInflater;

	public AbstractSpinerAdapter(Context context) {
		init(context);
	}

	public void refreshData(List<T> objects, int selIndex/**, int type**/) {
		mObjects = objects;
		if (selIndex < 0) {
			selIndex = 0;
		}
		if (selIndex >= mObjects.size()) {
			selIndex = mObjects.size() - 1;
		}

		mSelectItem = selIndex;
//		mType = type;
	}

	private void init(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		return mObjects.size();
	}

	@Override
	public Object getItem(int pos) {
		return mObjects.get(pos).toString();
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.spiner_item_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.mSpiner_item_layout=(LinearLayout) convertView.findViewById(R.id.spiner_item_layout);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.imageView);
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.textView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String item = (String) getItem(pos);
		viewHolder.mTextView.setTextColor(mContext.getResources().getColor(
				R.color.black));
		viewHolder.mTextView.setText(item);
		viewHolder.mSpiner_item_layout.setGravity(Gravity.CENTER);
//		if (mType == 2) {
//			viewHolder.mImageView.setVisibility(View.VISIBLE);
//			if(!"".equals(item)&&item!=null){
//				if("添加设备".equals(item)){
//					viewHolder.mImageView.setImageResource(R.drawable.add_1);
//				}else if("摇一摇".equals(item)){
//					viewHolder.mImageView.setImageResource(R.drawable.shake_1);
//				}else if("关于".equals(item)){
//					viewHolder.mImageView.setImageResource(R.drawable.about_1);
//				}else if("帮助".equals(item)){
//					viewHolder.mImageView.setImageResource(R.drawable.help_menu_1);
//				}else if("登录".equals(item)||"个人中心".equals(item)){
//					viewHolder.mImageView.setImageResource(R.drawable.login_1);
//				}
//			}
//		} else if (mType == 1) {
//			viewHolder.mImageView.setVisibility(View.GONE);
//			viewHolder.mSpiner_item_layout.setGravity(Gravity.CENTER);
//		}

		return convertView;
	}

	public static class ViewHolder {
		private LinearLayout mSpiner_item_layout;
		public ImageView mImageView;
		public TextView mTextView;
	}

}
