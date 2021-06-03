package com.amostrone.akash.gtd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    static TextView date_text;
    static String ans;
    static String week[]={"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_text = findViewById(R.id.text_date);
        String strDate = setDate_Text();
        date_text.setText(strDate);
        Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();


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

        //Toast.makeText(this, dt, Toast.LENGTH_SHORT).show();

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

        Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
        String strDate = setDate_Text();
        date_text.setText(strDate);
        switch (view.getId()) {
            case R.id.button1:
                date_text.setText(strDate);
                break;
            case R.id.button2:
                date_text.setText(strDate);
                break;
            case R.id.button3:
                date_text.setText(strDate);
                break;
            case R.id.button4:
                date_text.setText(strDate);
                break;
        }
    }
}