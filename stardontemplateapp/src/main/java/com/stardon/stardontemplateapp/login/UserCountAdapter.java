package com.stardon.stardontemplateapp.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

import java.util.List;

public class UserCountAdapter extends BaseAdapter {
	private Context mContext;
	private List<UserCount> data;

	public UserCountAdapter() {
		super();
	}

	public UserCountAdapter(Context context, List<UserCount> data) {
		super();
		mContext = context;
		this.data = data;
	}

	public int getCount() {
		// TODO 自动生成的方法存根
		return data.size();
	}

	@Override
	public UserCount getItem(int position) {
		// TODO 自动生成的方法存根
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_usercount, null);
			holder = new ViewHolder();
			holder.userName = (TextView) convertView.findViewById(R.id.username);
//			holder.mImage = (ImageView) convertView.findViewById(R.id.img_menu_main);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 先获取数据源中对应position的对象
		holder.userName.setText(data.get(position).getUserName().toString());
//		holder.mImage.setImageResource(data.get(position).getImage());
		return convertView;
	}

	class ViewHolder {
		TextView userName;
	}
}
