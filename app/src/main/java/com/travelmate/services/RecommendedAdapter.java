package com.travelmate.services;

import android.app.Activity;
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
import com.travelmate.data.Recommended;

import java.util.ArrayList;


public class RecommendedAdapter extends ArrayAdapter<Recommended> {
    public ArrayList<Recommended> recommended;
    public Activity activity;

    public RecommendedAdapter(@NonNull Activity activity, int resource) {
        super(activity, R.layout.content_main);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View gridView = inflater.inflate(R.layout.content_main,null,false);

        ImageView image = (ImageView) gridView.findViewById(R.id.grid_item_image);
        TextView  name = (TextView) gridView.findViewById(R.id.grid_item_name);

        name.setText(recommended.get(position).getName());
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);

        Picasso.get().load(recommended.get(position).getUrlImage()).resize(600,400).into(image);

        return gridView;
    }

    @Override
    public int getCount() {
        return recommended.size();
    }
}
