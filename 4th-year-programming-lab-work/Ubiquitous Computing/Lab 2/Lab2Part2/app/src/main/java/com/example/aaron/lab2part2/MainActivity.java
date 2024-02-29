package com.example.aaron.lab2part2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.net.*;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitHandler(View view) {

        //Take in email address from user
        EditText e2 = (EditText)findViewById(R.id.editText3);
        String email = e2.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto"));
        //Set send contact to the email given
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        i.putExtra(Intent.EXTRA_SUBJECT, "Test");
        i.putExtra(Intent.EXTRA_TEXT, "this is a test");
        i.setType("text/plain"); // "text/plain" MIME type

        //Check if an email app exists
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        } else{
            System.out.println("Could not start activity");
        }

    }
}
