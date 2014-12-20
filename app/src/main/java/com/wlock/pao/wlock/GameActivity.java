package com.wlock.pao.wlock;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends ActionBarActivity {

    boolean flag;
    public Timer timer;
    public TimerTask timerTask;
    final Handler timerHandler = new Handler();
    public int randomNumber;
    public Random random;
    public MediaPlayer mediaPlayer;

    // 2 by 2 array button
    private Button[][] button = new Button[2][2];

    private Random rainbowRandom;
    private int rainbowRandomNumber;
    private int[][] rainbowArr = new int[2][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        randomNumber = 0;
        flag = false;

        button[0][0] = (Button)findViewById(R.id.button2);
        button[0][1] = (Button)findViewById(R.id.button3);
        button[1][0] = (Button)findViewById(R.id.button4);
        button[1][1] = (Button)findViewById(R.id.button5);

        initButtonColorAndStatus();

        button[0][0].setOnClickListener(myClickListener);
        button[0][1].setOnClickListener(myClickListener);
        button[1][0].setOnClickListener(myClickListener);
        button[1][1].setOnClickListener(myClickListener);
        StartRandomAndTimer();
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2:
                    button[0][0].setBackgroundColor(Color.WHITE);
                    mediaPlayer.start();
                    button[0][0].setEnabled(false);
                    break;
                case R.id.button3:
                    button[0][1].setBackgroundColor(Color.WHITE);
                    mediaPlayer.start();
                    button[0][1].setEnabled(false);
                    break;
                case R.id.button4:
                    button[1][0].setBackgroundColor(Color.WHITE);
                    mediaPlayer.start();
                    button[1][0].setEnabled(false);
                    break;
                case R.id.button5:
                    button[1][1].setBackgroundColor(Color.WHITE);
                    mediaPlayer.start();
                    button[1][1].setEnabled(false);
                    break;
            }

        }
    };

    void initButtonColorAndStatus() {
        // Make Random number for init button color
        rainbowRandomNumber = 0;
        rainbowRandom = new Random();
        mediaPlayer = MediaPlayer.create(this, R.raw.dog);
        int Color;

        for (int i = 0; i < rainbowArr.length; i++) {
            for (int j = 0; j < rainbowArr[i].length; j++){
                rainbowRandomNumber = 1 + rainbowRandom.nextInt(7);
                rainbowArr[i][j] = rainbowRandomNumber;
                Log.i("Pao", Integer.toString(i) + "," + Integer.toString(j) + "= " + Integer.toString(rainbowRandomNumber));
                Color = transferNumber2Color();
                button[i][j].setBackgroundColor(Color);
            }
        }

        // the button is disable in init
        button[0][0].setEnabled(false);
        button[0][1].setEnabled(false);
        button[1][0].setEnabled(false);
        button[1][1].setEnabled(false);
    }

    int transferNumber2Color(){
        switch(rainbowRandomNumber){
            case 1:
                return Color.RED;
            case 2:
                return Color.argb(48, 255, 205, 0);
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLUE;
            case 6:
                return Color.argb(272, 137, 0, 255);
            case 7:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    void StartRandomAndTimer(){
        random = new Random();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timerHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        randomNumber = 1 + random.nextInt(4);
                        switch(randomNumber){
                            case 1:
                                button[0][0].setEnabled(true);
                                button[0][0].setBackgroundColor(Color.RED);
                                break;
                            case 2:
                                button[0][1].setEnabled(true);
                                button[0][1].setBackgroundColor(Color.RED);
                                break;
                            case 3:
                                button[1][0].setEnabled(true);
                                button[1][0].setBackgroundColor(Color.RED);
                                break;
                            case 4:
                                button[1][1].setEnabled(true);
                                button[1][1].setBackgroundColor(Color.RED);
                                break;
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000);
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

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
