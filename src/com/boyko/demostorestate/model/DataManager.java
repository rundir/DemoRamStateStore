package com.boyko.demostorestate.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.boyko.demostorestate.Logger;

public class DataManager {

	private static final String TAG = "DataManager";

	private static DataManager dataManager;
	
	private Set<NetworkData> data;
	private Set<ManagerOperableListener> listeners;
	
	private boolean isRestored;
	
	private DataManager() {
		data = new HashSet<NetworkData>();
		listeners = new HashSet<ManagerOperableListener>();
		Logger.d(TAG, "data has created: " + this);
	}
	
	public synchronized static DataManager getInstance(){
		if(dataManager == null)
			dataManager = new DataManager();
		return dataManager;
	} 
	
	public void addManagerOperable(NetworkData object){
		Logger.d(TAG, "addManagerOperable: " + object.getTypeName());
		data.add(object);
		notifyDataSetChanged();
	}

	public boolean isRestored() {
		return isRestored;
	}
	
	public void setRestored(boolean isRestored) {
		this.isRestored = isRestored;
	}
	
	public List<ManagerOperable> getDataAsList() {
		List<ManagerOperable> list = new ArrayList<ManagerOperable>();
		list.addAll(data);
		return list ;
	}
	
	public Set<NetworkData> getData() {
		return data;
	}
	
	public void setData(Set<NetworkData> data) {
		this.data = data;
	}
	
	public void notifyDataSetChanged() {
		for (ManagerOperableListener listener : listeners) {
			listener.onChange();
		}
	}
	
	public void addListener(ManagerOperableListener listener) {
		listeners.add(listener);
	}

	public void removeListener(ManagerOperableListener listener) {
		listeners.remove(listener);
	}
	
}
