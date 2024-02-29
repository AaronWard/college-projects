package com.example.aaron.lab2part1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String name = getIntent().getExtras().getString("Message");

        String toastMessage = "Thank you " + name + ", Your request is being processed";

        TextView t = (TextView)findViewById(R.id.intentTextView);
        t.setText(toastMessage);
    }
}
