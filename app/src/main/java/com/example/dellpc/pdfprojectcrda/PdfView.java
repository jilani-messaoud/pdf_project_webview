package com.example.dellpc.pdfprojectcrda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class PdfView extends AppCompatActivity {
WebView webView  ;
Bundle bundle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
    init();
    setView();
    }
    void init(){

        webView = findViewById(R.id.webpdf) ;
        bundle =getIntent().getExtras();
    }
    void setView()
    {
        final WebView webView = findViewById(R.id.webpdf);
        final ProgressBar progressBar = findViewById(R.id.progressBarpdf);
        progressBar.setVisibility(View.VISIBLE);
        String url = bundle.getString("lien") ;
                //"https://drive.google.com/file/d/1PCrU4iN7q0zCmsslrbuZ9vl1htlAmjcy/view";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                getSupportActionBar().setTitle("waiting ...");
                if (newProgress == 100) {

                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(webView.getTitle());
                }
            }
        });
        webView.loadUrl(url);
    }
}
