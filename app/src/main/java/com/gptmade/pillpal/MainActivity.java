package com.gptmade.pillpal;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.gptmade.pillpal.databinding.ActivityMainBinding;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        //CREATE THE NOTIFICATION
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "medication_reminder")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Time to take your medication")
                .setContentText("Don't forget to take your medication")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Date now = new Date();
        int notificationId = Integer.parseInt(new SimpleDateFormat("MMddHHmmss",  Locale.US).format(now));
        notificationManager.notify(notificationId, builder.build());

    }
}