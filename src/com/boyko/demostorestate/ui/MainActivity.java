package com.boyko.demostorestate.ui;

import android.app.Activity;
import android.os.Bundle;

import com.boyko.demostorestate.Logger;
import com.boyko.demostorestate.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logger.d("MainActivity", "onCreate");
		
		setContentView(R.layout.activity_main);
		
		getFragmentManager().beginTransaction().add(R.id.body, new DataUiListFragment(), null).commit();
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

}
