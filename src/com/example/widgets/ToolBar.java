package com.example.widgets;

import com.soynerdito.sample.app.R;
import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class ToolBar extends RelativeLayout {

	public ToolBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub		
		LayoutInflater li = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.toolbar, null);
	}

}
