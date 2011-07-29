package mn.aug;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class KataAsynchronicityActivity extends Activity {

	protected static final Void Void = null;
	ImageView imageView;
	private String TAG = this.getClass().getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btn_load_webview);
		imageView = (ImageView) findViewById(R.id.main_imageview);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AsyncNetworkCall anc = new AsyncNetworkCall();
				anc.execute(Void);
			}
		});
	}
	
	class AsyncNetworkCall extends AsyncTask<Void, Void, Void> {

		Drawable d;
		
		@Override
		protected void onPreExecute() {
			showDialog(1);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			d = getImage("http://www.google.com/intl/en_com/images/srpr/logo2w.png"); 
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			imageView.setImageDrawable(d);
			removeDialog(1);
		}
		
	}

	private Drawable getImage(String url) {
		try {
			// simulate slow network traffic..
			Thread.sleep(2000);
			
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;

		} catch (Exception ex) {
			Log.d(TAG, "Exception getting image: " + ex.getMessage());
			return null;
		}
	}
	
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setMessage("Loading Image...");
		return dialog;
	}

}