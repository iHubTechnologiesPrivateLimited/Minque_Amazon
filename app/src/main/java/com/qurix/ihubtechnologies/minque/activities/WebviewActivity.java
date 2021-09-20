package com.qurix.ihubtechnologies.minque.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.network.ServiceGenarator;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import okhttp3.Response;

public class WebviewActivity extends BaseActivity  {
    Bundle bundle;
    String webUrl;
    @BindView(R.id.webView)
    WebView mywebview;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBarCyclic;
    String display_url;
    Request request;
    OkSse okSse = new OkSse();
    ServerSentEvent sse;
    private ServerSentEvent.Listener listner;

    BroadcastReceiver myReciver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywebview_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            webUrl = bundle.getString("webUrl");
        } else {
            webUrl = sessionManager.getDisplayurl();
        }

        progressBarCyclic.setVisibility(View.VISIBLE);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setUseWideViewPort(true);
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mywebview.getSettings().setAllowFileAccess(true);
        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        //  mywebview.loadUrl("https://www.google.com");
        mywebview.loadUrl(webUrl);
        Log.d("weburllll", webUrl.toString());

        mywebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                try {

                    progressBarCyclic.setVisibility(View.GONE);
                    Log.d("madhu", "doneloding");
                } catch (Exception e) {

                }

            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            }


        });
        listenSse();
        myReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    if (isOnline(context)) {

                        //Toast.makeText(WebviewActivity.this, "Connected", Toast.LENGTH_SHORT).show();

                    } else {

                        //  clearHandlers();
                        sse.close();
                        //Toast.makeText(WebviewActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };

        registerNetworkBroadcast();
    }

    private void registerNetworkBroadcast() {
        registerReceiver(myReciver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void unregisterNetworkChanges() {
        try {
            unregisterReceiver(myReciver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    private boolean isOnline(Context context) {
        try {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private void listenSse() {
        request = new Request.Builder().url(ServiceGenarator.BASE_API + "displayScreenEvent/" + sessionManager.getDisplyCode()).build();
        listner = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, Response response) {

            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                try {
                    Log.d("madhusse", message);
                    JSONObject obj = new JSONObject(message);

                     display_url = obj.getString("url");
                    sessionManager.setDisplayurl(display_url);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mywebview.loadUrl(display_url);
                            progressBarCyclic.setVisibility(View.VISIBLE);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onComment(ServerSentEvent sse, String comment) {

            }

            @Override
            public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
                return false;
            }

            @Override
            public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
                return false;
            }

            @Override
            public void onClosed(ServerSentEvent sse) {

            }

            @Override
            public Request onPreRetry(ServerSentEvent sse, Request originalRequest) {
                return null;
            }
        };
        sse = okSse.newServerSentEvent(request, listner);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());

    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            sse.close();
            unregisterNetworkChanges();
        } catch (Exception e) {

        }


    }

}
