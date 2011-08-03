package mn.aug;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class KataAsynchronicityActivity extends Activity {

	ImageView imageView;
	private String TAG = this.getClass().getName();
	
	private ProgressBar mProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btn = (Button) findViewById(R.id.btn_load_webview);
		imageView = (ImageView) findViewById(R.id.main_imageview);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mProgressBar.setVisibility(View.GONE);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				(new DownloadTask()).execute("http://www.google.com/intl/en_com/images/srpr/logo2w.png");
			}
		});
	}

	private Drawable getImage(String url) {
		try {
			// simulate slow network traffic..
			Thread.sleep(2000);

			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;

		}
		catch (Exception ex) {
			Log.d(TAG, "Exception getting image: " + ex.getMessage());
			return null;
		}
	}

	private class DownloadTask extends AsyncTask<String, Void, Drawable> {

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Drawable doInBackground(String... params) {
			return getImage(params[0]);
		}

		@Override
		protected void onPostExecute(Drawable result) {
			if (result != null) {
				imageView.setImageDrawable(result);
			}
			mProgressBar.setVisibility(View.GONE);
		}
	}
}