package com.mar.asynctask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView imageView;
	private Button button;
	private ProgressBar pb;
	private EditText et;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView)findViewById(R.id.imageView);
		button = (Button)findViewById(R.id.button1);
		pb = (ProgressBar)findViewById(R.id.progressbar);
		pb.setVisibility(View.INVISIBLE);
		et = (EditText)findViewById(R.id.editText);
		tv = (TextView)findViewById(R.id.textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@SuppressLint("NewApi")
	public void OnStart(View view){		
		
		if(et.getText().toString().isEmpty() == false){
		
			String website = et.getText().toString();
			GetImage getImage = new GetImage();
			getImage.execute(website);
			
		}else{
			tv.setText("The WebSite Can't be Null");
		}
			
		
	}
	
	public void OnClear(View view){
		
		imageView.setImageBitmap(null);
		tv.setText("");
	}
	
	private class GetImage extends AsyncTask {

		public GetImage() {

		super();

		// TODO Auto-generated constructor stub

		}

		@Override
		protected void onCancelled() {

		Log.i("czb", "onCancelled is running...");

		super.onCancelled();

		}

		@Override
		protected void onPostExecute(Object result) {

		/*

		* 此方法在主线程执行，任务执行的结果作为此方法的参数返回

		*/

		Log.i("czb", "onPostExecute is running...");

		Log.i("czb", "result == null ? " + (result == null));
		pb.setVisibility(View.INVISIBLE);
		imageView.setImageBitmap((Bitmap)result);
		
		super.onPostExecute(result);

		}

		@Override
		protected void onPreExecute() {

		/*

		* 执行预处理，它运行于UI线程，可以为后台任务做一些准备工作，比如绘制一个进度条控件

		*/

		Log.i("czb", "onPreExecute is running...");
		pb.setVisibility(View.VISIBLE);
		super.onPreExecute();

		}

		@Override
		protected void onProgressUpdate(Object... values) {

		/*

		* 此方法在主线程执行，用于显示任务执行的进度。

		*/

		Log.i("czb", "onProgressUpdate is running...");

		// 由publishProgress传递的值

		Log.i("czb", "values " + values[0]);
		
		super.onProgressUpdate(values);

		}

		@Override
		protected Object doInBackground(Object... params) {

		/*

		* 此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。

		* 在执行过程中可以调用publicProgress(Progress…)来更新任务的进度。

		*/

		Log.i("czb", "doInBackground is running...");

		try {

		Bitmap bitmap;

		HttpClient client = new DefaultHttpClient();

		// params[0]代表连接的url

		URI uri = URI.create((String) params[0]);

		HttpGet get = new HttpGet(uri);

		HttpResponse response = client.execute(get);

		HttpEntity entity = response.getEntity();

		long length = entity.getContentLength();

		Log.i("czb", " " + length);

		InputStream in = entity.getContent();

		if (in != null) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[128];

		int ch = -1;

		int count = 0;

		while ((ch = in.read(buf)) != -1) {

		baos.write(buf, 0, ch);

		count += ch;

		if (length > 0) {

		// 如果知道响应的长度，调用publishProgress()更新进度

		// onProgressUpdate读取进度

		publishProgress((int) ((count / (float) length) * 100));

		}

		// 为了在模拟器中清楚地看到进度，让线程休眠100ms

		//Thread.sleep(10);

		}

		//bitmap = BitmapFactory.decodeStream(in);
		bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),0,count);
		in.close();

		baos.close();

		return bitmap;

		}

		} catch (Exception e) {

		e.printStackTrace();

		}

		return null;

		}		
	}

}
