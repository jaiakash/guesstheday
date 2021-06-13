package com.amostrone.akash.gtd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    static TextView date_text,score_text,high_text,timer_text;
    Button[] opt_butt = new Button[4];
    static String ans;
    static String week[]={"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};
    private int Score=0;
    ConstraintLayout home;
    private int highScore = 0;
    public int counter;

    SharedPreferences sharedPref;
    SharedPreferences.Editor SP_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opt_butt[0]=findViewById(R.id.button1);
        opt_butt[1]=findViewById(R.id.button2);
        opt_butt[2]=findViewById(R.id.button3);
        opt_butt[3]=findViewById(R.id.button4);
        date_text = findViewById(R.id.text_date);
        score_text = findViewById(R.id.score);
        home = findViewById(R.id.home);
        high_text = findViewById(R.id.high_score);
        timer_text = findViewById(R.id.timer);

        sharedPref = getSharedPreferences("High Score", MODE_PRIVATE);
        SP_edit = sharedPref.edit();

        highScore = sharedPref.getInt("High Score", 0);
        high_text.setText("High Score : "+highScore);

        String strDate = setDate_Text();
        date_text.setText(strDate);
        score_text.setText("Start Game");
        //Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();

        make_option();
    }

    public void make_option(){
        String opt[]={ans,"","",""};
        int i=1;
        while(i<4){
            String temp=week[randBetween(0,6)].substring(0,3);
            if(opt[0].equals(temp) || opt[1].equals(temp) || opt[2].equals(temp) || opt[3].equals(temp))
                continue;
            opt[i++]=temp;
        }

        ArrayList<Integer> rand_l = new ArrayList<Integer>();
        for (i=0;i<4;i++) {
            rand_l.add(i);
        }
        Collections.shuffle(rand_l);
        for(i=0;i<4;i++)
            opt_butt[rand_l.get(i)].setText(opt[i]);
    }

    String setDate_Text(){

        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2100);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String month_name = month_date.format(gc.getTime());
        String dt = ( gc.get(gc.DAY_OF_MONTH)+ " " + (month_name) + " " + gc.get(gc.YEAR));

        //Getting Answer as 3 word eg Wed, Thu
        try {
            Date date = new SimpleDateFormat("d MMM yyyy").parse(dt);
            ans= date.toString().substring(0,4);
            Log.i("Answers ", ans+"");
        } catch (ParseException e) {
            Log.e("Date conversion","ParseException occured: " + e.getMessage());
        }

        return dt;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    //Declare timer
    CountDownTimer cTimer = null;

    //start timer function
    void startTimer() {
        counter=10;
        cTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer_text.setText("Timer : "+counter);
                counter--;
                if(counter<0) {
                    //Log.i("Answers", counter+"");
                    gameEnd();
                }
            }
            public void onFinish() {
                //Toast.makeText(MainActivity.this, "Times Up", Toast.LENGTH_SHORT).show();
                timer_text.setText("Times Up");
                gameEnd();
                //Score=0;
            }
        };
        cTimer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(cTimer!=null) {
            cTimer.cancel();
        }
    }

    void gameEnd(){

        //High score
        highScore=Math.max(highScore,Score);
        high_text.setText("High Score : "+highScore);

        score_text.setText("Game Ended");

        //Saving high Score to Shared prefrences
        // write all the data entered by the user in SharedPreference and apply
        SP_edit.putInt("High Score", highScore);
        SP_edit.apply();

        cancelTimer();

        //Toast.makeText(this, "Game Ended, Your Score is "+ (Score+1), Toast.LENGTH_SHORT).show();
        Score=0;
        home.setBackgroundColor(getResources().getColor(R.color.white));
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        //Change color of home layout
        home.setBackgroundColor(getResources().getColor(R.color.wrong));

        switch (view.getId()) {
            case R.id.button1:
                if(opt_butt[0].getText().toString().equals(ans)){
                    Score++;
                    home.setBackgroundColor(getResources().getColor(R.color.right));
                }
                else {
                    Score--;
                }
                //Log.e("Options", opt_butt[0].getText().toString()+" "+ans);
                break;
            case R.id.button2:
                if(opt_butt[1].getText().equals(ans)){
                    Score++;
                    home.setBackgroundColor(getResources().getColor(R.color.right));
                }
                else {
                    Score--;
                }
                break;
            case R.id.button3:
                if(opt_butt[2].getText().equals(ans)){
                    Score++;
                    home.setBackgroundColor(getResources().getColor(R.color.right));
                }
                else {
                    Score--;
                }
                break;
            case R.id.button4:
                if(opt_butt[3].getText().equals(ans)){
                    Score++;
                    home.setBackgroundColor(getResources().getColor(R.color.right));
                }
                else {
                    Score--;
                }
                break;
        }

        Log.i("Score", Score+"");

        startTimer();

        String strDate = setDate_Text();
        date_text.setText(strDate);

        score_text.setText("Score : "+Score);

        if(Score<=0) gameEnd();

        make_option();
        //Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, Integer.toString(Score), Toast.LENGTH_SHORT).show();
    }
}