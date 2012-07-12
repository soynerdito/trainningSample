package com.example.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
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

		//Initialize Database
		mdb = new DBHelper(this.getApplicationContext());
		
		ImageButton ibtnAdd = (ImageButton)findViewById(R.id.ibtnAdd);
		ibtnAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickAdd( );	
			}
			
		});
		
		mDeviceListView = (ListView)findViewById(R.id.listView1);

	}
	
	public void refreshDeviceList(){
		//Get DB Cursor
		Device device = new Device();
		Cursor cursor = mdb.get(device);
		
		//Create Cursor and set to List
		mDeviceListView.setAdapter(new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2,cursor, 
				new String[] {device.mDescription.mName, device.mDeviceID.mName},
				new int[]{ android.R.id.text1, android.R.id.text2}, 0 ));
	}
	
	
	@Override
	public void onBackPressed() {	
		super.onBackPressed();
		System.exit(0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if( requestCode == COMMAND && resultCode == RESULT_OK ){
			//Do Stuff			
            int iOption = data.getIntExtra("OPTION", -1);
            System.out.println("Option " + String.valueOf(iOption));
            switch( iOption ){
            	case 0:
            	{
            		Device device = new Device();
            		device.mDescription.setValue( "Telefono" );
            		device.mDeviceID.setValue( "Dev1" );
            		mdb.insert(device);
            		//trigger a refresh
            		refreshDeviceList();
            	}
            	break;
            	case 1:
            	{
            	}
            	break;
            }
		}else{
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	protected void onClickAdd( ) {
		Intent intent = new Intent(this, OptionMenu.class );		
		startActivityForResult(intent, COMMAND );
		//overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
		overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
	}

	

}
