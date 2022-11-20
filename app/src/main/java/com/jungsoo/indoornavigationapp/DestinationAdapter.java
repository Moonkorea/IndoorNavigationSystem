package com.jungsoo.indoornavigationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DestinationAdapter extends ArrayAdapter<destination> {
    public DestinationAdapter(Context context, int resource, List<destination> destinationList){

        super(context, resource, destinationList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        destination Destination = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.destination_list, parent, false);
        }

        TextView roomNum = convertView.findViewById(R.id.roomNum);
        TextView roomName = convertView.findViewById(R.id.roomName);

        roomNum.setText(Destination.getRoomNum());
        roomName.setText(Destination.getRoomName());

        return convertView;
    }

}
