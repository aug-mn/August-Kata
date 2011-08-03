package mn.aug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class KataAsynchronicityActivity extends Activity {

	ImageView imageView;
	
	ProgressBar mProgressBar;

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
				(new DownloadTask(imageView, mProgressBar)).execute("http://www.google.com/intl/en_com/images/srpr/logo2w.png");
			}
		});
	}

	
}