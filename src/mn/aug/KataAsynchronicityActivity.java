package mn.aug;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class KataAsynchronicityActivity extends Activity {

	ImageView imageView;
	private String TAG = this.getClass().getName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(false);
		
		setContentView(R.layout.main);
		

		Button btn = (Button) findViewById(R.id.btn_load_webview);
		imageView = (ImageView) findViewById(R.id.main_imageview);
		btn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                new AsyncTask<String, Void, Drawable>() {
                    
                    @Override
                    protected void onPreExecute() {
                        setProgressBarIndeterminateVisibility(true);
                    }

                    @Override
                    protected void onProgressUpdate(Void... values) {
                    }

                    @Override
                    protected Drawable doInBackground(String... params) {
                        Drawable d = getImage(params[0]);
                        return d;
                    }

                    @Override
                    protected void onPostExecute(Drawable result) {
                        setProgressBarIndeterminateVisibility(false);
                        imageView.setImageDrawable(result);
                    }
                    
                }.execute("http://www.google.com/intl/en_com/images/srpr/logo2w.png");
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

		} catch (Exception ex) {
			Log.d(TAG, "Exception getting image: " + ex.getMessage());
			return null;
		}
	}

}