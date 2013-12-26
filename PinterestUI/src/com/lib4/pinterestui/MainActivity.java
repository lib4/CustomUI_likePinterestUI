package com.lib4.pinterestui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	PinterestUI mPinterestUI;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mPinterestUI	=	new PinterestUI(this);
		ScrollView mScrollView	=new ScrollView(this);
		mScrollView.addView(mPinterestUI);
		setContentView(mScrollView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		mPinterestUI.createLayout();
		
	}

}
