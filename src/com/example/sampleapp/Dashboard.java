package com.example.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Dashboard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		ImageButton ibtnAdd = (ImageButton)findViewById(R.id.ibtnAdd);
		ibtnAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickAdd( );	
			}
			
		});
	}
	
	@Override
	public void onBackPressed() {	
		super.onBackPressed();
		System.exit(0);
	}

	protected void onClickAdd( ) {
		Intent intent = new Intent(this, OptionMenu.class );		
		startActivity(intent);
		//overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
		overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
	}

	

}
