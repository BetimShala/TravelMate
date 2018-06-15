package com.travelmate.services;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelmate.R;
import com.travelmate.data.Hotels;

import java.util.ArrayList;

/**
 * Created by Betim on 6/10/2018.
 */

public class HotelsAdapter extends ArrayAdapter<Hotels> {
    public ArrayList<Hotels> hotels;
    public Activity activity;

    public HotelsAdapter(@NonNull Activity activity, int resource) {
        super(activity, R.layout.grid_hotels_layout);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View gridView = inflater.inflate(R.layout.grid_hotels_layout,null,false);

        ImageView image = (ImageView) gridView.findViewById(R.id.grid_item_image);
        TextView  name = (TextView) gridView.findViewById(R.id.grid_item_name);

        name.setText(hotels.get(position).getCity() + " Hotel");
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        Picasso.get().load(hotels.get(position).getUrlImages().get(0)).resize(400,400).into(image);

        return gridView;
    }

    @Override
    public int getCount() {
        return hotels.size();
    }
}
