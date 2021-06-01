package com.amostrone.akash.gtd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView date_text = findViewById(R.id.text_date);
        date_text.setText("Akash");
    }

    /** Called when the user touches the button */
    public void sendMessage(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(80);
        switch (view.getId()) {
            case R.id.button1:
                Toast.makeText(getApplicationContext(), "Button 1 was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(getApplicationContext(), "Button 2 was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(getApplicationContext(), "Button 3 was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Toast.makeText(getApplicationContext(), "Button 4 was clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}