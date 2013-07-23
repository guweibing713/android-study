package com.mar.developtrain_c01_s05;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void Save(){
		
		Context context = getApplicationContext();
		SharedPreferences sharedPref = context.getSharedPreferences(
		        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		EditText editText = (EditText) findViewById(R.id.edittext_login);	
		
		String userName = editText.getText().toString();
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(getString(R.string.saveName), userName);
		editor.commit();
	}
	
	
	public void GetRecord(){
		
		Context context = getApplicationContext();
		
		SharedPreferences sharedPref = context.getSharedPreferences(
		        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		String defaultValue = getResources().getString(R.string.saveName);
		String saveName = sharedPref.getString(getString(R.string.saveName), defaultValue);
		
		EditText editText = (EditText) findViewById(R.id.textview_display);	
		
		editText.setText(saveName);
		
	}
	
}
