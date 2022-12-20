package com.gptmade.pillpal;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import com.gptmade.pillpal.databinding.ActivityMainBinding;


public class MainActivity extends Activity {
    
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Set the alarm to start at the specified time and repeat at the specified interval
        long interval = 6000; // 60 seconds
        long triggerTime = SystemClock.elapsedRealtime() + interval;
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, interval, pendingIntent);

        //Create a High Importance notification Channel
        NotificationChannel channel = new NotificationChannel(
                "medication_reminder",
                "medications_channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Notifications to remind you to take your medication");
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManagerService = getSystemService(NotificationManager.class);
        notificationManagerService.createNotificationChannel(channel);
    }
}