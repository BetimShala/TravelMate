package com.travelmate.services;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelmate.R;

import java.util.Random;

/**
 * Created by Betim on 5/13/2018.
 */

public class UsersCursorAdapter extends CursorAdapter {
    public UsersCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.user_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        Random random = new Random();
        TextView fullname = (TextView) view.findViewById(R.id.txtName);
        TextView email = (TextView) view.findViewById(R.id.txtEmail);
        TextView userAge = (TextView)view.findViewById(R.id.txtAge);
        TextView phone = (TextView)view.findViewById(R.id.txtPhone);
        ImageView userImage = (ImageView) view.findViewById(R.id.profile);
        // Extract properties from cursor
        String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
        String name = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
        String emaili = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        String age = cursor.getString(cursor.getColumnIndex("age"));
        // Populate fields with extracted properties
        fullname.setText("Fullname: "+name);
        email.setText("Email: "+emaili);
        userAge.setText("Age: "+age);
        phone.setText("Phone: +383"+String.valueOf(100000000 + random.nextInt(600000)));
        Log.i("gender",gender.toString());
        if(gender.equals("Male"))
        {
            userImage.setImageDrawable(view.getResources().getDrawable(R.drawable.man1));
        }
        else {userImage.setImageDrawable(view.getResources().getDrawable(R.drawable.woman));}
    }
}