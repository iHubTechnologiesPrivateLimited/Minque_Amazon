package com.qurix.ihubtechnologies.minque.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.respose.DisplayCodeResponse;
import com.qurix.ihubtechnologies.minque.network.ServiceGenarator;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupActivity extends BaseActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.wifiname)
    TextView wifiname;
    @BindView(R.id.uuidcode)
    TextView uuidcode;

    Request request;
    OkSse okSse = new OkSse();
    ServerSentEvent sse;
    private ServerSentEvent.Listener listner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);
        text1.setTypeface(thin);
        text2.setTypeface(thin);
        wifiname.setTypeface(thin);
        uuidcode.setTypeface(bold);

        // get and set wifiname
        String ssid = getWifiSsid(this);
        ssid = ssid.replaceAll("^\"|\"$", "");
        wifiname.setText(ssid);

        //get mac id
        String macId = getMacAddress();
        Log.d("macid", macId);
        if (internetStatus.isNetworkAvailable()) {
            sendMacAddress(macId);
        } else {
            Toast.makeText(SetupActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }


    }

    private String getWifiSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
            return ssid;
        } else {
            return "No wifi found";
        }

    }

    private String getMacAddress() {

        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)) {
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac == null) {
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    private void sendMacAddress(String macId) {

        Call<DisplayCodeResponse> call = client.getDisplayCode(macId);
        call.enqueue(new Callback<DisplayCodeResponse>() {
            @Override
            public void onResponse(Call<DisplayCodeResponse> call, final Response<DisplayCodeResponse> response) {
//
                sessionManager.setDisplyCode(response.body().getDisplayCode());
                String s = response.body().getDisplayCode();
                s = s.replaceAll(".", "$0 ");
                uuidcode.setText(s);
                Log.d("displayyyyyyyy", response.body().getDisplayCode());

                InitializeSse(response.body().getDisplayCode());


            }

            @Override
            public void onFailure(Call<DisplayCodeResponse> call, Throwable t) {
                Log.d("madhuE", t.getMessage());

            }
        });
    }

    private void InitializeSse(String displayCode) {
        request = new Request.Builder().url(ServiceGenarator.BASE_API + "displayScreenEvent/" + displayCode).build();

        listner = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, okhttp3.Response response) {

            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                try {
                    Log.d("madhusse", message);
                    JSONObject obj = new JSONObject(message);
                    String display_url = obj.getString("url");
                    sessionManager.setDisplayurl(display_url);

                    Intent i = new Intent(SetupActivity.this, WebviewActivity.class);
                    i.putExtra("webUrl", display_url);
                    startActivity(i);
                    sse.close();
                    finish();

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
            public boolean onRetryError(ServerSentEvent sse, Throwable throwable, okhttp3.Response response) {
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
}
