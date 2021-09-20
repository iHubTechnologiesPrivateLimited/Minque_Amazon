package com.qurix.ihubtechnologies.minque.utils;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.qurix.ihubtechnologies.minque.network.ServiceCalls;
import com.qurix.ihubtechnologies.minque.network.ServiceGenarator;


public class BaseActivity extends FragmentActivity {
    public ServiceCalls client;
    public Typeface bold, reg, thin;
    public SessionManager sessionManager;
    public InternetStatus internetStatus;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = ServiceGenarator.createService(ServiceCalls.class);
        internetStatus = new InternetStatus(BaseActivity.this);
        sessionManager = new SessionManager(BaseActivity.this);
        bold = Typeface.createFromAsset(getAssets(), "Proxima_Bold.otf");
        reg = Typeface.createFromAsset(getAssets(), "Proxima_Reg.ttf");
        thin = Typeface.createFromAsset(getAssets(), "Proxima_Thin.otf");

    }
}
