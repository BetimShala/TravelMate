package com.travelmate;

import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.travelmate.data.*;
/**
 * Created by Betim on 5/13/2018.
 */

public class UsersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ListView userList;
    private ImageView img;
    private LoginRegisterSQLHelper usersSQLHelper;
    Cursor userCursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        usersSQLHelper = new LoginRegisterSQLHelper(this);
        userList = (ListView) findViewById(R.id.listUsers);

       getUsers();
       //delete();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_user);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_user);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public void getUsers() {
        userCursor = usersSQLHelper.getAllUsers();
        UsersCursorAdapter todoAdapter = new UsersCursorAdapter(this, userCursor);
// Attach cursor adapter to the ListView
        userList.setAdapter(todoAdapter);
    }
    public void delete()
    {
      // usersSQLHelper.deleteAll();
    }
}
