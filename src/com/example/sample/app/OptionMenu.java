package com.example.sample.app;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.soynerdito.sample.app.R;


public class OptionMenu extends Activity implements OnItemClickListener {

	private ListView mOptionListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_menu);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.option_item, R.id.text1 );
		adapter.add("Create New Device");
		adapter.add("Create New Customer");		
		
		mOptionListView = (ListView)findViewById(R.id.listview1);
		mOptionListView.setAdapter(adapter);

		mOptionListView.setOnItemClickListener(this);		
		
	}

	@Override
	protected void onDestroy() {
		overridePendingTransition( android.R.anim.fade_out, android.R.anim.fade_in );
		super.onDestroy();
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Intent resultData = new Intent();
		resultData.putExtra("OPTION", position);
		setResult(Activity.RESULT_OK, resultData);
		finish();
	}

}
