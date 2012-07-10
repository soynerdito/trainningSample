package com.example.sampleapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SecondaryActivity extends Activity {
	private static final String VALUE_KEY = "VALUE_KEY";
	
	public static Bundle createBundle(String value){
		Bundle bundle = new Bundle();
		bundle.putString(VALUE_KEY, value);
		
		return bundle;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle bundle;
		if( intent != null && (bundle = intent.getExtras()) != null ){
			 String value = bundle.getString(VALUE_KEY);
			 this.setTitle(value);
		}
	}

}
