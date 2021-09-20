package com.qurix.ihubtechnologies.minque.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qurix.ihubtechnologies.minque.R;
import com.qurix.ihubtechnologies.minque.model.PatientModel;
import com.qurix.ihubtechnologies.minque.utils.BaseActivity;

import java.util.ArrayList;

public class PatietAdapterWhite extends RecyclerView.Adapter<PatietAdapterWhite.ViewHolder> {
    Context context;
    ArrayList<PatientModel> data;


    public PatietAdapterWhite(ArrayList<PatientModel> upNextList, Context context) {
        this.data = upNextList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatietAdapterWhite.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_list_item2, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatietAdapterWhite.ViewHolder viewHolder, int i) {
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
        PatientModel data;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            contact = itemView.findViewById(R.id.item_contact);
            status = itemView.findViewById(R.id.item_status);
        }

        public void setData(PatientModel upnextMdel) {
            this.data = upnextMdel;
            int position = getAdapterPosition();
            if (data.getStatus().equalsIgnoreCase("CheckedIn")) {
                status.setTextColor(context.getResources().getColor(R.color.font_green));
            }

            name.setText(data.getPatient());
            contact.setText(data.getContact());
            status.setText(data.getStatus());

            name.setTypeface(((BaseActivity) context).bold);
            contact.setTypeface(((BaseActivity) context).reg);
            status.setTypeface(((BaseActivity) context).reg);

        }
    }
}
