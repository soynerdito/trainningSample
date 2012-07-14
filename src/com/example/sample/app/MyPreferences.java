package com.example.sample.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.soynerdito.sample.app.R;

public class MyPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

}
