package com.gmijo.eltlite.ui.pocetna;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.gmijo.eltlite.R;


public class PocetnaFragment extends Fragment {

    ProgressBar progressBar;
    WebView webView_pos;
    TextView textView4, textView5;
    Button button;
    ImageView imageView;
    private boolean mbErrorOccured = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View poc = inflater.inflate(R.layout.fragment_pocenta, container, false);
        WebView webView = (WebView)poc.findViewById(R.id.webView_pocetna);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://elektrotehnicar.ba");

        webView.setWebViewClient(new myWebViewClient());
        progressBar = poc.findViewById(R.id.progressBar);
        webView_pos = poc.findViewById(R.id.webView_pocetna);
        textView4 = poc.findViewById(R.id.textView4);
        textView5 = poc.findViewById(R.id.textView5);
        imageView = poc.findViewById(R.id.imageView4);
        button = poc.findViewById(R.id.button_osvjezi_pocetna);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    if (!mbErrorOccured) {
                        return;
                    }
                    webView_pos.reload();
                    mbErrorOccured = false;

            }
        });
        return poc;
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
                webView_pos.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }
            super.onPageFinished(view, url);
            webView_pos.loadUrl("javascript:(function() { " + "document.getElementsByTagName('header')[0].style.display = 'none'; " + "})()");
            webView_pos.loadUrl("javascript:(function() { " + "document.getElementsByTagName('button')[0].style.display = 'none'; " + "})()");
            webView_pos.loadUrl("javascript:(function() { " + "document.getElementsByTagName('button')[1].style.display = 'none'; " + "})()");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mbErrorOccured = true;
            webView_pos.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
    }

