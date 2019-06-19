package com.hp.hp.jobschedulerapi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

public class POPUP extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    MediaPlayer mp;
    Context context = this;
    EditText hr,min;
    Switch set;
    int hour,minute;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mediaPlayer=MediaPlayer.create(context,R.raw.bgm);
        mediaPlayer.start();
       // mediaPlayer.getDuration();

    }

    @Override
    public void onBackPressed() {

        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();

        finish();
        //
    }
}
