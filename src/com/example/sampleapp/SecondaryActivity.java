package com.example.sampleapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


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
		
		setContentView(R.layout.second_activity);
		
		Intent intent = getIntent();
		Bundle bundle;
		if( intent != null && (bundle = intent.getExtras()) != null ){
			 String value = bundle.getString(VALUE_KEY);
			 this.setTitle(value);
		}
		
		Button btn = (Button)findViewById(R.id.btnShow);
		btn.setOnClickListener(new OnClickListener(){

			
			
			@Override
			public void onClick(View v) {
				displaySharedPreferences();
			}
			
		});
		
		//Get Handle for Spinner
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.add("Item 1");
		adapter.add("Item 2");
		adapter.add("Item 3");
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		
	}

	private void displaySharedPreferences() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		String username = prefs.getString("username", "Default NickName");
		String passw = prefs.getString("password", "Default Password");
		boolean checkBox = prefs.getBoolean("checkBox", false);
		String listPrefs = prefs.getString("listpref", "Default list prefs");

		StringBuilder builder = new StringBuilder();
		builder.append("Username: " + username + "\n");
		builder.append("Password: " + passw + "\n");
		builder.append("Keep me logged in: " + String.valueOf(checkBox) + "\n");
		builder.append("List preference: " + listPrefs);

		Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
	}

}
