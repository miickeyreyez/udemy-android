package com.udemy.notifications;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextTitle)
    EditText editTextTitle;
    @BindView(R.id.editTextMessage)
    EditText editTextMessage;
    @BindView(R.id.switchNotifications)
    Switch aSwitch;
    @BindView(R.id.button)
    Button buttonSend;
    @BindString(R.string.switchNotificationsOn) String on;
    @BindString(R.string.switchNotificationsOff) String off;

    private boolean isHighImportance = false;
    private NotificationHandler notificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Right after setContentView
        ButterKnife.bind(this);
        notificationHandler = new NotificationHandler(this);
    }

    @OnClick(R.id.button)
    public void click() {
        sendNotification();
    }

    @OnCheckedChanged(R.id.switchNotifications)
    public void change(CompoundButton compoundButton, boolean b){
        isHighImportance = b;
        aSwitch.setText((b) ? on : off);
    }

    private void sendNotification(){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            Notification.Builder nb = notificationHandler.createNotification(title, message, isHighImportance);
            notificationHandler.getNotificationManager().notify(1,nb.build());
        }
    }
}
