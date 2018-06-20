package com.travelmate.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.travelmate.R;
import com.travelmate.services.GeneralAdapter;

import com.travelmate.data.Restaurants;
import com.travelmate.services.RestaurantsAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Betim on 6/10/2018.
 */

public class RestaurantsActivity extends AppCompatActivity   {
    GridView gvRestaurants;
    RestaurantsAdapter adapter;
    ArrayList<Restaurants> restaurants;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        gvRestaurants = (GridView)findViewById(R.id.gv);
        restaurants = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String url = "https://opentable.herokuapp.com/api/restaurants?name=New York";


        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("errori",e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("s",response.toString());
                    throw new IOException("Unexpected code " + response);

                } else{
                    JSONObject body=null;
                    JSONArray aRestaurants=null;
                    try {
                         body = new JSONObject(response.body().string());
                         aRestaurants = body.getJSONArray("restaurants");
                        for (int i = 0; i < 10; i++) {
                            JSONObject item = aRestaurants.getJSONObject(i);
                            restaurants.add(new Restaurants(item.getString("name"),
                                    item.getString("country"),
                                    item.getString("city"),
                                    item.getString("address"),
                                    item.getString("phone"),
                                    item.getDouble("lat"),
                                    item.getDouble("log"),
                                    item.getDouble("price"),
                                    item.getString("image_url")
                            ));
                        }
                    } catch (JSONException e) {
                        Log.e("eeee", e.getMessage()+"\n   =   \n"+body.toString() + "\n ----- \n"+aRestaurants.toString());
                        //Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                    RestaurantsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new RestaurantsAdapter(RestaurantsActivity.this, R.layout.grid_restaurants_layout);
                            adapter.restaurants = restaurants;
                            gvRestaurants.setAdapter(adapter);
                        }
                    });
                }
            }

        });
        //details
        gvRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailActivity(restaurants,position);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.general_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void openDetailActivity(ArrayList<Restaurants> restaurants,int position)
    {
        Intent i=new Intent(RestaurantsActivity.this,HotelDetailsActivity.class);
        i.putExtra("name",restaurants.get(position).getCity()+" Hotel");
        i.putExtra("location",restaurants.get(position).getCountry());
        i.putExtra("message","restaurant@"+restaurants.get(position).getCity().toLowerCase()+".com");
       // i.putExtra("price","$"+restaurants.get(position).get());
        i.putExtra("phone","(212)885-443");
        i.putExtra("star",restaurants.get(position).getRating());
        i.putExtra("main_pic",restaurants.get(position).getUrlImage());


        RestaurantsActivity.this.startActivity(i);

    }

}
