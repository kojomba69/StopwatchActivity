package com.example.edu.stopwatchactivity;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        TextView textView;
        Button btStart,btPause,btReset;
        Handler handler = new Handler();
        long startTime, timeBuff, millisecondTime, updateTime;
        private int seconds, minutes, milliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStart=findViewById(R.id.btStart);
        btStart.setOnClickListener(this);
        btPause=findViewById(R.id.btPause);
        btPause.setOnClickListener(this);
        btReset=findViewById(R.id.btReset);
        btReset.setOnClickListener(this);
        textView=findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btStart:
                startTime=SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);//0은 무한대로 루핑돌게
                break;

            case R.id.btPause:
                timeBuff+=millisecondTime;
                handler.removeCallbacks(runnable);
                break;

            case R.id.btReset:
                handler.removeCallbacks(runnable);
                timeBuff = 0; startTime =0;
                textView.setText("0:00:000");
                break;
        }


    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime=SystemClock.uptimeMillis()-startTime;
            updateTime=timeBuff+millisecondTime;
            seconds=(int)(updateTime/1000);
            minutes=seconds/60;
            seconds=seconds%60;
            milliSeconds=(int)(updateTime%1000);
            textView.setText(minutes+":"+String.format("%02d",seconds)+":"+String.format("%03d",milliSeconds));
            handler.postDelayed(this,0);
        }
    };
}
