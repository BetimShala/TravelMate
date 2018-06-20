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
import android.widget.Toast;
import com.travelmate.R;

import com.travelmate.data.Hotels;
import com.travelmate.services.HotelsAdapter;

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

public class HotelsActivity extends AppCompatActivity   {
    GridView gvHotels;
    HotelsAdapter adapter;
    ArrayList<Hotels> hotels;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        gvHotels = (GridView)findViewById(R.id.gv);
        hotels = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String url = "https://fake-hotel-api.herokuapp.com/api/hotels";


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
                            for (int i = 0; i < 10; i++) {
                                ArrayList<String> Images = new ArrayList<String>();
                                JSONObject item = body.getJSONObject(i);
                                JSONArray images = item.getJSONArray("images");
                                for (int img = 0; img < images.length(); img++) {
                                    Images.add(images.getString(img));
                                }
                                hotels.add(new Hotels(item.getString("name"),
                                        item.getString("country"),
                                        item.getString("city"),
                                        item.getString("description"),
                                        item.getDouble("price"),
                                        item.getDouble("stars"),
                                        item.getDouble("rating"),
                                        Images
                                ));
                            }
                        } catch (JSONException e) {
                            Log.i("eeee", e.getMessage());
                            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                        HotelsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new HotelsAdapter(HotelsActivity.this, R.layout.grid_hotels_layout);
                                adapter.hotels = hotels;
                                gvHotels.setAdapter(adapter);
                            }
                        });
                    }
            }

        });
        //details
        gvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailActivity(hotels,position);


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

    private void openDetailActivity(ArrayList<Hotels> hotels,int position)
    {
        Intent i=new Intent(HotelsActivity.this,DetailsActivity.class);
        i.putExtra("name",hotels.get(position).getCity()+" Hotel");
        i.putExtra("location",hotels.get(position).getCountry());
        i.putExtra("message","hotel@"+hotels.get(position).getCity().toLowerCase()+".com");
        i.putExtra("price","$"+hotels.get(position).getPrice());
        i.putExtra("phone","(212)885-443");
        i.putExtra("star",hotels.get(position).getStars());
        i.putExtra("main_pic",hotels.get(position).getUrlImages().get(0));
        i.putExtra("description",hotels.get(position).getDescription());

        HotelsActivity.this.startActivity(i);

    }

}
