package com.boyko.demostorestate;

import android.app.IntentService;
import android.content.Intent;
import android.net.NetworkInfo;

import com.boyko.demostorestate.model.DataManager;
import com.boyko.demostorestate.model.NetworkData;

public class SampleIntentService extends IntentService {

	public SampleIntentService() {
		super("SampleIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Logger.d("SampleIntentService", "onHandleIntent");

		if(intent!=null && intent.getParcelableExtra(NetworkConnectivityReceiver.NETWORK)!=null){
		
			DataManager dataManager = DataManager.getInstance();
			NetworkInfo network = intent.getParcelableExtra(NetworkConnectivityReceiver.NETWORK);
			NetworkData networkData = new NetworkData();
			networkData.setNetworkType(network.getType());
			networkData.setRoaming(network.isRoaming());
			networkData.setSubtype(network.getSubtype());
			networkData.setSubtypeName(network.getSubtypeName());
			networkData.setTypeName(network.getTypeName());
			dataManager.addManagerOperable(networkData);
		}
		
	}

}
