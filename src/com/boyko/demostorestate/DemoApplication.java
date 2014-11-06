package com.boyko.demostorestate;

import android.app.Application;
import android.content.Intent;

public class DemoApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		Logger.d("DemoApplication", "onCreate");
		startService(new Intent(this, DataHolderService.class));

	}
}
