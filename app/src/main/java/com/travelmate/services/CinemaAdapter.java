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
import com.travelmate.data.Cinema;
import com.travelmate.data.Hotels;
import com.travelmate.data.Restaurants;

import java.util.ArrayList;



public class CinemaAdapter extends ArrayAdapter<Cinema> {
    public ArrayList<Cinema> cinemas;
    public Activity activity;

    public CinemaAdapter(@NonNull Activity activity, int resource) {
        super(activity, R.layout.grid_layout);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View gridView = inflater.inflate(R.layout.grid_layout,null,false);

        ImageView image = (ImageView) gridView.findViewById(R.id.grid_item_image);
        TextView  name = (TextView) gridView.findViewById(R.id.grid_item_name);

        name.setText(cinemas.get(position).getName());
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        Picasso.get().load(cinemas.get(position).getUrlImage()).resize(600,400).into(image);

        return gridView;
    }

    @Override
    public int getCount() {
        return cinemas.size();
    }
}
