package com.travelmate.activities;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelmate.R;

import org.w3c.dom.Text;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Betim on 6/10/2018.
 */

public class DetailsActivity extends AppCompatActivity{
    ImageView main_pic,info,pin,message,price,phone,star1,star2,star3,star4,star5;
    TextView name,location,contact,price_night,phone_contact,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(DetailsActivity.this);
*/
        main_pic = (ImageView)findViewById(R.id.main_pic);

        info = (ImageView)findViewById(R.id.pin);
        info.setImageResource(R.drawable.ic_info);

        pin = (ImageView)findViewById(R.id.info);
        pin.setImageResource(R.drawable.ic_pin);

        phone =(ImageView)findViewById(R.id.phone);
        phone.setImageResource(R.drawable.ic_phone);

        message =(ImageView)findViewById(R.id.message);
        message.setImageResource(R.drawable.ic_message);

        price = (ImageView)findViewById(R.id.price);
        price.setImageResource(R.drawable.ic_price);

        star1 = (ImageView)findViewById(R.id.star1);
        star1.setImageResource(R.drawable.icon_star);
        star2 = (ImageView)findViewById(R.id.star2);
        star2.setImageResource(R.drawable.icon_star);
        star3 = (ImageView)findViewById(R.id.star3);
        star3.setImageResource(R.drawable.icon_star);
        star4 = (ImageView)findViewById(R.id.star4);
        star4.setImageResource(R.drawable.icon_star);
        star5 = (ImageView)findViewById(R.id.star5);
        star5.setImageResource(R.drawable.icon_star);

        name = (TextView)findViewById(R.id.hName);
        location =(TextView)findViewById(R.id.location);
        contact =(TextView)findViewById(R.id.contact);
        price_night =(TextView)findViewById(R.id.price_night);
        phone_contact = (TextView)findViewById(R.id.phone_contact);
        description = (TextView)findViewById(R.id.description);

        Intent i=this.getIntent();

        //RECEIVE DATA
        String hName=i.getExtras().getString("name");
        String hLocation=i.getExtras().getString("location");
        String hMessage=i.getExtras().getString("message");
        String hPrice = i.getExtras().getString("price");
        String hPhone = i.getExtras().getString("phone");
        String hImg = i.getExtras().getString("main_pic");
        String hStar = i.getExtras().getString("star");
        String hDesc = i.getExtras().getString("description");
//        int stars = Integer.parseInt(hStar);

        //BIND DATA
        name.setText(hName);
        location.setText(hLocation);
        contact.setText(hMessage);
        if(hPrice.equals("noprice")){
            price.setVisibility(ImageView.INVISIBLE);
            price_night.setVisibility(TextView.INVISIBLE);
        }
        else
            price_night.setText(hPrice+" per night");
        phone_contact.setText(hPhone);
        description.setText(hDesc);

        Picasso.get().load(hImg).resize(200,200).into(main_pic);

    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
}
