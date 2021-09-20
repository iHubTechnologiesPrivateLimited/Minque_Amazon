//In case any constructer issue do like this
//* in fragment class add static method to get fragment instant like follows
//*
//*    public static MyFragment getInstance(int someInt) {
//     MyFragment myFragment = new MyFragment();
//
//     Bundle args = new Bundle();
//     args.putInt("someInt", someInt);
//     myFragment.setArguments(args);
//
//     return myFragment;
//    }
//
//in oncrete of fragment get data like this
//    getArguments().getInt("someInt", 0);
//
//
//in Activity class pass data like
//   MyFragment myfragment = MyFragment.getInstance( 10)
//
//*
package com.qurix.ihubtechnologies.minque.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.adapters.UpnextAdapter;
import com.qurix.ihubtechnologies.minque.model.UpnextMdel;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.storage.DatabaseClient;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class DoctorsFragment extends Fragment {
    @BindView(R.id.doc_img1)
    CircleImageView docImg1;
    @BindView(R.id.doc_hospital1)
    CircleImageView docHospital1;
    @BindView(R.id.id_doc_status1)
    TextView idDocStatus1;
    @BindView(R.id.id_doc_name1)
    TextView idDocName1;
    @BindView(R.id.id_doc_que1)
    TextView idDocQue1;
    @BindView(R.id.id_upnext1)
    TextView idUpnext1;
    @BindView(R.id.id_up_next_list1)
    RecyclerView idUpNextList1;
    @BindView(R.id.doc_img2)
    CircleImageView docImg2;
    @BindView(R.id.doc_hospital2)
    CircleImageView docHospital2;
    @BindView(R.id.id_doc_status2)
    TextView idDocStatus2;
    @BindView(R.id.id_doc_name2)
    TextView idDocName2;
    @BindView(R.id.id_doc_que2)
    TextView idDocQue2;
    @BindView(R.id.id_upnext2)
    TextView idUpnext2;
    @BindView(R.id.id_up_next_list2)
    RecyclerView idUpNextList2;
    @BindView(R.id.doc_img3)
    CircleImageView docImg3;
    @BindView(R.id.doc_hospital3)
    CircleImageView docHospital3;
    @BindView(R.id.id_doc_status3)
    TextView idDocStatus3;
    @BindView(R.id.id_doc_name3)
    TextView idDocName3;
    @BindView(R.id.id_doc_que3)
    TextView idDocQue3;
    @BindView(R.id.id_upnext3)
    TextView idUpnext3;
    @BindView(R.id.id_up_next_list3)
    RecyclerView idUpNextList3;
    @BindView(R.id.doc_img4)
    CircleImageView docImg4;
    @BindView(R.id.doc_hospital4)
    CircleImageView docHospital4;
    @BindView(R.id.id_doc_status4)
    TextView idDocStatus4;
    @BindView(R.id.id_doc_name4)
    TextView idDocName4;
    @BindView(R.id.id_doc_que4)
    TextView idDocQue4;
    @BindView(R.id.id_upnext4)
    TextView idUpnext4;
    @BindView(R.id.id_up_next_list4)
    RecyclerView idUpNextList4;
    Unbinder unbinder;
    @BindView(R.id.doc1_layout)
    LinearLayout doc1Layout;
    @BindView(R.id.doc2_layout)
    LinearLayout doc2Layout;
    @BindView(R.id.doc3_layout)
    LinearLayout doc3Layout;
    @BindView(R.id.doc4_layout)
    LinearLayout doc4Layout;
    @BindView(R.id.id_root_layout)
    LinearLayout idRootLayout;
    private Context mContext;
    ArrayList<UpnextMdel> upNextList = new ArrayList<>();
    UpnextAdapter upnextAdapter;

    List<DoctorsData> data;


//    @SuppressLint("ValidFragment")
//    public DoctorsFragment(List<DoctorsData> data) {
//        this.data = data;
//
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_multi_doc_four, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();

        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                data = DatabaseClient.getInstance(mContext.getApplicationContext()).getAppDatabase().doctorsDataDao().getAll();
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

//                        if (data.size() == 1) {
//                            setFragmentForSingleDoc(0);
//                        } else if (data.size() == 2) {
//                            ArrayList<Integer> positions2= new ArrayList<>();
//                            positions2.add(0);
//                            positions2.add(1);
//                            setFragmentForTwoDoc(positions2);
//                        } else {


                switch (counter) {
                    case 3:
                        setListOneData(positions.get(0));
                        setListTwoData(positions.get(1));
                        setListThreeData(positions.get(2));
                        doc4Layout.setVisibility(View.GONE);
                        idRootLayout.setWeightSum(3f);

                        setMargins(idDocStatus1, 70);
                        setMargins(idDocStatus2, 70);
                        setMargins(idDocStatus3, 70);

                        setMarginsImage(docHospital1, 110);
                        setMarginsImage(docHospital2, 110);
                        setMarginsImage(docHospital3, 110);
                        setMarginsText(idUpnext1, 70, 0, 0, 0);
                        setMarginsText(idUpnext2, 70, 0, 0, 0);
                        setMarginsText(idUpnext3, 70, 0, 0, 0);

                        break;
                    case 4:
                        setListOneData(positions.get(0));
                        setListTwoData(positions.get(1));
                        setListThreeData(positions.get(2));
                        setListFourData(positions.get(3));

//                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
//                               0,
//                                LinearLayout.LayoutParams.MATCH_PARENT,
//                                1.0f
//                        );
//                        doc1Layout.setLayoutParams(param);
//                        doc2Layout.setLayoutParams(param);
//                        doc3Layout.setLayoutParams(param);
//                        doc4Layout.setLayoutParams(param);

                        break;


                    default:
                        setListOneData(positions.get(0));
                        setListTwoData(positions.get(1));
                        setListThreeData(positions.get(2));
                        setListFourData(positions.get(3));


                        break;
                }


//                if (data.size() >= 4) {
//                    Log.d("madhu3","data more than 4");
//                    setListOneData();
//                    setListTwoData();
//                    setListThreeData();
//                    setListFourData();
//                } else if (data.size() == 3) {
//                    setListOneData();
//                    setListTwoData();
//                    setListThreeData();
//                    doc4Layout.setVisibility(View.GONE);
//
//                } else if (data.size() == 2) {
//                    setListOneData();
//                    setListTwoData();
//                    doc3Layout.setVisibility(View.GONE);
//                    doc4Layout.setVisibility(View.GONE);
//                } else if (data.size() == 1) {
//                    setListOneData();
//                    doc2Layout.setVisibility(View.GONE);
//                    doc3Layout.setVisibility(View.GONE);
//                    doc4Layout.setVisibility(View.GONE);
//
//                }


            }
        }.execute();


        return view;

    }

    public void setMarginsText(TextView layout_id, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout_id.getLayoutParams();
        params.setMargins(left, top, right, bottom);
        layout_id.setLayoutParams(params);

    }

    public void setMargins(TextView layout_id, int left) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout_id.getLayoutParams();
        params.setMarginEnd(left);
        layout_id.setLayoutParams(params);

    }

    public void setMarginsImage(ImageView layout_id, int left) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout_id.getLayoutParams();
        params.setMarginEnd(left);
        layout_id.setLayoutParams(params);

    }

    private void setListFourData(int position) {

            if (!data.get(position).getDcDoctorAppointmentDisplays().isEmpty()) {

                doc4Layout.setVisibility(View.VISIBLE);
                idDocName4.setText(data.get(position).getDoctorName());
                idDocQue4.setText(data.get(position).getDoctorstudies());
                if(data.get(position).getDoctorImg()!=null){
                    Picasso.get().load(data.get(position).getDoctorImg()).into(docImg4);
                }


                idUpNextList4.setLayoutManager(new LinearLayoutManager(mContext));
                idUpNextList4.setHasFixedSize(true);
                upnextAdapter = new UpnextAdapter(data.get(position).getDcDoctorAppointmentDisplays(), mContext);
                idUpNextList4.setAdapter(upnextAdapter);
            } else {

                doc4Layout.setVisibility(View.GONE);

            }



    }

    private void setListThreeData(int position) {

            if (!data.get(position).getDcDoctorAppointmentDisplays().isEmpty()) {

                doc3Layout.setVisibility(View.VISIBLE);
                idDocName3.setText(data.get(position).getDoctorName());
                idDocQue3.setText(data.get(position).getDoctorstudies());
                if(data.get(position).getDoctorImg()!=null) {
                    Picasso.get().load(data.get(position).getDoctorImg()).into(docImg3);
                }
                idUpNextList3.setLayoutManager(new LinearLayoutManager(mContext));
                idUpNextList3.setHasFixedSize(true);
                upnextAdapter = new UpnextAdapter(data.get(position).getDcDoctorAppointmentDisplays(), mContext);
                idUpNextList3.setAdapter(upnextAdapter);
            } else {

                doc3Layout.setVisibility(View.GONE);

            }

    }

    private void setListTwoData(int position) {

            if (!data.get(position).getDcDoctorAppointmentDisplays().isEmpty()) {

                doc2Layout.setVisibility(View.VISIBLE);
                idDocName2.setText(data.get(position).getDoctorName());
                idDocQue2.setText(data.get(position).getDoctorstudies());
                if(data.get(position).getDoctorImg()!=null) {
                    Picasso.get().load(data.get(position).getDoctorImg()).into(docImg2);
                }
                idUpNextList2.setLayoutManager(new LinearLayoutManager(mContext));
                idUpNextList2.setHasFixedSize(true);
                upnextAdapter = new UpnextAdapter(data.get(position).getDcDoctorAppointmentDisplays(), mContext);
                idUpNextList2.setAdapter(upnextAdapter);
            } else {
                doc2Layout.setVisibility(View.GONE);


            }

    }

    private void setListOneData(int position) {

            if (!data.get(position).getDcDoctorAppointmentDisplays().isEmpty()) {


                doc1Layout.setVisibility(View.VISIBLE);
                idDocName1.setText(data.get(position).getDoctorName());
                idDocQue1.setText(data.get(position).getDoctorstudies());
                if(data.get(position).getDoctorImg()!=null) {
                    Picasso.get().load(data.get(position).getDoctorImg()).into(docImg1);
                }
                idUpNextList1.setLayoutManager(new LinearLayoutManager(mContext));
                idUpNextList1.setHasFixedSize(true);
                upnextAdapter = new UpnextAdapter(data.get(position).getDcDoctorAppointmentDisplays(), mContext);
                idUpNextList1.setAdapter(upnextAdapter);

            } else {

                doc1Layout.setVisibility(View.GONE);


            }

    }

    private void init() {
        idDocName1.setTypeface(((BaseActivity) mContext).bold);
        idDocName2.setTypeface(((BaseActivity) mContext).bold);
        idDocName3.setTypeface(((BaseActivity) mContext).bold);
        idDocName4.setTypeface(((BaseActivity) mContext).bold);
        idDocQue1.setTypeface(((BaseActivity) mContext).reg);
        idDocQue2.setTypeface(((BaseActivity) mContext).reg);
        idDocQue3.setTypeface(((BaseActivity) mContext).reg);
        idDocQue4.setTypeface(((BaseActivity) mContext).reg);
        idDocStatus1.setTypeface(((BaseActivity) mContext).bold);
        idDocStatus2.setTypeface(((BaseActivity) mContext).bold);
        idDocStatus3.setTypeface(((BaseActivity) mContext).bold);
        idDocStatus4.setTypeface(((BaseActivity) mContext).bold);
        idUpnext1.setTypeface(((BaseActivity) mContext).bold);
        idUpnext2.setTypeface(((BaseActivity) mContext).bold);
        idUpnext3.setTypeface(((BaseActivity) mContext).bold);
        idUpnext4.setTypeface(((BaseActivity) mContext).bold);
//        docImg1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        docImg2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        docImg3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        docImg4.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
