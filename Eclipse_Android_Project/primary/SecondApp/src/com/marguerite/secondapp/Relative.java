package com.marguerite.secondapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Relative extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relative);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	public void login(View view)
	{
		Intent intent = new Intent(this, TimeAndDate.class);

	    startActivity(intent);
	}
	
	public void cancel(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);

	    startActivity(intent);
	}
}
