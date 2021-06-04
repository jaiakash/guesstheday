package com.amostrone.akash.gtd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
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
    static TextView date_text,score_text;
    Button opt_butt[]= new Button[4];
    static String ans;
    static String week[]={"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};
    private int Score=0;

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

        String strDate = setDate_Text();
        date_text.setText(strDate);
        score_text.setText("Score : "+Score);
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

    public static String setDate_Text(){

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
        } catch (ParseException e) {
            Log.e("Date conversion","ParseException occured: " + e.getMessage());
        }

        return dt;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        //Vibration
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);

        //Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.button1:
                if(opt_butt[0].getText().toString().equals(ans))
                    Score++;
                //Log.e("Options", opt_butt[0].getText().toString()+" "+ans);
                break;
            case R.id.button2:
                if(opt_butt[1].getText().equals(ans))
                    Score++;
                break;
            case R.id.button3:
                if(opt_butt[2].getText().equals(ans))
                    Score++;
                break;
            case R.id.button4:
                if(opt_butt[3].getText().equals(ans))
                    Score++;
                break;
        }

        String strDate = setDate_Text();
        date_text.setText(strDate);

        score_text.setText("Score : "+Score);

        make_option();
        //Toast.makeText(this, Integer.toString(Score), Toast.LENGTH_SHORT).show();
    }
}