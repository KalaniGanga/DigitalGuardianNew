package com.example.digitalguardian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PatientAdapter extends ArrayAdapter<Patient> {
    private ArrayList<Patient> patientList;

    public PatientAdapter(@NonNull Context context, int resource, ArrayList<Patient> patientList) {
        super(context, resource, patientList);
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview,
                    parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.name_text);
        TextView roomNumberTextView = convertView.findViewById(R.id.room_text);
        TextView temperatureTextView = convertView.findViewById(R.id.tem_text);
        TextView descTextView = convertView.findViewById(R.id.desc_text);
        TextView genderTextView = convertView.findViewById(R.id.gender_text);
        TextView indexTextView = convertView.findViewById(R.id.index_text);
        ImageView srcTextView = convertView.findViewById(R.id.profile_image);
        TextView bpmTextView = convertView.findViewById(R.id.bpm_text);

        nameTextView.setText(patientList.get(position).getName());
        roomNumberTextView.setText(String.valueOf(patientList.get(position).getRoomNumber()));
        temperatureTextView.setText(String.valueOf(patientList.get(position).getTemperature()));
        descTextView.setText(patientList.get(position).getDesc());
        genderTextView.setText(patientList.get(position).getGender());
        indexTextView.setText(patientList.get(position).getIndex());
        bpmTextView.setText(String.valueOf(patientList.get(position).getBpm()) );
        srcTextView.setImageResource(patientList.get(position).getSrc());

        return convertView;
    }

}
