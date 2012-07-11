package com.example.sampleapp;
import android.app.Activity;
import android.os.Bundle;


public class OptionMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_menu);
	}

	@Override
	protected void onDestroy() {
		overridePendingTransition( android.R.anim.fade_out, android.R.anim.fade_in );
		super.onDestroy();
		
	}

}
