package com.mar.mysliding;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

public class MainActivity extends Activity {

	private SlidingMenu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.slide_menu);
    }
   
    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing() == false) {
            menu.showMenu();
        } else {
        	  logout();
            //super.onBackPressed();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void logout(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  
    	builder.setMessage("Are you sure you want to exit?")  
    	       .setCancelable(false)  
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
    	           public void onClick(DialogInterface dialog, int id) {  
    	                MainActivity.this.finish();  
    	           }  
    	       })  
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {  
    	           public void onClick(DialogInterface dialog, int id) {  
    	                dialog.cancel();  
    	           }  
    	       });  
    	builder.show();
	}

}
