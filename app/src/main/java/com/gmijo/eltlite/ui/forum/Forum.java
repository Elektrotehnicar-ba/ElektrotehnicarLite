package com.gmijo.eltlite.ui.forum;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import com.gmijo.eltlite.R;


public class Forum extends Fragment {
    ProgressBar progressBar;
    WebView webView_fom;
    TextView textView8, textView8a;
    Button button;
    ImageView imageView;

    private boolean mbErrorOccured = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fom = inflater.inflate(R.layout.fragment_forum, container, false);
        WebView webView = (WebView)fom.findViewById(R.id.webView_forum);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://forum.elektrotehnicar.ba");
        webView.setWebViewClient(new myWebViewClient());
        progressBar = fom.findViewById(R.id.progressBar8);
        webView_fom = fom.findViewById(R.id.webView_forum);
        textView8 = fom.findViewById(R.id.textView8);
        textView8a = fom.findViewById(R.id.textView8a);
        imageView = fom.findViewById(R.id.imageView8);
        button = fom.findViewById(R.id.button_osvjezi_forum);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!mbErrorOccured) {
                    return;
                }
                webView_fom.reload();
                mbErrorOccured = false;

            }
        });
        return fom;
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
                webView_fom.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView8.setVisibility(View.GONE);
                textView8a.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mbErrorOccured = true;
            webView_fom.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView8.setVisibility(View.VISIBLE);
            textView8a.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}