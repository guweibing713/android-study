package com.marguerite.thirdapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private Button	sendMsgBtn;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		textView = (TextView)findViewById(R.id.textViewId);
		sendMsgBtn = (Button)findViewById(R.id.sendMsgId);
		
		handler = new MyHandler();
				
	}
	
	public void SendMsg(View v)
	{
		Thread t = new NetworkThread();
		t.start();
	}
	
	class MyHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			String s = (String)msg.obj;
			
			textView.setText(s);
		}	
		
	}
	
	class NetworkThread extends Thread{

		@Override
		public void run() {

			try{
				Thread.sleep(2 * 1000);
			}catch(InterruptedException e){			
				e.printStackTrace();
			}
			
			String s = "Network data";
			
			Message msg = handler.obtainMessage();
			msg.obj = s;
			handler.sendMessage(msg);
		}	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
