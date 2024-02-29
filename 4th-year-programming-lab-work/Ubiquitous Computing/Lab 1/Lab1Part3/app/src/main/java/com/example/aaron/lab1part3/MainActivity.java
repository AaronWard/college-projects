package com.example.aaron.lab1part3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);

        setRandom();
    }

    public void ButtonOneClick(View view) {

        int buttonOneValue = Integer.parseInt(b1.getText().toString());
        int buttonTwoValue = Integer.parseInt(b2.getText().toString());

        if(buttonOneValue > buttonTwoValue){
            correctAnswer();
        }
        else{
            TextView t = (TextView)findViewById(R.id.textView);
            t.setText(t.getText() + "\n\n WRONG!!");
        }
    }

    public void ButtonTwoClick(View view) {

        int buttonOneValue = Integer.parseInt(b1.getText().toString());
        int buttonTwoValue = Integer.parseInt(b2.getText().toString());

        if(buttonOneValue < buttonTwoValue){
            correctAnswer();
        }
        else{
            TextView t = (TextView)findViewById(R.id.textView);
            t.setText(t.getText() + "\n\n WRONG!!");
        }
    }

    public void correctAnswer(){
        count++;
        TextView t = (TextView)findViewById(R.id.textView);
        t.setText("Score: " + count);
        setRandom();
    }


    public void setRandom(){
        Random r1 = new Random();

        b1.setText(r1.nextInt(10) + 1 + "");
        b2.setText(r1.nextInt(10) + 1 + "");
    }

}