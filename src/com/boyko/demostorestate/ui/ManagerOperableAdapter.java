package com.boyko.demostorestate.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boyko.demostorestate.R;
import com.boyko.demostorestate.model.ManagerOperable;

public class ManagerOperableAdapter extends BaseAdapter {

	private List<ManagerOperable> data;
	private Context context;
	
	public ManagerOperableAdapter(Context context, List<ManagerOperable> data) {
		this.context = context;
		this.data = data;
	}
	
	@Override
	public int getCount() {
		return data==null? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data==null? null : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data==null? -1 : data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
		}
		TextView tw = (TextView) convertView.findViewById(R.id.tw);
		tw.setText(data.get(position).getText());
		
		return convertView;
	}

	public void setData(List<ManagerOperable> data) {
		this.data = data;
		notifyDataSetChanged();
	}

}
