package com.travelmate.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.travelmate.R;
import com.travelmate.services.GeneralAdapter;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnLogin,btnRegister,btnContinue;
    TextView txtName, txtEmail;
    String fullname,email;
    NavigationView navigationView;
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
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);



        // show the button when some condition is true

        return true;
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

        } else if (id == R.id.nav_hotels) {
            changeActivity(HotelsActivity.class);

        } else if (id == R.id.nav_food) {
            changeActivity(RestaurantsActivity.class);

        } else if (id == R.id.nav_cinema) {

        }
        else if (id == R.id.nav_attractions) {

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


}
