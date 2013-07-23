package com.mar.developtrain_c01_s06;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Activity start...");
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void ClickButton(){
		
		//EditText editText = (EditText) findViewById(R.id.editText);
		//String str = "tel" + editText.toString();
		System.out.println("Tel is :");
		Uri number = Uri.parse("tel:10010");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		
		// Verify it resolves
		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
		boolean isIntentSafe = activities.size() > 0;
		  
		// Start an activity if it's safe
		if (isIntentSafe) {
		    startActivity(callIntent);
		}
	}

}
