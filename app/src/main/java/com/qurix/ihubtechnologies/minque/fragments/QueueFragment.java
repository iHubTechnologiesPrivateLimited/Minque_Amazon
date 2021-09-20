package com.qurix.ihubtechnologies.minque.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.adapters.PatietAdapter;
import com.qurix.ihubtechnologies.minque.adapters.UpnextAdapter2;
import com.qurix.ihubtechnologies.minque.model.PatientModel;
import com.qurix.ihubtechnologies.minque.model.UpnextMdel;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.storage.DatabaseClient;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class QueueFragment extends Fragment {


    ArrayList<UpnextMdel> upNextList = new ArrayList<>();
    public  UpnextAdapter2 upnextAdapter;
    ArrayList<PatientModel> patientList = new ArrayList<>();
    PatietAdapter patietAdapter;

    Context mcontext;


    @BindView(R.id.imageView)
    CircleImageView imageView;
    @BindView(R.id.imageView2)
    CircleImageView imageView2;
    @BindView(R.id.id_status)
    TextView idStatus;

    @BindView(R.id.id_spec1)
    TextView idSpec1;
    @BindView(R.id.id_spec2)
    TextView idSpec2;
    @BindView(R.id.id_spec3)
    TextView idSpec3;
    @BindView(R.id.id_tv_qul)
    TextView idTvQul;
    @BindView(R.id.id_tv_room)
    TextView idTvRoom;
    @BindView(R.id.id_tv_time)
    TextView idTvTime;
    @BindView(R.id.id_tv_address)
    TextView idTvAddress;
    @BindView(R.id.id_tv_upnext)
    TextView idTvUpnext;
    @BindView(R.id.id_up_next)
    RecyclerView idUpNext;
    @BindView(R.id.waitinglist)
    TextView waitinglist;
    @BindView(R.id.waiting_patient_text)
    TextView waitingPatientText;
    @BindView(R.id.waiting_contact_text)
    TextView waitingContactText;
    @BindView(R.id.waiting_status_text)
    TextView waitingStatusText;
    @BindView(R.id.id_patient_list)
    RecyclerView idPatientList;
    Unbinder unbinder1;
    @BindView(R.id.id_tv_name)
    TextView idTvName;
    private Unbinder unbinder;
    // DoctorsData data;
    List<DoctorsData> data ;
    int m;
    List<DoctorsData.DcDoctorAppointmentDisplaysBean> data2 = new ArrayList<>();
    final Handler handler = new Handler();

    @SuppressLint("ValidFragment")
    public QueueFragment(int m) {
        this.m = m;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_grey_mini, viewGroup, false);
        unbinder = ButterKnife.bind(this, view);

        init();


        
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
          data = DatabaseClient.getInstance(mcontext.getApplicationContext()).getAppDatabase().doctorsDataDao().getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {

                setDoctorsDetails(data.get(m));

            }
        }.execute();


        return view;

    }



    private void setDoctorsDetails(DoctorsData doctorsData) {


        idTvName.setText(doctorsData.getDoctorName());
        idTvQul.setText(doctorsData.getDoctorstudies());
        idTvTime.setText(doctorsData.getDoctorTime());
        idTvAddress.setText(doctorsData.getOrganization());
if(doctorsData.getDoctorImg()!=null){
    Picasso.get().load(doctorsData.getDoctorImg()).into(imageView);
}


        List<String> sepecList = doctorsData.getDoctorSpeciality();

        if (sepecList.size() >= 3) {
            idSpec1.setText(sepecList.get(0));
            idSpec2.setText(sepecList.get(1));
            idSpec2.setText(sepecList.get(2));
            idSpec1.setVisibility(View.VISIBLE);
            idSpec2.setVisibility(View.VISIBLE);
            idSpec3.setVisibility(View.VISIBLE);

        } else if (sepecList.size() == 2) {
            idSpec1.setText(sepecList.get(0));
            idSpec2.setText(sepecList.get(1));
            idSpec1.setVisibility(View.VISIBLE);
            idSpec2.setVisibility(View.VISIBLE);

        } else if (sepecList.size() == 1) {
            idSpec1.setText(sepecList.get(0));
            idSpec1.setVisibility(View.VISIBLE);
        }
        if (doctorsData.getDcDoctorAppointmentDisplays().size() != 0) {

            idUpNext.setLayoutManager(new LinearLayoutManager(mcontext));
            idUpNext.setHasFixedSize(true);
            upnextAdapter = new UpnextAdapter2(doctorsData.getDcDoctorAppointmentDisplays(), mcontext);
            idUpNext.setAdapter(upnextAdapter);


            final List<DoctorsData.DcDoctorAppointmentDisplaysBean> data2 = new ArrayList<>();
            for(int i = 3; i<doctorsData.getDcDoctorAppointmentDisplays().size();i++){
                data2.add(doctorsData.getDcDoctorAppointmentDisplays().get(i));
            }
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.VERTICAL, false);
            idPatientList.setLayoutManager(linearLayoutManager);
            idPatientList.setHasFixedSize(true);

            if(data2.isEmpty()){
                DoctorsData.DcDoctorAppointmentDisplaysBean emptyData = new DoctorsData.DcDoctorAppointmentDisplaysBean();
                emptyData.setApptPatientName("_ _");
                emptyData.setApptBookedTime("_ _");
                emptyData.setApptPatientMobileNo("_ _");
                data2.add(emptyData);
            }
            patietAdapter = new PatietAdapter(data2, mcontext);
            idPatientList.setAdapter(patietAdapter);

            final int scrollSpeed = 50;   // Scroll Speed in Milliseconds


            final Runnable runnable = new Runnable() {
                int x = 15;        // Pixels To Move/Scroll
                boolean flag = true;
                // Find Scroll Position By Accessing RecyclerView's LinearLayout's Visible Item Position
                int scrollPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                int arraySize = data2.size();  // Gets RecyclerView's Adapter's Array Size

                @Override
                public void run() {
                    if (scrollPosition < arraySize) {
                        if (scrollPosition == arraySize - 1) {
                            flag = false;
                        } else if (scrollPosition <= 1) {
                            flag = true;
                        }
                        if (!flag) {
                            try {
                                // Delay in Seconds So User Can Completely Read Till Last String
                                TimeUnit.SECONDS.sleep(2);
                                idPatientList.scrollToPosition(0);  // Jumps Back Scroll To Start Point
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            flag = true;
                        }
                        // Know The Last Visible Item
                        scrollPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                        idPatientList.smoothScrollBy(0, 2);
                        handler.postDelayed(this, scrollSpeed);
                    }
                }
            };

        //    handler.postDelayed(runnable, 3000);




        }


    }


    private void init() {
        idStatus.setTypeface(((BaseActivity) mcontext).bold);
        idTvRoom.setTypeface(((BaseActivity) mcontext).bold);
        idTvAddress.setTypeface(((BaseActivity) mcontext).reg);
        idTvTime.setTypeface(((BaseActivity) mcontext).reg);
        idTvQul.setTypeface(((BaseActivity) mcontext).reg);
        idTvName.setTypeface(((BaseActivity) mcontext).bold);
        idTvUpnext.setTypeface(((BaseActivity) mcontext).bold);
        waitinglist.setTypeface(((BaseActivity) mcontext).bold);
        waitingContactText.setTypeface(((BaseActivity) mcontext).reg);
        waitingStatusText.setTypeface(((BaseActivity) mcontext).reg);
        waitingPatientText.setTypeface(((BaseActivity) mcontext).reg);
      //  imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public void onAttach(Context context) {
        mcontext = context;
        super.onAttach(context);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        handler.removeCallbacksAndMessages(null);
    }



}