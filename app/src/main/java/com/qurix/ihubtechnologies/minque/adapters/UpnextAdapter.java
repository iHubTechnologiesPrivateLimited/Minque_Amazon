package com.qurix.ihubtechnologies.minque.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import java.util.List;

public class UpnextAdapter extends RecyclerView.Adapter<UpnextAdapter.ViewHolder> {
    Context context;
    List<DoctorsData.DcDoctorAppointmentDisplaysBean> data;
    //   ArrayList<UpnextMdel> data;

    int s = 1;

    public UpnextAdapter(List<DoctorsData.DcDoctorAppointmentDisplaysBean> dcDoctorAppointmentDisplays, Context context) {
        this.data = dcDoctorAppointmentDisplays;
        this.context = context;

    }


//    public UpnextAdapter(ArrayList<UpnextMdel> upNextList, Context context) {
//        this.data = upNextList;
//        this.context = context;
//    }

    @NonNull
    @Override
    public UpnextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // todo change layout incase full screen
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upnext_list_item_mini, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpnextAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView serial;
        private final TextView name;
        private final TextView mobile;
        DoctorsData.DcDoctorAppointmentDisplaysBean data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serial = itemView.findViewById(R.id.id_serial_num);
            name = itemView.findViewById(R.id.id_serial_name);
            mobile = itemView.findViewById(R.id.id_serial_mobile);
        }

        public void setData(DoctorsData.DcDoctorAppointmentDisplaysBean upnextMdel) {
            this.data = upnextMdel;
            int position = getAdapterPosition();
            if (position == 0) {
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 14, 0, 3);
                serial.setLayoutParams(params1);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.setMargins(0, 3, 0, 3);
                name.setLayoutParams(params2);

                LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params3.setMargins(0, 3, 0, 14);
                mobile.setLayoutParams(params3);
//todo change values in case full layout
                serial.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21f);
                mobile.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                itemView.setBackgroundResource(R.drawable.rounded_corner_rectanguler_blue1_top);
            } else if (position == 2) {
                itemView.setBackgroundResource(R.drawable.rounded_corner_rectanguler_blue1_bottom);
            }
            serial.setTypeface(((BaseActivity) context).bold);
            name.setTypeface(((BaseActivity) context).bold);
            mobile.setTypeface(((BaseActivity) context).reg);

            serial.setText("0" + s++);

            if (data.getApptPatientName() != null) {
                name.setText(data.getApptPatientName());
            }
            if (data.getApptPatientMobileNo() != null) {
                StringBuilder str = new StringBuilder(data.getApptPatientMobileNo());
                for(int i=3;i<7;i++){
                    str.setCharAt(i, '*');
                }
               String newStr = str.toString();
                newStr = newStr.replaceAll(".....", "$0 ");
                mobile.setText(newStr);

                //  serial.setText(data.getNumber());
            }


        }
    }
}
