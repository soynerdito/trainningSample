package com.example.sampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.data.DBHelper;
import com.example.data.Device;

public class Dashboard extends Activity {

	private ListView mDeviceListView;
	private DBHelper mdb;

	public final static int COMMAND = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		// Initialize Database
		mdb = new DBHelper(this.getApplicationContext());

		ImageButton ibtnAdd = (ImageButton) findViewById(R.id.ibtnAdd);
		ibtnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickAdd();
			}

		});

		mDeviceListView = (ListView) findViewById(R.id.listView1);

		refreshDeviceList();
	}

	public void refreshDeviceList() {
		// Get DB Cursor
		Device device = new Device();
		Cursor cursor = mdb.get(device);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, new String[] {
						device.mDescription.mName, device.mDeviceID.mName },
				new int[] { android.R.id.text1, android.R.id.text2 }, 0);

		// Create Cursor and set to List
		mDeviceListView.setAdapter(adapter);
		registerForContextMenu(mDeviceListView);

	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		MenuItem delete = menu.add("Delete");
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				Device device = new Device();
				device.mRecID.setValue((int) info.id);
				mdb.delete(device);
				// scriptdb.deleteScript(info.id);
				// adapter.getCursor().requery();
				refreshDeviceList();
				return true;
			}
		});

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == COMMAND && resultCode == RESULT_OK) {
			// Do Stuff
			int iOption = data.getIntExtra("OPTION", -1);
			System.out.println("Option " + String.valueOf(iOption));
			switch (iOption) {
			case 0: {
				Device device = new Device();
				device.mDescription.setValue("Telefono");
				device.mDeviceID.setValue("Dev4");
				mdb.insert(device);
				// trigger a refresh
				refreshDeviceList();
			}
				break;
			case 1: {
			}
				break;
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	protected void onClickAdd() {
		/*
		 * Intent intent = new Intent(this, OptionMenu.class );
		 * startActivityForResult(intent, COMMAND );
		 * //overridePendingTransition( android.R.anim.fade_in,
		 * android.R.anim.fade_out ); overridePendingTransition(
		 * R.anim.slide_in_up, R.anim.slide_out_up );
		 */
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Create New Device");

		// Set an EditText view to get user input
		//final EditText input = new EditText(this);
		LayoutInflater inflater = this.getLayoutInflater();
		
		alert.setView(inflater.inflate(R.layout.new_device, null) );

		alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				EditText edDeviceName = (EditText) ((AlertDialog)dialog).findViewById(R.id.etDeviceName);
				EditText edDescription = (EditText)((AlertDialog)dialog).findViewById(R.id.etDescription);
				System.out.println(edDescription.getText().toString());
				
				Device newDevice = new Device();
				newDevice.mDescription.setValue( edDescription.getText().toString() );
				newDevice.mDeviceID.setValue(edDescription.getText().toString() );
				
				mdb.insert(newDevice);
				refreshDeviceList();
				
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}

}