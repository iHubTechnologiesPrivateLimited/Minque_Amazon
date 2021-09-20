package com.qurix.ihubtechnologies.minque.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class MyWebViewFragment extends Fragment {
    @BindView(R.id.webView)
    WebView mywebview;
    Unbinder unbinder;
    List<PaneResponse.ListOfPlayListBean> listData;
    @BindView(R.id.progressBar_cyclic)
    ProgressBar progressBarCyclic;
    private Context mcontext;
   // private ProgressDialog progDailog;

//    @SuppressLint("ValidFragment")
//    public MyWebViewFragment(List<PaneResponse.ListOfPlayListBean> playList) {
//        this.listData = playList;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mywebview_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        //  progDailog = ProgressDialog.show(mcontext, "Loading", "Please wait...", true);
        //       ProgressDialog myDialog = new ProgressDialog(getActivity());
//        myDialog.setOwnerActivity(getActivity());
//        myDialog.show(mcontext, "Loading","Please wait...", true);
//        myDialog.setCancelable(false);
        progressBarCyclic.setVisibility(View.VISIBLE);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.getSettings().setLoadWithOverviewMode(true);
        mywebview.getSettings().setUseWideViewPort(true);
        mywebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mywebview.getSettings().setAllowFileAccess(true);
        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        //mywebview.loadUrl("https://razorpay.com/");
     //   mywebview.loadUrl("http://192.168.1.9:4200/doctorqueue");
    //    mywebview.loadUrl("http://139.59.17.219:4200/doctorqueue");
        //  mywebview.loadUrl("https://www.google.com");
       mywebview.loadUrl("http://qurix.ai/doctorqueue");
        mywebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //     progDailog.show();
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                try {
                    //   progDailog.dismiss();
//                    Activity activity = myDialog.getOwnerActivity();
//                    if( activity!=null && !activity.isFinishing()) {
//                        myDialog.dismiss();
//                    }
                    progressBarCyclic.setVisibility(View.GONE);
                    Log.d("madhu", "doneloding");
                } catch (Exception e) {

                }

            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                Log.d("madhu", String.valueOf(errorResponse.getData()));
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.d("madhu", error.toString());
            }

//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                // ignore ssl error
//                if (handler != null){
//                    handler.proceed();
//                } else {
//                    super.onReceivedSslError(view, null, error);
//                }
//            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(Context context) {
        mcontext = context;
        super.onAttach(context);

    }

}
