package com.example.sample.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.soynerdito.sample.app.R;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		TextView tvLink = (TextView) findViewById(R.id.tvLink);
	    tvLink.setMovementMethod(LinkMovementMethod.getInstance());

	    tvLink.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String url = "https://github.com/soynerdito/trainningSample";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
	    	
	    });
	}

}
