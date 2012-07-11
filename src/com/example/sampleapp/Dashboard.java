package com.example.sampleapp;

import android.app.Activity;
import android.os.Bundle;

public class Dashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
	}
	
	@Override
	public void onBackPressed() {
		System.exit(0);
		// super.onBackPressed();
	}

}
