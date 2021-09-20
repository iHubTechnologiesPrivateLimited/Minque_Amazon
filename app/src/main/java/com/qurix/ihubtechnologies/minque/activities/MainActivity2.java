package com.qurix.ihubtechnologies.minque.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.fragments.DoctorsFragment;
import com.qurix.ihubtechnologies.minque.fragments.MyWebViewFragment;
import com.qurix.ihubtechnologies.minque.fragments.ImageFragment;
import com.qurix.ihubtechnologies.minque.fragments.ScrollTextFragment;
import com.qurix.ihubtechnologies.minque.fragments.QueueFragment;
import com.qurix.ihubtechnologies.minque.fragments.VideoFragment;
import com.qurix.ihubtechnologies.minque.model.SizeModel;
import com.qurix.ihubtechnologies.minque.model.request.PaneRequest;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;
import com.qurix.ihubtechnologies.minque.storage.DatabaseClient;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends BaseActivity {
    BroadcastReceiver myReciver;


    @BindView(R.id.id_paly_back0)
    FrameLayout idPalyBack0;
    @BindView(R.id.id_paly_back1)
    FrameLayout idPalyBack1;
    @BindView(R.id.id_paly_back2)
    FrameLayout idPalyBack2;
    @BindView(R.id.id_paly_back3)
    FrameLayout idPalyBack3;

    @BindView(R.id.id_paly_back4)
    FrameLayout idPalyBack4;
    @BindView(R.id.frame_root_layout)
    FrameLayout frameRootLayout;
    private int period = 5000;
    private boolean redirect = true;
    public List<DoctorsData> data = new ArrayList<>();

    final Handler handler = new Handler();
    final Handler handler2 = new Handler();

    int list_index = 0;


    final Request request = new Request.Builder().url("http://139.59.17.219:8080/AnkuraHospitals/rest/doctorConsultation/serverSideEvent?locationId=1").build();
    OkSse okSse = new OkSse();
    ServerSentEvent sse;
    private ServerSentEvent.Listener listner;

    final Request request2 = new Request.Builder().url("http://139.59.17.219:8080/AnkuraHospitals/rest/doctorConsultation/serverSideEventForDisplay?locationId=1").build();
    OkSse okSse2 = new OkSse();
    ServerSentEvent sse2;
    private ServerSentEvent.Listener listner2;

    int temp2;
    int temp4;
    String docName, pationtId, pationtName, pationtNum, appStatus, appTime, emergncy, docId;
    private boolean navigater = false;
    private ArrayList<DoctorsData> data2 = new ArrayList<>();
    private String eventStatus;
    private boolean redirect2 = true;
    FragmentTransaction transaction;





    int scr_height;
    int scr_width;
    private List<PaneResponse> localPanedatalist;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_full_pop);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ButterKnife.bind(this);

//        setMargins(idQueue, 0, 0, 330, 100);
//        setMargins(idPalyBack1, 950, 0, 0, 100);
//        setMargins(idPalyBack2, 0, 620, 0, 0);
//        setMargins(idPalyBack3, 680, 50, 230, 500);
        //    getDoctorsData();
