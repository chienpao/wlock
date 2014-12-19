package com.wlock.pao.wlock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends ActionBarActivity {
    boolean test;
    boolean flag;
    public Timer timer;
    public TimerTask timerTask;
    final Handler timerhandler = new Handler();
    public int randomNumber;
    public Random random;

    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        randomNumber = 0;
        flag = false;

        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button2.setOnClickListener(myClickListener);
        button3.setOnClickListener(myClickListener);
        button4.setOnClickListener(myClickListener);
        button5.setOnClickListener(myClickListener);
        StartRandomAndTimer();

    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2:
                    button2.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.button3:
                    button3.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.button4:
                    button4.setBackgroundColor(Color.WHITE);
                    break;
                case R.id.button5:
                    button5.setBackgroundColor(Color.WHITE);
                    break;
                default:
                    button2.setBackgroundColor(Color.WHITE);
                    button3.setBackgroundColor(Color.WHITE);
                    button4.setBackgroundColor(Color.WHITE);
                    button5.setBackgroundColor(Color.WHITE);
                    break;
            }

        }
    };

    void StartRandomAndTimer(){
        random = new Random();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timerhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        randomNumber = 1 + random.nextInt(4);
                        switch(randomNumber){
                            case 1:
                                button2.setEnabled(true);
                                button2.setBackgroundColor(Color.RED);
                                break;
                            case 2:
                                button3.setEnabled(true);
                                button3.setBackgroundColor(Color.RED);
                                break;
                            case 3:
                                button4.setEnabled(true);
                                button4.setBackgroundColor(Color.RED);
                                break;
                            case 4:
                                button5.setEnabled(true);
                                button5.setBackgroundColor(Color.RED);
                                break;
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 2000, 2000);
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
