package com.boyko.demostorestate.model;


public class NetworkData implements ManagerOperable {

	
	private int networkType;
	private int subtype;
	private String typeName;
	private String subtypeName;
	private boolean isRoaming;
	private long time = System.currentTimeMillis();
	
	public NetworkData() {
	}
	
	@Override
	public long getId() {
		return 0;
	}
	
	@Override
	public String getText() {
		return typeName ;
	}
	
	public int getNetworkType() {
		return networkType;
	}

	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}

	public int getSubtype() {
		return subtype;
	}

	public void setSubtype(int subtype) {
		this.subtype = subtype;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSubtypeName() {
		return subtypeName;
	}

	public void setSubtypeName(String subtypeName) {
		this.subtypeName = subtypeName;
	}

	public boolean isRoaming() {
		return isRoaming;
	}

	public void setRoaming(boolean isRoaming) {
		this.isRoaming = isRoaming;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
}
