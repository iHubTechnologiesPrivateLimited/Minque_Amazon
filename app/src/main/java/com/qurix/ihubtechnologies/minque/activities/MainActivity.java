/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.qurix.ihubtechnologies.minque.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.adapters.PatietAdapter;
import com.qurix.ihubtechnologies.minque.adapters.UpnextAdapter;
import com.qurix.ihubtechnologies.minque.model.PatientModel;
import com.qurix.ihubtechnologies.minque.model.UpnextMdel;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.id_status)
    TextView idStatus;
    @BindView(R.id.id_tv_room)
    TextView idTvRoom;
    @BindView(R.id.id_tv_time)
    TextView idTvTime;
    @BindView(R.id.id_tv_address)
    TextView idTvAddress;
    @BindView(R.id.id_tv_qul)
    TextView idTvQul;
    @BindView(R.id.id_tv_name)
    TextView idTvName;
    @BindView(R.id.id_tv_upnext)
    TextView idTvUpnext;
    @BindView(R.id.waitinglist)
    TextView waitinglist;
    @BindView(R.id.id_up_next)
    RecyclerView idUpNext;
    @BindView(R.id.id_patient_list)
    RecyclerView idPatientList;

    ArrayList<UpnextMdel> upNextList = new ArrayList<>();
    UpnextAdapter upnextAdapter;
    ArrayList<PatientModel> patientList = new ArrayList<>();
    PatietAdapter patietAdapter;
    @BindView(R.id.waiting_patient_text)
    TextView waitingPatientText;
    @BindView(R.id.waiting_contact_text)
    TextView waitingContactText;
    @BindView(R.id.waiting_status_text)
    TextView waitingStatusText;
    @BindView(R.id.id_spec1)
    TextView idSpec1;
    @BindView(R.id.id_spec2)
    TextView idSpec2;
    @BindView(R.id.id_spec3)
    TextView idSpec3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grey_mini);
        ButterKnife.bind(this);
//        init();
 //         prepareUpNextData();
        //  preparePatientData();
//        getDoctorsData();


//      String mac_Address = getWifiMacAddress();
//        Log.d("adiki",mac_Address);

    }

 //   private String getWifiMacAddress() {
//        try {
//            String interfaceName = "wlan0";
//            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface intf : interfaces) {
//                if (!intf.getName().equalsIgnoreCase(interfaceName)){
//                    continue;
//                }
//
//                byte[] mac = intf.getHardwareAddress();
//                if (mac==null){
//                    return "";
//                }
//
//                StringBuilder buf = new StringBuilder();
//                for (byte aMac : mac) {
//                    buf.append(String.format("%02X:", aMac));
//                }
//                if (buf.length()>0) {
//                    buf.deleteCharAt(buf.length() - 1);
//                }
//                return buf.toString();
//            }
//        } catch (Exception ex) { } // for now eat exceptions
//        return "";
//    }
//    private void getDoctorsData() {
//        Call<List<DoctorsData>> call = client.getDoctorsData();
//        call.enqueue(new Callback<List<DoctorsData>>() {
//            @Override
//            public void onResponse(Call<List<DoctorsData>> call, Response<List<DoctorsData>> response) {
//                List<DoctorsData> data = response.body();
//                setDoctorsDetails(data.get(0));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DoctorsData>> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void setDoctorsDetails(DoctorsData doctorsData) {
//        idTvName.setText(doctorsData.getDoctorName());
//        idTvQul.setText(doctorsData.getDoctorstudies());
//        idTvTime.setText(doctorsData.getDoctorTime());
//        idTvAddress.setText(doctorsData.getOrganization());
//        List<String> sepecList = doctorsData.getDoctorSpeciality();
//
//        if (sepecList.size() >=3) {
//            idSpec1.setText(sepecList.get(0));
//            idSpec2.setText(sepecList.get(1));
//            idSpec2.setText(sepecList.get(2));
//            idSpec1.setVisibility(View.VISIBLE);
//            idSpec2.setVisibility(View.VISIBLE);
//            idSpec3.setVisibility(View.VISIBLE);
//
//        } else if (sepecList.size() == 2) {
//            idSpec1.setText(sepecList.get(0));
//            idSpec2.setText(sepecList.get(1));
//            idSpec1.setVisibility(View.VISIBLE);
//            idSpec2.setVisibility(View.VISIBLE);
//
//        } else if (sepecList.size() == 1) {
//            idSpec1.setText(sepecList.get(0));
//            idSpec1.setVisibility(View.VISIBLE);
//        }
//
//        idUpNext.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        idUpNext.setHasFixedSize(true);
//        upnextAdapter = new UpnextAdapter(doctorsData.getDcDoctorAppointmentDisplays(), MainActivity.this);
//        idUpNext.setAdapter(upnextAdapter);
//
//        idPatientList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        idPatientList.setHasFixedSize(true);
//        patietAdapter = new PatietAdapter(doctorsData.getDcDoctorAppointmentDisplays(), MainActivity.this);
//        idPatientList.setAdapter(patietAdapter);


    }


    private void preparePatientData() {
//        patientList.add(new PatientModel("madhumohan", "9898**8*8*", "CheckedIn"));
//        patientList.add(new PatientModel("adikimadhu", "9898**8*8*", "CheckedIn"));
//        patientList.add(new PatientModel("adikimohan", "9898**8*8*", "CheckedIn"));
//        patientList.add(new PatientModel("patient one", "9898**8*8*", "CheckedIn"));
//        patientList.add(new PatientModel("patient2", "9898**8*8*", "Contact frent desk"));
////        patientList.add(new PatientModel("patient34", "9898989898", "CheckedIn"));
////        patientList.add(new PatientModel("patient37", "9898989898", "CheckedIn"));
//        idPatientList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        idPatientList.setHasFixedSize(true);
//        patietAdapter = new PatietAdapter(patientList, MainActivity.this);
//        idPatientList.setAdapter(patietAdapter);

    }

//    private void prepareUpNextData() {
//        upNextList.add(new UpnextMdel("01", "Patient one", "9999999999"));
////        upNextList.add(new UpnextMdel("02", "Patient two", "9999999999"));
////        upNextList.add(new UpnextMdel("03", "Patient three", "9999999999"));
////        upNextList.add(new UpnextMdel("04", "Patient four", "9999999999"));
////        upNextList.add(new UpnextMdel("05", "Patient five", "9999999999"));
//
//        idUpNext.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        idUpNext.setHasFixedSize(true);
//        upnextAdapter = new UpnextAdapter(upNextList, MainActivity.this);
//        idUpNext.setAdapter(upnextAdapter);
//    }

    private void init() {

        idStatus.setTypeface(bold);
        idTvRoom.setTypeface(bold);
        idTvAddress.setTypeface(reg);
        idTvTime.setTypeface(reg);
        idTvQul.setTypeface(reg);
        idTvName.setTypeface(bold);
        idTvUpnext.setTypeface(bold);
        waitinglist.setTypeface(bold);
        waitingContactText.setTypeface(reg);
        waitingStatusText.setTypeface(reg);
        waitingPatientText.setTypeface(reg);
    }

}
