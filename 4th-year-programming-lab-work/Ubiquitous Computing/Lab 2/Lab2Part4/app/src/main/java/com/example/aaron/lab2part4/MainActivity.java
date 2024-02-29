package com.example.aaron.lab2part4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.database.sqlite.*;
import android.database.Cursor;
import android.content.Context;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText listName;
    EditText listBox;
    TextView bannerText;
    Button create, retrieve, clear, save, delete;
    SQLiteDatabase db;
    Cursor c;


    String currentList;
    String listContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listName = (EditText)findViewById(R.id.editText);
        listBox = (EditText)findViewById(R.id.editText2);
        create = (Button)findViewById(R.id.createButton);
        retrieve = (Button)findViewById(R.id.retrieveButton);
        clear = (Button)findViewById(R.id.clearButton);
        save = (Button)findViewById(R.id.saveButton);
        delete = (Button)findViewById(R.id.deleteButton);
        bannerText = (TextView) findViewById(R.id.textView);

        db=openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);

//        db.execSQL("DROP TABLE IF EXISTS list");/// DELETE THIS WHEN NOT TESTING, used to stop memory consumption
        db.execSQL("CREATE TABLE IF NOT EXISTS list(listname VARCHAR, listcontent VARCHAR);");

        //Listener for the create button for making a new list
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentList = listName.getText().toString();
                listContents = listBox.getText().toString();

                //If the user tries to create a list without a name
                if(currentList.equals("")){
                    dialogAlert("Can not create a list with no name.");
                }
                //if the user tries to create a list without any contents
                else if(listContents.equals("")){
                    dialogAlert("Can not create a list with no contents");
                }else{
                    //add the list name and contents into the DB
                    db.execSQL("INSERT INTO list (listname, listcontent)" +
                            " VALUES('"+ currentList+"', '" + listContents + "');");

                    bannerText.setText(currentList+ " has been created and stored in the database");
                    listName.setText("");
                    listBox.setText("");
                }
            }
        });

        //Listener for the retrieve button to view lists
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentList = listName.getText().toString();
                listContents = listBox.getText().toString();

                //If the user tries to search without a name entered
                if(currentList.equals("")){
                    dialogAlert("Can not retrieve a list without a name");
                }
                else{
                    //Get the list contents where the searchs list name is
                    //that of one in the DB
                    c = db.rawQuery("SELECT listcontent FROM list WHERE listname ='" + currentList + "'", null);

                    bannerText.setText("Searching for list: " + currentList);

                    //If there is no contents, then it does not exist
                    if(c.getCount() == 0){
                        dialogAlert("List does not exist");
                    }
                    else{
                        ArrayList<String> listList=new ArrayList<String>();
                        //Add list contents to the Array list
                        while (c.moveToNext()) {
                            listList.add(c.getString(c.getColumnIndex("listcontent")));
                        }

                        //Print contents to the edit text
                        for(int i = 0; i < listList.size(); i++){
                            listBox.append(listList.get(i) + "\n");
                        }
                        c.close();
                    }
                }
            }
        });

        //Listener for the clear button for deleting a list
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listContents = listBox.getText().toString();

                //if the user tries to clear an empty field
                if(listContents.equals("")){
                    dialogAlert("There is nothing to clear");
                }else{
                    listBox.setText("");
                    bannerText.setText("Text cleared, enter new list contents & save the changes");
                }
            }
        });

        //listener for save button for saving current state of a list
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentList = listName.getText().toString();
                listContents = listBox.getText().toString();
                c = db.rawQuery("SELECT * FROM list WHERE listname= '"+ currentList + "'", null);

                //Make sure the user enters a list name
                if(currentList.equals("")){
                    dialogAlert("Enter a list name to save to");
                }
                //Check if the list they are trying to save to exists
                else if(c.getCount() == 0){
                    dialogAlert("You can not save to a list you have not created");
                }
                //Contents checking
                else if(listContents.equals("")){
                    dialogAlert("There is nothing to save");
                }else{
                    //Update the table with the new list contents
                    db.execSQL("UPDATE list" +
                            " SET listcontent = '"+  listContents + "' " +
                            "WHERE listname= '" + currentList + "';");
                    bannerText.setText("Your changes have been saved");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentList = listName.getText().toString();
                listContents = listBox.getText().toString();

                c = db.rawQuery("SELECT * FROM list WHERE listname= '"+ currentList + "'", null);

                if(currentList.equals("")){
                    dialogAlert("Please select a list to delete");
                }
                else if(c.getCount() == 0){
                    dialogAlert("You can not delete to a list you have not created");
                }
                else{
                    db.execSQL("DELETE FROM list" +
                            " WHERE listname= '" + currentList + "';");

                    listBox.setText("");
                    listName.setText("");
                    bannerText.setText(currentList +" has been deleted");
                }
            }
        });
    }

    /**
     * An alert dialog box that is called when user makes
     * a mistake.
     * @param string
     */
    public void dialogAlert(String string){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(string);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
