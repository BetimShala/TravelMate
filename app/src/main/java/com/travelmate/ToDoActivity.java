package com.travelmate;

/**
 * Created by Betim on 5/10/2018.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener {

    private ListView lsTodo;
    private ToDoListSQLHelper todoListSQLHelper;

    final Calendar c = Calendar.getInstance();
    public String todoTaskInput;
    int hourU,minutesU;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_todo);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_todo);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_todo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_todo);
        navigationView.setNavigationItemSelectedListener(this);
        todoListSQLHelper = new ToDoListSQLHelper(this);
        lsTodo = (ListView) findViewById(R.id.list);
        TextView emptyText = (TextView) findViewById(android.R.id.empty);
        lsTodo.setEmptyView(emptyText);
        loadTodoList();


    /*public void notificationService(int afterThisTime)
    {

        int seconds = afterThisTime;
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        c.add(Calendar.SECOND,seconds);

        Intent intent = new Intent("com.travelmate.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),broadcast);


    }*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);

        return true;
    }
    DialogFragment dateTimeDialogHandler = new DateTimeDialogHandler();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_add_task) {
            AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
            todoTaskBuilder.setTitle("Make a plan for today");
            final EditText todoET = new EditText(this);
            final TextView todoTimePicker = new TextView(this);
            todoET.setHint("write your plan...");
            todoTaskBuilder.setView(todoET);

            final ImageButton forDate = new ImageButton(this);
            //todoTaskBuilder.setView(forDate);
            todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                        todoTaskInput = String.valueOf(todoET.getText());


                    if(todoET.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Ju nuk mund te shtoni plane pa bere,ju lutem shenojeni planin ne fushen e caktuar", Toast.LENGTH_LONG).show();

                        }
                    else {
                        dateTimeDialogHandler.show(getFragmentManager(),"time");
                    }



                }
            }).setNegativeButton("Cancel", null).create();
            todoTaskBuilder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTV = (TextView)parent.findViewById(R.id.todoTv);
        TextView taSkTV = (TextView)parent.findViewById(R.id.todoTime);
        String task = String.valueOf(taskTV.getText());
        String time = String.valueOf(taSkTV.getText());
        Log.i("task","PLANI"+taskTV.getText().toString()+" ORA :"+time);

        todoListSQLHelper.deleteTask(task);
        loadTodoList();
    }
    Cursor todoCursor;
    //update the todo task list UI
    private void loadTodoList() {
        todoCursor = todoListSQLHelper.getAllTasks();
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor);
// Attach cursor adapter to the ListView
        lsTodo.setAdapter(todoAdapter);
       /*
        if(ca == null)
        {
            tdAdapter = new SimpleCursorAdapter(this, R.layout.todo, ca, new String[] {"todo","task"}, new int[] { R.id.todoTv, R.id.todoTime }, 0);
            lsTodo.setAdapter(tdAdapter);
        }
        else {
            //tdAdapter.clear();
            //tdAdapter.addAll(todoList);
            //tdAdapter.notifyDataSetChanged();
        }*/
    }

@Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_recommended) {
                Toast.makeText(getApplicationContext(),"Recommended",Toast.LENGTH_LONG).show();

            } else if (id == R.id.nav_favorite) {

            } else if (id == R.id.nav_findbuddy) {

            } else if (id == R.id.nav_hotels) {

            } else if (id == R.id.nav_food) {

            } else if (id == R.id.nav_cinema) {

            }
            else if (id == R.id.nav_shopping) {

            }
            else if (id == R.id.nav_transport) {

            }
            else if (id == R.id.nav_attractions) {

            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_todo);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    @Override
    public void onTimeSet(TimePicker timePicker, int pickedHour, int pickedMinutes) {

        hourU = pickedHour;
        minutesU = pickedMinutes;


        int hourS = c.get(Calendar.HOUR_OF_DAY);
        int minutesS = c.get(Calendar.MINUTE);

        int totalH,totalM;

        if(minutesS > minutesU)
        {
            hourU-=1;
            minutesU+=60;
            totalH= hourU-hourS;
            totalM = minutesU - minutesS;
        }
        else
        {
            totalH= hourU-hourS;
            totalM = minutesU - minutesS;
        }

        int totalSec = (60*totalM)+(3600*totalH);
        //notificationService(totalSec);
        Log.i("sekondat",String.valueOf(totalSec));
        todoListSQLHelper.addNewTask(todoTaskInput,String.valueOf(hourU)+":"+String.valueOf(minutesU));
        loadTodoList();
    }
}
