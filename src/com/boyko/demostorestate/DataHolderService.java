package com.boyko.demostorestate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.boyko.demostorestate.model.DataManager;
import com.boyko.demostorestate.model.NetworkData;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class DataHolderService extends Service {

	public static final String EXTRA_NETWORK_STATUS = "EXTRA_NETWORK_STATUS";
	public static final String ACTION_UPDATE_NETWORK_STATE = "ACTION_UPDATE_NETWORK_STATE";
	private static final String FILENAME = Environment.getExternalStorageDirectory() + "/" + "data_model";
	private static final String TAG = "DataHolderService";

	private DataManager dataManager;

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder();
	}

	public class LocalBinder extends Binder {
		public DataHolderService getService() {
			return DataHolderService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Logger.d(TAG, "onCreate");
		dataManager = DataManager.getInstance();
		

		try {
			Gson gson = new Gson();
			DataManager dataManager = gson.fromJson(new JsonReader(new FileReader(FILENAME)), DataManager.class);
			dataManager.setRestored(true);
			this.dataManager.setData(dataManager.getData());
			Logger.d(TAG, "data has restored");
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {

		}

		NetworkData networkData = new NetworkData();
		if (networkData.getTypeName() == null) {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo network = cm.getActiveNetworkInfo();
			networkData.setNetworkType(network.getType());
			networkData.setRoaming(network.isRoaming());
			networkData.setSubtype(network.getSubtype());
			networkData.setSubtypeName(network.getSubtypeName());
			networkData.setTypeName(network.getTypeName());
			dataManager.addManagerOperable(networkData);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Logger.d(TAG, "onDestroy");
		try {
			Gson gson = new Gson();
			String s = gson.toJson(dataManager);
			FileOutputStream outputStream;
			File f = new File(FILENAME);
			outputStream = new FileOutputStream(f);
			outputStream.write(s.getBytes());
			outputStream.close();
			Logger.d(TAG, "data has saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return Service.START_STICKY;
	}

}