//        intializeLisnter();

        getScreenSize();
        if (internetStatus.isNetworkAvailable()) {
            getPaneData();
        } else {
            Toast.makeText(MainActivity2.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
        intializeLisnterForPaneChanges();

        myReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    if (isOnline(context)) {
                        removePreviousdata();
                        clearHandlers();
                        getDoctorsData();
                        intializeLisnter();
                        Toast.makeText(MainActivity2.this, "Connected", Toast.LENGTH_SHORT).show();

                    } else {

                        //  clearHandlers();
                        sse.close();
                        Toast.makeText(MainActivity2.this, "No Internet connection", Toast.LENGTH_SHORT).show();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };
//        registerNetworkBroadcast();


//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 300);
//        params.gravity = Gravity.BOTTOM;
//        idPalyBack3.setLayoutParams(params);

    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        scr_height = displayMetrics.heightPixels;
        scr_width = displayMetrics.widthPixels;

        Log.d("madhusize", "height" + String.valueOf(scr_height) + "width" + String.valueOf(scr_width));
    }

    private void getPaneData() {

        Call<List<PaneResponse>> data = client.getPaneData(new PaneRequest(null, "74e6"));
        data.enqueue(new Callback<List<PaneResponse>>() {
            @Override
            public void onResponse(Call<List<PaneResponse>> call, Response<List<PaneResponse>> response) {
                Log.d("madhu1", "done");
               final List<PaneResponse> paneData = response.body();
//                new AsyncTask(){
//
//                    @Override
//                    protected Object doInBackground(Object[] objects) {
//                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().paneResponseDao().insertAll(paneData);
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Object o) {
//                        getAndSetLocalPaneData();
//                    }
//                }.execute();


                List<SizeModel> actualSizesList = new ArrayList<>();
                for (int i = 0; i < paneData.size(); i++) {
                    int x = paneData.get(i).getXAxis();
                    int y = paneData.get(i).getYAxis();
                    int hight = paneData.get(i).getHeight();
                    int width = paneData.get(i).getWidth();

                    int actualX = (scr_width * x) / 100;
                    int actualY = (scr_height * y) / 100;
                    int actualHight = (scr_height * hight) / 100;
                    int actualwidth = (scr_width * width) / 100;

                    actualSizesList.add(new SizeModel(actualX, actualY, actualHight, actualwidth));
                }
                Log.d("madhu", actualSizesList.toString());

                if (actualSizesList.size() == 1) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);

                    if (paneData.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());

                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                      //  registerNetworkBroadcast();
                        Log.d("madhusize1","inAndroidQue");


                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                } else if (actualSizesList.size() == 2) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);
                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), actualSizesList.get(1).getActualY(), 0, 0);

                    if (paneData.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                       // registerNetworkBroadcast();
                        Log.d("madhusize2","inAndroidQue");
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (paneData.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                     //   registerNetworkBroadcast();
                        Log.d("madhusize2","inAndroidQue");
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                } else if (actualSizesList.size() == 3) {

                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);
                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), actualSizesList.get(1).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(actualSizesList.get(2).getActualwidth(), actualSizesList.get(2).getActualHight());
                    idPalyBack2.setLayoutParams(params3);
                    setMargins(idPalyBack2, actualSizesList.get(2).getActualX(), actualSizesList.get(2).getActualY(), 0, 0);


                    if (paneData.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {

                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {

                        Log.d("madhusize3","inAndroidQue");
                      //  registerNetworkBroadcast();

                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("text")) {

                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("image")) {

                        setFragment(R.id.id_paly_back0, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("video")) {

                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (paneData.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                         //  registerNetworkBroadcast();
                        Log.d("madhusize3","inAndroidQue");
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());

                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment(paneData.get(1).getListOfPlayList()));

                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                    if (paneData.get(2).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back2, new MyWebViewFragment());
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                          // registerNetworkBroadcast();
                        Log.d("madhusize3","inAndroidQue");

                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back2, new ScrollTextFragment());
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back2, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back2, new VideoFragment());
                    }
                } else if (actualSizesList.size() == 4) {
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), 0, 0, actualSizesList.get(0).getActualY());
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);

                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), 0, 0, actualSizesList.get(1).getActualY());
                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);

                    setMargins(idPalyBack2, actualSizesList.get(2).getActualX(), 0, 0, actualSizesList.get(2).getActualY());
                    FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(actualSizesList.get(2).getActualwidth(), actualSizesList.get(2).getActualHight());
                    idPalyBack2.setLayoutParams(params3);

                    setMargins(idPalyBack3, actualSizesList.get(3).getActualX(), 0, 0, actualSizesList.get(3).getActualY());
                    FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(actualSizesList.get(3).getActualwidth(), actualSizesList.get(3).getActualHight());
                    idPalyBack3.setLayoutParams(params4);

                    if (paneData.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                     //   registerNetworkBroadcast();
                        Log.d("madhusize4","inAndroidQue");
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (paneData.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                       // registerNetworkBroadcast();
                        Log.d("madhusize4","inAndroidQue");
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                    if (paneData.get(2).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back2, new MyWebViewFragment());
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                      // registerNetworkBroadcast();
                        Log.d("madhusize4","inAndroidQue");
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back2, new ScrollTextFragment());
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back2, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(2).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back2, new VideoFragment());
                    }

                    if (paneData.get(3).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back3, new MyWebViewFragment());
                    } else if (paneData.get(3).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                       // registerNetworkBroadcast();
                        Log.d("madhusize4","inAndroidQue");
                    } else if (paneData.get(3).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back3, new ScrollTextFragment());
                    } else if (paneData.get(3).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back3, new ImageFragment(paneData.get(1).getListOfPlayList()));
                    } else if (paneData.get(3).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back3, new VideoFragment());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<PaneResponse>> call, Throwable t) {
                Log.d("madhu_er", t.getMessage());

            }

        });


    }

    private void getAndSetLocalPaneData() {
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                localPanedatalist = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().paneResponseDao().getAll();

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                List<SizeModel> actualSizesList = new ArrayList<>();
                for (int i = 0; i < localPanedatalist.size(); i++) {
                    int x = localPanedatalist.get(i).getXAxis();
                    int y = localPanedatalist.get(i).getYAxis();
                    int hight = localPanedatalist.get(i).getHeight();
                    int width = localPanedatalist.get(i).getWidth();

                    int actualX = (scr_width * x) / 100;
                    int actualY = (scr_height * y) / 100;
                    int actualHight = (scr_height * hight) / 100;
                    int actualwidth = (scr_width * width) / 100;

                    actualSizesList.add(new SizeModel(actualX, actualY, actualHight, actualwidth));
                }

                Log.d("madhu", actualSizesList.toString());
                Log.d("madhuc", String.valueOf(actualSizesList.size()));

                if (actualSizesList.size() == 1) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);

                    if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();


                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                       // setFragment(R.id.id_paly_back0, new ImageFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                } else if (actualSizesList.size() == 2) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);
                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), actualSizesList.get(1).getActualY(), 0, 0);

                    if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                } else if (actualSizesList.size() == 3) {
                    Log.d("madhuP","size 3");
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), actualSizesList.get(0).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);
                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), actualSizesList.get(1).getActualY(), 0, 0);


                    FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(actualSizesList.get(2).getActualwidth(), actualSizesList.get(2).getActualHight());
                    idPalyBack2.setLayoutParams(params3);
                    setMargins(idPalyBack2, actualSizesList.get(2).getActualX(), actualSizesList.get(2).getActualY(), 0, 0);


                    if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        Log.d("madhuP","in web");
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                        Log.d("madhuP","in android");

                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                           registerNetworkBroadcast();

                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());

                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment());

                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                    if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back2, new MyWebViewFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                           registerNetworkBroadcast();

                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back2, new ScrollTextFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back2, new ImageFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back2, new VideoFragment());
                    }
                } else if (actualSizesList.size() == 4) {
                    setMargins(idPalyBack0, actualSizesList.get(0).getActualX(), 0, 0, actualSizesList.get(0).getActualY());
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(actualSizesList.get(0).getActualwidth(), actualSizesList.get(0).getActualHight());
                    idPalyBack0.setLayoutParams(params);

                    setMargins(idPalyBack1, actualSizesList.get(1).getActualX(), 0, 0, actualSizesList.get(1).getActualY());
                    FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(actualSizesList.get(1).getActualwidth(), actualSizesList.get(1).getActualHight());
                    idPalyBack1.setLayoutParams(params2);

                    setMargins(idPalyBack2, actualSizesList.get(2).getActualX(), 0, 0, actualSizesList.get(2).getActualY());
                    FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(actualSizesList.get(2).getActualwidth(), actualSizesList.get(2).getActualHight());
                    idPalyBack2.setLayoutParams(params3);

                    setMargins(idPalyBack3, actualSizesList.get(3).getActualX(), 0, 0, actualSizesList.get(3).getActualY());
                    FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(actualSizesList.get(3).getActualwidth(), actualSizesList.get(3).getActualHight());
                    idPalyBack3.setLayoutParams(params4);

                    if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back0, new MyWebViewFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back0, new ScrollTextFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back0, new ImageFragment());
                    } else if (localPanedatalist.get(0).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back0, new VideoFragment());
                    }

                    if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back1, new MyWebViewFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back1, new ScrollTextFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back1, new ImageFragment());
                    } else if (localPanedatalist.get(1).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back1, new VideoFragment());
                    }


                    if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back2, new MyWebViewFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back2, new ScrollTextFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back2, new ImageFragment());
                    } else if (localPanedatalist.get(2).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back2, new VideoFragment());
                    }

                    if (localPanedatalist.get(3).getPlaylistType().equalsIgnoreCase("WebQueue")) {
                        setFragment(R.id.id_paly_back3, new MyWebViewFragment());
                    } else if (localPanedatalist.get(3).getPlaylistType().equalsIgnoreCase("AndriodQueue")) {
                        registerNetworkBroadcast();
                    } else if (localPanedatalist.get(3).getPlaylistType().equalsIgnoreCase("text")) {
                        setFragment(R.id.id_paly_back3, new ScrollTextFragment());
                    } else if (localPanedatalist.get(3).getPlaylistType().equalsIgnoreCase("image")) {
                        setFragment(R.id.id_paly_back3, new ImageFragment());
                    } else if (localPanedatalist.get(3).getPlaylistType().equalsIgnoreCase("video")) {
                        setFragment(R.id.id_paly_back3, new VideoFragment());
                    }
                }
            }
        }.execute();
    }


    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


