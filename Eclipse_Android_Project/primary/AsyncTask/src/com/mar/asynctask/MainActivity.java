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

		* �˷��������߳�ִ�У�����ִ�еĽ����Ϊ�˷����Ĳ�������

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

		* ִ��Ԥ������������UI�̣߳�����Ϊ��̨������һЩ׼���������������һ���������ؼ�

		*/

		Log.i("czb", "onPreExecute is running...");
		pb.setVisibility(View.VISIBLE);
		super.onPreExecute();

		}

		@Override
		protected void onProgressUpdate(Object... values) {

		/*

		* �˷��������߳�ִ�У�������ʾ����ִ�еĽ��ȡ�

		*/

		Log.i("czb", "onProgressUpdate is running...");

		// ��publishProgress���ݵ�ֵ

		Log.i("czb", "values " + values[0]);
		
		super.onProgressUpdate(values);

		}

		@Override
		protected Object doInBackground(Object... params) {

		/*

		* �˷����ں�̨�߳�ִ�У�����������Ҫ������ͨ����Ҫ�ϳ���ʱ�䡣

		* ��ִ�й����п��Ե���publicProgress(Progress��)����������Ľ��ȡ�

		*/

		Log.i("czb", "doInBackground is running...");

		try {

		Bitmap bitmap;

		HttpClient client = new DefaultHttpClient();

		// params[0]�������ӵ�url

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

		// ���֪����Ӧ�ĳ��ȣ�����publishProgress()���½���

		// onProgressUpdate��ȡ����

		publishProgress((int) ((count / (float) length) * 100));

		}

		// Ϊ����ģ����������ؿ������ȣ����߳�����100ms

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
