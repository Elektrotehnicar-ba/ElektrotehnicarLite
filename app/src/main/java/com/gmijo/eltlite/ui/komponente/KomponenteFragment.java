package com.gmijo.eltlite.ui.komponente;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gmijo.eltlite.R;
import com.gmijo.eltlite.ui.pocetna.PocetnaFragment;

public class KomponenteFragment extends Fragment {

    ProgressBar progressBar;
    WebView webView_kom;
    TextView textView7, textView7a;
    Button button;
    ImageView imageView;
    private boolean mbErrorOccured = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View kom = inflater.inflate(R.layout.fragment_komponente, container, false);
        WebView webView = (WebView)kom.findViewById(R.id.webView_komponente);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://komponente.elektrotehnicar.ba");
        webView.setWebViewClient(new myWebViewClient());
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(1);
        progressBar = kom.findViewById(R.id.progressBar7);
        webView_kom = kom.findViewById(R.id.webView_komponente);
        textView7 = kom.findViewById(R.id.textView7);
        textView7a = kom.findViewById(R.id.textView7a);
        imageView = kom.findViewById(R.id.imageView7);
        button = kom.findViewById(R.id.button_osvjezi_komponente);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!mbErrorOccured) {
                    return;
                }
                webView_kom.reload();
                mbErrorOccured = false;

            }
        });
        return kom;
    }
    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (mbErrorOccured == false ) {
                webView_kom.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView7.setVisibility(View.GONE);
                textView7a.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mbErrorOccured = true;
            webView_kom.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textView7a.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}