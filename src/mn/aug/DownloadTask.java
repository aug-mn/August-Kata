package mn.aug;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

class DownloadTask extends AsyncTask<String, Void, Drawable> {

	String TAG = this.getClass().getName();

	/**
	 * 
	 */
	private ImageView imageView;
	private ProgressBar progressBar;

	/**
	 * @param kataAsynchronicityActivity
	 */
	DownloadTask(ImageView imageView, ProgressBar progressBar) {
		this.imageView = imageView;
		this.progressBar = progressBar;
	}
	
	public void setViews(ImageView imageView, ProgressBar progressBar) {
		this.imageView = imageView;
		this.progressBar = progressBar;
		
		if (getStatus() == Status.RUNNING) {
			this.progressBar.setVisibility(View.VISIBLE);
		}
		else {
			this.progressBar.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onPreExecute() {
		progressBar.setVisibility(View.VISIBLE);
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
		progressBar.setVisibility(View.GONE);
	}

	Drawable getImage(String url) {
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
}