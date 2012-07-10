package com.example.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final int PICK_CONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnAction = (Button) this.findViewById(R.id.btnAction);
		btnAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onActionClicked();
			}

		});

		btnAction = (Button) this.findViewById(R.id.btnSelectContact);
		btnAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onGetContact();
			}

		});
	}

	protected void onActionClicked() {
		Bundle bundle = SecondaryActivity.createBundle("TITULO 2");

		Intent newIntent = new Intent(this, SecondaryActivity.class);
		newIntent.putExtras(bundle);
		startActivity(newIntent);

	}

	protected void onManageContact() {
		Intent intent = new Intent(Intent.ACTION_DEFAULT,
				ContactsContract.Contacts.CONTENT_URI);
		startActivity(intent);
	}

	protected void onGetContact() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, PICK_CONTACT);

	}

	@Override
	public void onBackPressed() {
		System.exit(0);
		// super.onBackPressed();
	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {
					String name = c
							.getString(c
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					setTitle(name);
				}
			}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem mItem = menu.add(0, 1, Menu.NONE, "Details");
		MenuItem mItem2 = menu.add(0, 2, Menu.NONE, "Preferences");
		MenuItem mItem3 = menu.add(0, 3, Menu.NONE, "Close");

		mItem.setIcon(android.R.drawable.ic_menu_info_details);
		mItem2.setIcon(android.R.drawable.ic_menu_preferences);
		mItem3.setIcon(android.R.drawable.ic_menu_close_clear_cancel);

		mItem2.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				onPreferences();
				return false;
			}

		});
		return true;
	}

	protected void onPreferences() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, MyPreferences.class);
		startActivity(intent);
	}

	private void displaySharedPreferences() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(MainActivity.this);

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
