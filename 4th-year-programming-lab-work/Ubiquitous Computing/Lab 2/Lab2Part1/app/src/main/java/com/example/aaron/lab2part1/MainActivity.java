package com.example.aaron.lab2part1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import java.lang.Object;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitHandler(View view) {

        EditText e1 = (EditText)findViewById(R.id.editText);
        String name = e1.getText().toString();

        Intent i = new Intent(this, Main3Activity.class);
        i.putExtra("Message", name);

        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }

    }
}
