package com.qurix.ihubtechnologies.minque.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import java.util.List;

public class PatietAdapter extends RecyclerView.Adapter<PatietAdapter.ViewHolder> {
    Context context;
    List<DoctorsData.DcDoctorAppointmentDisplaysBean> data;
//    ArrayList<PatientModel> data;
//    public PatietAdapter(ArrayList<PatientModel> patientList, Context mcontext) {
//        this.context= mcontext;
//        this.data = patientList;
//
//    }


    public PatietAdapter(List<DoctorsData.DcDoctorAppointmentDisplaysBean> upNextList, Context context) {
        this.data = upNextList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatietAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //todo change layout in case full layout
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_list_item_mini, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatietAdapter.ViewHolder viewHolder, int i) {

            viewHolder.setData(data.get(i));




    }

    @Override
    public int getItemCount() {
            return data.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView contact;
        private final TextView status;
        DoctorsData.DcDoctorAppointmentDisplaysBean data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            contact = itemView.findViewById(R.id.item_contact);
            status = itemView.findViewById(R.id.item_status);
        }

        public void setData(DoctorsData.DcDoctorAppointmentDisplaysBean upnextMdel) {
            this.data = upnextMdel;
            int position = getAdapterPosition();
            if (data.getApptStatus() != null) {
                if (data.getApptStatus().equalsIgnoreCase("CheckedIn")) {
                    status.setTextColor(context.getResources().getColor(R.color.font_green));
                }
            }
            name.setText(data.getApptPatientName());
                status.setText(data.getApptBookedTime());
                if (data.getApptPatientMobileNo() == null||data.getApptPatientMobileNo().equalsIgnoreCase("_ _")) {
                    contact.setText(data.getApptPatientMobileNo());

                //  serial.setText(data.getNumber());
            }else{
                    StringBuilder str = new StringBuilder(data.getApptPatientMobileNo());
                    for (int i = 3; i < 7; i++) {
                        str.setCharAt(i, '*');
                    }
                    String newStr = str.toString();
                    newStr = newStr.replaceAll(".....", "$0 ");
                    contact.setText(newStr);
                }


            name.setTypeface(((BaseActivity) context).bold);
            contact.setTypeface(((BaseActivity) context).reg);
            status.setTypeface(((BaseActivity) context).reg);


        }


    }
}