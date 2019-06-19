package com.hp.hp.jobschedulerapi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    MediaPlayer mp;
    Context context = this;
    EditText hr,min;
    Switch set;
    int hour,minute;
    MediaPlayer mediaPlayer;

//https://markojerkic.com/background-task-how-to-start-an-activity-at-a-certain-time/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hr=findViewById(R.id.hour);
        min=findViewById(R.id.minute);
        set=findViewById(R.id.switch1);

//        MediaPlayer mediaPlayer=MediaPlayer.create(context,R.raw.bgm);
//        mediaPlayer.start();
//        mediaPlayer.getDuration();


        set.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    hour=Integer.parseInt(hr.getText().toString());
                    minute=Integer.parseInt(min.getText().toString());
                    setAlarm();

                }else {

                }


            }
        });

//        mp = MediaPlayer.create(context, R.raw.bgm);
//
//        try {
//            if (mp.isPlaying()) {
//                mp.stop();
//                mp.release();
//                mp = MediaPlayer.create(context, R.raw.bgm);
//            } mp.start();
//        } catch(Exception e) { e.printStackTrace(); }

//        Button stop =findViewById(R.id.stop);
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(mp.isPlaying())
//                {
//                    mp.stop();
//                    mp.release();
//                }
//                else{
//                    Toast.makeText(context, "No media playind", Toast.LENGTH_SHORT).show();
//                }
//                mp.stop();
//
//            }
//        });




        if (getIntent().getBooleanExtra("lock", false)) {
            // These next few lines of code open a window with the MainActivity
            // evan if the device is locked
            Window win = this.getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
            win.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

            //coming from wake lock  alarm recieved.

         mediaPlayer=MediaPlayer.create(context,R.raw.bgm);
        mediaPlayer.start();
        mediaPlayer.getDuration();

            //startActivity(new Intent(MainActivity.this,POPUP.class));
        }

        alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    }
    public void setAlarm() {
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 10000, pendingIntent);


        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);


// With setInexactRepeating(), you have to use one of the AlarmManager interval
// constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);



    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            finish();
        }


    }
}