package com.wlock.pao.wlock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends ActionBarActivity {

    boolean flag;
    public Timer timer;
    public TimerTask timerTask;
    final Handler timerhandler = new Handler();

    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        flag = false;

        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button2.setOnClickListener(myClickListener);
        button3.setOnClickListener(myClickListener);
        button4.setOnClickListener(myClickListener);
        button5.setOnClickListener(myClickListener);

        StartTimer();

    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2:
                    button2.setBackgroundColor(Color.RED);
                    break;
                case R.id.button3:
                    button3.setBackgroundColor(Color.RED);
                    break;
                case R.id.button4:
                    button4.setBackgroundColor(Color.RED);
                    break;
                case R.id.button5:
                    button5.setBackgroundColor(Color.RED);
                    break;
            }
        }
    };

    void StartTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timerhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        button2.setBackgroundColor(Color.BLACK);
                    }
                });
            }
        };
        timer.schedule(timerTask, 10000, 10000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
