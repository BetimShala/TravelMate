package com.travelmate.services;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Betim on 6/20/2018.
 */

public class GeneralAdapter extends AppCompatActivity{

    public void changeActivity(Class cl)
    {
        Intent i = new Intent(getApplicationContext(),cl);
        startActivity(i);
    }
}
