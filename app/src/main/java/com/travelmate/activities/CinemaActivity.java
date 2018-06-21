package com.travelmate.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.travelmate.R;
import com.travelmate.data.Cinema;
import com.travelmate.data.Restaurants;
import com.travelmate.services.CinemaAdapter;
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
 * Created by IsoCom on 6/21/2018.
 */

public class CinemaActivity extends AppCompatActivity {
    GridView gvCinema;
    CinemaAdapter adapter;
    ArrayList<Cinema> cinemas;
    ArrayList<String> urlS;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        gvCinema = (GridView)findViewById(R.id.gv);
        cinemas = new ArrayList<>();
        urlS = new ArrayList<String>();
        urlS.add("http://www.heraldscotland.com/resources/images/6675937/?type=responsive-gallery-fullscreen");
        urlS.add("https://cdn.meetingsbooker.com/images/venues/London-West-India-Quay-Cineworld-1.jpg");
        urlS.add("https://www.playpennies.com/media/images/2015/05/Capture1-540x325.jpg");
        urlS.add("http://londonvenues.london/wp-content/uploads/2015/08/hackney-Picture-house-venue-hire-London-Venues.jpg");
        urlS.add("http://bloodyhellbrennan.com/wp-content/uploads/2014/09/photo-14.jpg");
        urlS.add("https://images.savoysystems.co.uk/RIL/7328336.jpg");

        OkHttpClient client = new OkHttpClient();
        String url = "http://moviesapi.herokuapp.com/cinemas/find/:E1";


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


                    try {
                        JSONArray body = new JSONArray(response.body().string());
                        for (int i = 0; i <6; i++) {
                            JSONObject item = body.getJSONObject(i);
                            cinemas.add(new Cinema(item.getString("name"),
                                   item.getString("address"),
                                    "(232)-44324",
                                    urlS.get(i)
                            ));
                        }
                    } catch (JSONException e) {
                        Log.e("eeee", e.getMessage());
                        //Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                    CinemaActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new CinemaAdapter(CinemaActivity.this, R.layout.grid_cinema_layout);
                            adapter.cinemas = cinemas;
                            gvCinema.setAdapter(adapter);
                        }
                    });
                }
            }

        });
        //details
        gvCinema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailActivity(cinemas,position);


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

    private void openDetailActivity(ArrayList<Cinema> cinemas,int position)
    {
        Intent i=new Intent(CinemaActivity.this,DetailsActivity.class);
        i.putExtra("name",cinemas.get(position).getName().split(",")[0]);
        i.putExtra("location",cinemas.get(position).getName().split(",")[1]);
        i.putExtra("message","www."+cinemas.get(position).getName().split(" ")[0].toLowerCase().trim()+".com");
        i.putExtra("phone",cinemas.get(position).getPhone());
        i.putExtra("star",5);
        i.putExtra("price","noprice");
        i.putExtra("main_pic",cinemas.get(position).getUrlImage());


        CinemaActivity.this.startActivity(i);

    }

}
