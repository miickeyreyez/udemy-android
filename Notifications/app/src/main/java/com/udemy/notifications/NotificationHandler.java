package com.udemy.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

/**
 * Created by INSPIRON on 23/5/2018.
 */

public class NotificationHandler extends ContextWrapper {

    private NotificationManager notificationManager;
    public static final  String CHANNEL_HIGH_ID = "1";
    public static final  String CHANNEL_LOW_ID = "2";
    private final  String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    private final  String CHANNEL_LOW_NAME = "LOW CHANNEL";

    public NotificationHandler(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        if(Build.VERSION.SDK_INT >= 26) {
            //Creating high channel
            NotificationChannel highChannel = new NotificationChannel(CHANNEL_HIGH_ID,CHANNEL_HIGH_NAME, NotificationManager.IMPORTANCE_HIGH);
            //Extra config
            highChannel.enableLights(true);
            highChannel.setLightColor(Color.YELLOW);
            highChannel.setShowBadge(true);
            highChannel.enableVibration(true);
            //highChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 100, 200, 300, 400});
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            Uri defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            //highChannel.setSound(defaultUri,null);

            NotificationChannel lowChannel = new NotificationChannel(CHANNEL_LOW_ID,CHANNEL_LOW_NAME,NotificationManager.IMPORTANCE_LOW);

            getNotificationManager().createNotificationChannel(highChannel);
            getNotificationManager().createNotificationChannel(lowChannel);
        }
    }

    public NotificationManager getNotificationManager() {
        if(notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public Notification.Builder createNotification(String title, String message, boolean isHighImportance) {
        if(Build.VERSION.SDK_INT >= 26) {
            if(isHighImportance) {
                return this.createNotificationWithChannel(title,message,CHANNEL_HIGH_ID);
            } else {
                return this.createNotificationWithChannel(title,message,CHANNEL_LOW_ID);
            }
        }
        return this.createNotificationWithOutChannel(title,message);
    }

    @SuppressLint("ResourceAsColor")
    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(getApplicationContext(),EmptyActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("message",message);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_CANCEL_CURRENT);

            Notification.Action action = new Notification.Action.Builder(
                    Icon.createWithResource(this,android.R.drawable.ic_menu_add),
                    "See details",
                    pendingIntent).build();

            return new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    //.setContentIntent(pendingIntent)
                    .addAction(action)
                    .setColor(R.color.colorPrimary)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
        }
        return null;
    }

    private Notification.Builder createNotificationWithOutChannel(String title, String message) {
        Intent intent = new Intent(getApplicationContext(),EmptyActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("message",message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true);
    }
}
