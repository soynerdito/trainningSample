package com.example.app;


import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onBackPressed() {		
		super.onBackPressed();
		System.exit(0);		
	}

	
}
