package com.example.aaron.lab1part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitHandler(View view) {

        EditText e1 = (EditText)findViewById(R.id.editText);
        String name = e1.getText().toString();

        String toastMessage = "Thank you " + name + ", your request is being processed";

        TextView t = (TextView)findViewById(R.id.textView6);
        t.setText(toastMessage);

    }
}