//    private boolean isOnline(Context context) {
//        try {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            //should check null because in airplane mode it will be null
//            return (netInfo != null && netInfo.isConnected());
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public Boolean isOnline(Context context) {
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

    private void intializeLisnterForPaneChanges() {
        listner2 = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, okhttp3.Response response) {

            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                Log.d("madhupaneevent", message);
//                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
//                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                }
//                clearHandlers();
//                unregisterNetworkChanges();
//                getPaneData();
                finish();
                startActivity(getIntent());

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
        sse2 = okSse2.newServerSentEvent(request2, listner2);
    }

    private void intializeLisnter() {

        listner = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, okhttp3.Response response) {

            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, final String message) {

                try {
                    Log.d("madhulis", message);
                    JSONObject obj = new JSONObject(message);
                    eventStatus = obj.getString("eventStatus");
                    JSONObject appobj = obj.getJSONObject("apptment");
                    docName = appobj.getString("apptDoctorName");
                    docId = appobj.getString("doctorId");


                    pationtId = appobj.getString("apptPatientId");
                    pationtName = appobj.getString("apptPatientName");
                    pationtNum = appobj.getString("apptPatientMobileNo");
                    appStatus = appobj.getString("apptStatus");
                    appTime = appobj.getString("apptBookedTime");
                    emergncy = appobj.getString("emergency");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (eventStatus.equalsIgnoreCase("CheckIn")) {
                    new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {

                            DoctorsData doctorsData = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().getSingleProduct(docId);

                            DoctorsData.DcDoctorAppointmentDisplaysBean appointmentData = new DoctorsData.DcDoctorAppointmentDisplaysBean();
                            appointmentData.setApptPatientId(pationtId);
                            appointmentData.setApptPatientName(pationtName);
                            appointmentData.setApptPatientMobileNo(pationtNum);
                            appointmentData.setApptStatus(appStatus);
                            appointmentData.setApptBookedTime(appTime);
                            appointmentData.setEmergency(emergncy);

                            //    doctorsData.getDcDoctorAppointmentDisplays().add(new DoctorsData.DcDoctorAppointmentDisplaysBean(pationtId, pationtName, pationtNum, appStatus, appTime));
                            if (emergncy.equalsIgnoreCase("true")) {
                                doctorsData.getDcDoctorAppointmentDisplays().add(0, appointmentData);
                            } else {
                                doctorsData.getDcDoctorAppointmentDisplays().add(appointmentData);
                            }

                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().update(doctorsData);
                            clearHandlers();
                            data = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().getAll();

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {

//                            if (data.size() == 1) {
//                                setFragmentForSingleDoc(0);
//                            } else if (data.size() == 2) {
//                                // setFragmentForTwoDoc();
//                                //do stuff in case any
//                            } else {
                            int counter = 0;
                            ArrayList<Integer> positions = new ArrayList<>();

                            for (int i = 0; i < data.size(); i++) {
                                if (!data.get(i).getDcDoctorAppointmentDisplays().isEmpty()) {
                                    counter++;
                                    positions.add(i);
                                }
                            }

                            switch (counter) {
                                case 1:
                                    //  clearHandlers();
                                    setFragmentForSingleDoc(positions.get(0));

                                    break;
                                case 2:
                                    //   clearHandlers();
                                    setFragmentForTwoDoc(positions);
                                    break;
                                default:
                                    //   clearHandlers();
                                    setFragmentForMultiDoc(positions);
                                    break;
//                                default:
//                                    clearHandlers();
//                                    setFragmentForMultiDoc();
//                                    break;
                            }
                            // }
                        }
                    }.execute();

                } else if (eventStatus.equalsIgnoreCase("CheckOut") ||
                        eventStatus.equalsIgnoreCase("NOShow") ||
                        eventStatus.equalsIgnoreCase("cancel")) {
                    Log.d("madhu", "noshow");
                    new AsyncTask() {
                        @Override

                        protected Object doInBackground(Object[] objects) {

                            DoctorsData doctorsData = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().getSingleProduct(docId);
                            List<DoctorsData.DcDoctorAppointmentDisplaysBean> pationtData = doctorsData.getDcDoctorAppointmentDisplays();

//                            for (DoctorsData.DcDoctorAppointmentDisplaysBean m : pationtData) {
//                                if (m.getApptPatientId().equalsIgnoreCase(pationtId)) {
//                                    m.setApptStatus(appStatus);
//                                }
//                                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().update(doctorsData);
//                            }
                            for (int i = 0; i < pationtData.size(); i++) {
                                if (pationtData.get(i).getApptPatientId().equalsIgnoreCase(pationtId)) {
                                    pationtData.remove(i);
                                }
                            }
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().update(doctorsData);
                            clearHandlers();
                            data = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().getAll();
                            Log.d("madhu", data.toString());
                            return null;


                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            int counter = 0;
                            ArrayList<Integer> positions = new ArrayList<>();

                            for (int i = 0; i < data.size(); i++) {
                                if (!data.get(i).getDcDoctorAppointmentDisplays().isEmpty()) {
                                    counter++;
                                    positions.add(i);
                                }
                            }
                            Log.d("madhu", String.valueOf(counter));
                            Log.d("madhu", positions.toString());
                            switch (counter) {
                                case 0:
                                    //   clearHandlers();

                                        getSupportFragmentManager().beginTransaction().remove(new QueueFragment(0)).commit();

                                    Toast.makeText(MainActivity2.this, "No appointments found", Toast.LENGTH_SHORT).show();

                                    break;
                                case 1:
                                    //   clearHandlers();
                                    setFragmentForSingleDoc(positions.get(0));

                                    break;
                                case 2:
                                    //   clearHandlers();
                                    setFragmentForTwoDoc(positions);
                                    break;
                                default:
                                    //  clearHandlers();
                                    setFragmentForMultiDoc(positions);
                                    break;

//                                default:
//                                    clearHandlers();
//                                    setFragmentForMultiDoc();
//                                    break;
                            }
                        }
                    }.execute();


//
//
//
//

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


    private void getDoctorsData() {

        Call<List<DoctorsData>> call = client.getDoctorsData();
        call.enqueue(new Callback<List<DoctorsData>>() {
            @Override
            public void onResponse(Call<List<DoctorsData>> call, Response<List<DoctorsData>> response) {
                data = response.body();

                //   m = data.size();
                new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] objects) {

                        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().insertAll(data);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {

                        //                       setFragment(R.id.id_paly_back1, new ImageFragment());
                        //                       setFragment(R.id.id_paly_back2, new ScrollTextFragment());
                        //                             setFragment(R.id.id_paly_back3, new VideoFragment());
                        int counter = 0;
                        ArrayList<Integer> positions = new ArrayList<>();

                        for (int i = 0; i < data.size(); i++) {
                            if (!data.get(i).getDcDoctorAppointmentDisplays().isEmpty()) {
                                counter++;
                                positions.add(i);
                            }

                        }

//                        if (data.size() == 1) {
//                            setFragmentForSingleDoc(0);
//                        } else if (data.size() == 2) {
//                            ArrayList<Integer> positions2= new ArrayList<>();
//                            positions2.add(0);
//                            positions2.add(1);
//                            setFragmentForTwoDoc(positions2);
//                        } else {


                        switch (counter) {
                            case 0:
                                clearHandlers();
                                Toast.makeText(MainActivity2.this, "No appointments found", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                clearHandlers();
                                setFragmentForSingleDoc(positions.get(0));
                                break;
                            case 2:
                                clearHandlers();
                                setFragmentForTwoDoc(positions);
                                break;
                            default:
                                clearHandlers();
                                setFragmentForMultiDoc(positions);
                                break;
//                            default:
//                                clearHandlers();
//                                setFragmentForMultiDoc();
//                                break;
                        }
                        //     }

                    }
                }.execute();


//
            }

            @Override
            public void onFailure(Call<List<DoctorsData>> call, Throwable t) {

            }
        });
    }


    private void setFragmentForTwoDoc(final ArrayList<Integer> positions) {

        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (redirect2) {
                    int temp3 = data.get(positions.get(0)).getDcDoctorAppointmentDisplays().size();
                    temp4 = Math.max(temp3 * 2, 10);

                    setFragment(R.id.id_paly_back0, new QueueFragment(positions.get(0)));
                    redirect2 = false;
                } else {
                    int temp3 = data.get(positions.get(1)).getDcDoctorAppointmentDisplays().size();
                    temp4 = Math.max(temp3 * 2, 10);

                    setFragment(R.id.id_paly_back0, new QueueFragment(positions.get(1)));
                    redirect2 = true;

                }

                handler2.postDelayed(this, temp4 * 1000);
            }
        }, 0);
    }

    private void setFragmentForSingleDoc(int position) {

        setFragment(R.id.id_paly_back0, new QueueFragment(position));
        //  navigater = true;
    }

    private void setFragmentForMultiDoc(final ArrayList<Integer> positions) {
        Log.d("madhu", "inmulti");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (redirect) {


                    setFragment(R.id.id_paly_back0, new DoctorsFragment());
                    temp2 = 5;
                    redirect = false;

                } else {


                    if (list_index == positions.size()) {
                        list_index = 0;
                    }


                    int temp = data.get(positions.get(list_index)).getDcDoctorAppointmentDisplays().size();
                    // temp2 = temp * 2;

                    temp2 = Math.max(temp * 2, 10);

                    setFragment(R.id.id_paly_back0, new QueueFragment(positions.get(list_index)));


                    redirect = true;


                    list_index++;


                }
                handler.postDelayed(this, temp2 * 1000);
            }
        }, 0);
    }

