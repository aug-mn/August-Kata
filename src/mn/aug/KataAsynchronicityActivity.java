package mn.aug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import mn.aug.R;

public class KataAsynchronicityActivity extends Activity {
	
	private WebView webView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
    Button btn = (Button) findViewById(R.id.btn_load_webview);
    webView = (WebView) findViewById(R.id.wv_page);
    btn.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			loadWebView("http://www.google.com");
		}
	});
}


    private void loadWebView(String url) {
    	webView.loadUrl(url);
	}


}