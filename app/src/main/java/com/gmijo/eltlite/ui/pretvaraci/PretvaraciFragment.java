package com.gmijo.eltlite.ui.pretvaraci;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gmijo.eltlite.R;
import com.gmijo.eltlite.ui.komponente.KomponenteFragment;

public class PretvaraciFragment extends Fragment {



    ProgressBar progressBar;
    WebView webView_pr;
    TextView textView6, textView6a;
    Button button;
    ImageView imageView;
    private boolean mbErrorOccured = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View pr = inflater.inflate(R.layout.fragment_pretvaraci, container, false);
        WebView webView = (WebView)pr.findViewById(R.id.webView_pretvaraci);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://pretvaraci.elektrotehnicar.ba");
        webView.setWebViewClient(new myWebViewClient());
        progressBar = pr.findViewById(R.id.progressBar6);
        webView_pr = pr.findViewById(R.id.webView_pretvaraci);
        textView6 = pr.findViewById(R.id.textView6);
        textView6a = pr.findViewById(R.id.textView6a);
        imageView = pr.findViewById(R.id.imageView6);
        button = pr.findViewById(R.id.button_osvjezi_pretvaraci);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!mbErrorOccured) {
                    return;
                }
                webView_pr.reload();
                mbErrorOccured = false;

            }
        });
        return pr;
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
                webView_pr.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                textView6a.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mbErrorOccured = true;
            webView_pr.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView6.setVisibility(View.VISIBLE);
            textView6a.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}