package com.marguerite.secondapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimeAndDate extends Activity {

	private TimePicker timePicker;
	private ProgressBar pbar1;
	private ProgressBar pbar2;
	private ProgressBar pbar3;
	private ProgressBar pbar4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
	    setContentView(R.layout.timeanddate);
	    
	    timePicker = (TimePicker)findViewById(R.id.timepId);
	    timePicker.setIs24HourView(true);
	    TimeListener ls = new TimeListener();
	    timePicker.setOnTimeChangedListener(ls);
	    
	    pbar1 = (ProgressBar)findViewById(R.id.progressbar1Id);
	    pbar2 = (ProgressBar)findViewById(R.id.progressbar2Id);
	    pbar3 = (ProgressBar)findViewById(R.id.progressbar3Id);
	    pbar4 = (ProgressBar)findViewById(R.id.progressbar4Id);
	    
	    pbar1.setMax(100);
	    pbar2.setMax(100);
	    pbar3.setMax(100);
	    pbar4.setMax(100);
	    
	}
	
	public void confirmTime(View view)
	{
		int hour = timePicker.getCurrentHour();
		int min  = timePicker.getCurrentMinute();
		
		TextView textView = (TextView)findViewById(R.id.timeId);
		String str = "CurrentTime is " + hour + ":" + min;
		textView.setText(str);
		
		pbar1.incrementProgressBy(2);
		pbar2.incrementProgressBy(5);
		pbar2.incrementSecondaryProgressBy(10);
		pbar3.incrementProgressBy(8);
		pbar4.incrementProgressBy(10);
		
		if(pbar1.getProgress() >= pbar1.getMax())
			pbar1.setProgress(0);
		
		if(pbar2.getProgress() >= pbar2.getMax())
		{	pbar2.setProgress(0);
			pbar2.setSecondaryProgress(20);
		}
		if(pbar3.getProgress() >= pbar3.getMax())
			pbar3.setProgress(0);
		
		if(pbar4.getProgress() >= pbar4.getMax())
			pbar4.setProgress(0);
		
	}

	class TimeListener implements OnTimeChangedListener
	{

		@Override
		public void onTimeChanged(TimePicker view, int hourofDay, int minute) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