//    private void setFragmentForMultiDoc() {
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (redirect) {
//
//
//                    setFragment(R.id.id_queue, new DoctorsFragment());
//                    temp2 = 5;
//                    redirect = false;
//
//                } else {
//
//                    //   setFragment(R.id.id_queue, new DoctorsFragment(data));
//                    if (list_index == data.size()) {
//                        list_index = 0;
//                    }
//                    if (!data.get(list_index).getDcDoctorAppointmentDisplays().isEmpty()) {
//
//                        int temp = data.get(list_index).getDcDoctorAppointmentDisplays().size();
//                        // temp2 = temp * 2;
//
//                        temp2 = Math.max(temp * 2, 10);
//
//                        setFragment(R.id.id_queue, new QueueFragment(list_index));
//
//
//                        redirect = true;
//                    } else {
//                        temp2 = 0;
//                        redirect = true;
//
//                    }
//
//                    list_index++;
//                    //   setFragment(R.id.id_queue, new QueueFragment(data.get(m)));
////                    for(int i = m; i<data.size();i++){
////                        if (!data.get(m).getDcDoctorAppointmentDisplays().isEmpty()){
//
////                            break;
////                        }
////                        m++;
////                    }
//
//
//                }
//                handler.postDelayed(this, temp2 * 1000);
//            }
//        }, 0);
//    }

    private void setMargins(FrameLayout layout_id, int left, int top, int right, int bottom) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout_id.getLayoutParams();
        params.setMargins(left, top, right, bottom);
        layout_id.setLayoutParams(params);
    }

    private void setFragment(int id, Fragment fragment) {


        try {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeinout);
            //     transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

            transaction.replace(id, fragment);
            transaction.commit();
        } catch (Exception e) {

        }

    }


    void clearHandlers() {
        handler2.removeCallbacksAndMessages(null);
        handler.removeCallbacksAndMessages(null);
    }

    private void removePreviousdata() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().doctorsDataDao().dropTable();
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().paneResponseDao().dropTable();
                return null;
            }
        }.execute();

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
        //  handler.removeCallbacksAndMessages(null);
        try{
            clearHandlers();
            sse.close();
            sse2.close();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            unregisterNetworkChanges();
        }catch (Exception e){

        }



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


}
