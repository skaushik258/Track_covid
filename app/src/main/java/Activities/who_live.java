package Activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

public class who_live extends AppCompatActivity {

    WebView mWebView;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_live);

        getSupportActionBar().setTitle("Zones");

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("https://www.covidhotspots.in");

        mWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
    }
}
// https://www.covidhotspots.in

