package com.travelmate.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.travelmate.R;
import com.travelmate.data.Recommended;
import com.travelmate.services.RecommendedAdapter;
import com.travelmate.services.GeneralAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnLogin,btnRegister,btnContinue;
    TextView txtName, txtEmail;
    String fullname,email;
    NavigationView navigationView;
    GridView gvRecommended;
    RecommendedAdapter adapter;
    ArrayList<Recommended> recommended;
    ArrayList<String> urlS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        fullname = i.getStringExtra("fullname");
        email = i.getStringExtra("email");


        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);


        txtName = (TextView)header.findViewById(R.id.txtNameView);
        //txtEmail = (TextView)header.findViewById(R.id.txtEmailView);

        Menu nav_Menu = navigationView.getMenu();

        if (fullname.equals("continue")) {
            nav_Menu.findItem(R.id.nav_findbuddy).setVisible(false);        }
        else
        {
            txtName.setText(fullname);
            //txtEmail.setText(email);
            nav_Menu.findItem(R.id.nav_findbuddy).setVisible(true);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        gvRecommended = (GridView)findViewById(R.id.gv);
        recommended = new ArrayList<>();
        urlS = new ArrayList<String>();
        urlS.add("https://cdn.vox-cdn.com/thumbor/nNduCX3-OhR05NqG0PUqAUsJwQc=/34x0:587x415/1200x800/filters:focal(34x0:587x415)/cdn.vox-cdn.com/uploads/chorus_image/image/48085683/Screen_20shot_202013-12-01_20at_203.40.54_20PM.0.png");
        urlS.add("https://backstage.worldarchitecturenews.com/worldinteriorsnews/uploads/award-entry/large/2014/1402078368_6009_Reception.jpg");
        urlS.add("https://www.countryside-properties.com/imagegen.ashx?image=/media/7544/barrowcroft-green-006.jpg&width=536");
        urlS.add("https://format-com-cld-res.cloudinary.com/image/private/s--Y6-B6uhx--/c_limit,g_center,h_1200,w_65535/a_auto,fl_keep_iptc.progressive,q_95/v1/2abeb5cc637f1a63afc3a62efaa67460/FOX_9090.jpg");
        urlS.add("http://enablelc.org/wp-content/uploads/2015/12/Rowing-700x500.jpg");
        urlS.add("http://www.akt-uk.com/medias/images/diaporamas/149/bonhams-auction-house-01_940x414.jpg");
        urlS.add("http://www.chelseafringe.com/wp-content/uploads/2016/04/fenton-123.png");
        urlS.add("https://i.ytimg.com/vi/NtCgN4WCujk/maxresdefault.jpg");


        OkHttpClient client = new OkHttpClient();
        String url = "http://tour-pedia.org/api/getPlaces?location=London&category=attraction&name=house";


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
                        for (int i = 3; i <11; i++) {
                            JSONObject item = body.getJSONObject(i);
                            recommended.add(new Recommended(item.getString("name"),
                                    item.getString("address"),
                                    "(232)-44324",
                                    urlS.get(i-3)
                            ));
                        }
                    } catch (JSONException e) {
                        Log.e("eeee", e.getMessage());
                        //Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new RecommendedAdapter(MainActivity.this, R.layout.content_main);
                            adapter.recommended = recommended;
                            gvRecommended.setAdapter(adapter);
                        }
                    });
                }
            }

        });
        //details
        gvRecommended.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailActivity(recommended,position);


            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_recommended) {

        }  else if (id == R.id.nav_findbuddy) {
            changeActivity(UsersActivity.class);

        }else if (id == R.id.action_add_task) {
            Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),ToDoActivity.class);
            i.putExtra("fullname","continue");
            startActivity(i);

        } else if (id == R.id.nav_hotels) {
            changeActivity(HotelsActivity.class);

        } else if (id == R.id.nav_food) {
            changeActivity(RestaurantsActivity.class);

        } else if (id == R.id.nav_cinema) {
            changeActivity(MainActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeActivity(Class cl)
    {
        Intent i = new Intent(getApplicationContext(),cl);
        startActivity(i);
    }

    private void openDetailActivity(ArrayList<Recommended> recommended, int position)
    {
        Intent i=new Intent(MainActivity.this,DetailsActivity.class);
        i.putExtra("name",recommended.get(position).getName().split(",")[0]);
        i.putExtra("location",recommended.get(position).getName().split(",")[1]);
        i.putExtra("message","www."+recommended.get(position).getName().split(" ")[0].toLowerCase().trim()+".com");
        i.putExtra("phone",recommended.get(position).getPhone());
        i.putExtra("star",5);
        i.putExtra("price","noprice");
        i.putExtra("main_pic",recommended.get(position).getUrlImage());


        MainActivity.this.startActivity(i);

    }


}
