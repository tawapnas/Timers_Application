package com.example.sanpawatsewsuwan.timer_application;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mySecond1,mySecond2,myMinute1,myMinute2;
    public int cnt;
    private SeekBar mySeekBar;

    public void setValue(int second){
        mySecond2.setText(String.valueOf(second%10));
        mySecond1.setText(String.valueOf((second%60)/10));
        myMinute2.setText(String.valueOf((second%600)/60));
        myMinute1.setText(String.valueOf(second/600));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySecond1 = (TextView)findViewById(R.id.mySecond1);
        mySecond2 = (TextView)findViewById(R.id.mySecond2);
        myMinute1 = (TextView)findViewById(R.id.myMinute1);
        myMinute2 = (TextView)findViewById(R.id.myMinute2);

        mySeekBar = (SeekBar)findViewById(R.id.mySeekBar);
        mySeekBar.setMax(700);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                cnt=i;
                setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("Start","Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Finish","Finish");
            }
        });

    }
    public void onStartTime(View view){
        new CountDownTimer(cnt*1000,1000){
            @Override
            public void onTick(long l) {
                cnt-=1;
                setValue(cnt);
                mySeekBar.setEnabled(false);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this,"Time's up!",Toast.LENGTH_SHORT).show();
                mySeekBar.setEnabled(true);
                mySeekBar.setProgress(0);
            }
        }.start();
    }
}
