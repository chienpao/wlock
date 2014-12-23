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
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends ActionBarActivity {

    boolean flag;
    public Timer timer;
    public TimerTask timerTask;
    final Handler timerHandler = new Handler();
    public int randomNumber;
    public MediaPlayer mediaPlayer;
    private TextView textView;

    // 2 by 2 array button
    private Button[][] button = new Button[2][2];

    private int[][] rainbowRandomNumber = new int[2][2];

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
        textView = (TextView)findViewById(R.id.textView);

        initButtonColorAndStatus();

        button[0][0].setOnClickListener(myClickListener);
        button[0][1].setOnClickListener(myClickListener);
        button[1][0].setOnClickListener(myClickListener);
        button[1][1].setOnClickListener(myClickListener);
        StartCheckTimer();
    }

    private View.OnClickListener myClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2:
                    rainbowRandomNumber[0][0]++;
                    button[0][0].setBackgroundColor(transferNumber2Color(rainbowRandomNumber[0][0]));
                    mediaPlayer.start();
                    break;
                case R.id.button3:
                    rainbowRandomNumber[0][1]++;
                    button[0][1].setBackgroundColor(transferNumber2Color(rainbowRandomNumber[0][1]));
                    mediaPlayer.start();
                    break;
                case R.id.button4:
                    rainbowRandomNumber[1][0]++;
                    button[1][0].setBackgroundColor(transferNumber2Color(rainbowRandomNumber[1][0]));
                    mediaPlayer.start();
                    break;
                case R.id.button5:
                    rainbowRandomNumber[1][1]++;
                    button[1][1].setBackgroundColor(transferNumber2Color(rainbowRandomNumber[1][1]));
                    mediaPlayer.start();
                    break;
            }

        }
    };

    void initButtonColorAndStatus() {
        // Make Random number for init button color
        mediaPlayer = MediaPlayer.create(this, R.raw.dog);
        int Color;

        for (int i = 0; i < rainbowRandomNumber.length; i++) {
            for (int j = 0; j < rainbowRandomNumber[i].length; j++){
                rainbowRandomNumber[i][j] = RandomClass.getRandom(0, 7);
                Log.i("Pao", Integer.toString(i) + "," + Integer.toString(j) + "= " + Integer.toString(rainbowRandomNumber[i][j]));
                Color = transferNumber2Color(rainbowRandomNumber[i][j]);
                button[i][j].setBackgroundColor(Color);
            }
        }

        /* the button is disable in init
        button[0][0].setEnabled(false);
        button[0][1].setEnabled(false);
        button[1][0].setEnabled(false);
        button[1][1].setEnabled(false);*/
    }

    int transferNumber2Color(int value){
        switch(value){
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

    void StartCheckTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timerHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(rainbowRandomNumber[0][0] ==
                                rainbowRandomNumber[0][1] &&
                                rainbowRandomNumber[1][0] ==
                                rainbowRandomNumber[1][1] &&
                                rainbowRandomNumber[1][1] ==
                                        rainbowRandomNumber[0][0]
                                )textView.setText("Pass");
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 100);
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
