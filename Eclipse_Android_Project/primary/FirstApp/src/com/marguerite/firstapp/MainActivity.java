package com.marguerite.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	private CheckBox eatBox;
	private CheckBox sleepBox;
	private CheckBox dotaBox;
	
	private RadioGroup radioGroup;
	private RadioButton maleBtn;
	private RadioButton femaleBtn;
	
	private ImageView headImage;
	
	private TextView resultTV;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        eatBox 		= (CheckBox)findViewById(R.id.eatId);
        sleepBox 	= (CheckBox)findViewById(R.id.sleepId);
        dotaBox		= (CheckBox)findViewById(R.id.dotaId);
        
        radioGroup	= (RadioGroup)findViewById(R.id.radioGroupId);
        maleBtn		= (RadioButton)findViewById(R.id.maleRadioId);
        femaleBtn	= (RadioButton)findViewById(R.id.femaleRadioId);
        
        headImage	= (ImageView)findViewById(R.id.headImageId);
        
        resultTV	= (TextView)findViewById(R.id.result);
        
    /*    OnBoxClickListener listener = new OnBoxClickListener();
        eatBox.setOnClickListener(listener);
        sleepBox.setOnClickListener(listener);
        dotaBox.setOnClickListener(listener);*/
        
        CheckBoxListener listener = new CheckBoxListener();
        eatBox.setOnCheckedChangeListener(listener);
        sleepBox.setOnCheckedChangeListener(listener);
        dotaBox.setOnCheckedChangeListener(listener);
        
        RadioGroupListener rl = new RadioGroupListener();        
        radioGroup.setOnCheckedChangeListener(rl);
        
    }
    
    class RadioGroupListener implements android.widget.RadioGroup.OnCheckedChangeListener
    {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			String str = resultTV.getText().toString();
			if(str.indexOf("boy") == -1 && str.indexOf("girl") == -1)
			{
				str = str.replace("½Y¹û:", "½Y¹û:Hey boy, you like those: ");
			}
			if(checkedId == femaleBtn.getId())
			{
				System.out.print("choose girl");
				str = str.replaceAll("boy","girl");
				headImage.setImageResource(R.drawable.girl);
			}
			else
			{
				str = str.replaceAll("girl","boy");
				headImage.setImageResource(R.drawable.boy);
			}
			resultTV.setText(str);
		}
    	
    }
    
    class CheckBoxListener implements OnCheckedChangeListener
    {

		@Override
		public void onCheckedChanged(CompoundButton Cb, boolean isChecked) {
			// TODO Auto-generated method stub
			String str = resultTV.getText().toString();
			CheckBox box = (CheckBox)Cb;
			if(box.isChecked())
			{
				if(box.getId() == eatBox.getId())
				{
					str+="eat,";
				}
				else if(box.getId() == sleepBox.getId())
				{
					str+="sleep,";
				}
				else
				{
					str+="dota,";
				}
			}
			else
			{
				if(box.getId() == eatBox.getId())
				{
					str = str.replaceAll(",eat|eat,","");
				}
				else if(box.getId() == sleepBox.getId())
				{
					str = str.replaceAll(",sleep|sleep,","");
				}
				else
				{
					str = str.replaceAll(",dota|dota,","");
				}
			}
			resultTV.setText(str);
		}
    	
    }
    
 /*   class OnBoxClickListener implements OnClickListener
    {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String str = resultTV.getText().toString();

			CheckBox box = (CheckBox)view;
			if(box.isChecked())
			{
				if(box.getId() == eatBox.getId())
				{
					str+="eat";
				}
				else if(box.getId() == sleepBox.getId())
				{
					str+="sleep";
				}
				else
				{
					str+="dota";
				}
			}
			else
			{
				if(box.getId() == eatBox.getId())
				{
					str.split("eat");
				}
				else if(box.getId() == sleepBox.getId())
				{
					str.split("sleep");
				}
				else
				{
					str.split("dota");
				}
			}
			resultTV.setText(str);
		}    	
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
