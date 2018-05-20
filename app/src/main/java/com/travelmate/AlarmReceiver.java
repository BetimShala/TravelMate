package com.travelmate;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import com.travelmate.ToDoActivity;

/**
 * Created by Betim on 5/13/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    ToDoActivity toDoActivity;
    //String message = toDoActivity.getTodoTaskInput();
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context,ToDoActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(ToDoActivity.class);
        taskStackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationBuilder =(NotificationCompat.Builder)new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle("Notification from TravelMate")
                .setContentText("Plani eshte ky : test");

        NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
