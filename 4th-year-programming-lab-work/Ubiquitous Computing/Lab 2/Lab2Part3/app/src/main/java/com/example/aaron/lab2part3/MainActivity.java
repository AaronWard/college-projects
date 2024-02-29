package com.example.aaron.lab2part3;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import javax.net.ssl.HttpsURLConnection;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    EditText et;
    ImageView img;
    Button retrieve;
    String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        img = (ImageView)findViewById(R.id.imageView);
        retrieve = (Button)findViewById(R.id.button);


        /**
         * Button listener for connecting to the network after being clicked
         */
        retrieve.setOnClickListener((new OnClickListener() {
            public void onClick(View v) {
                url = et.getText().toString();
                ConnectivityManager connect = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo net = connect.getActiveNetworkInfo();

                /**
                 * Execute the thread class
                 */
                if(net != null && net.isConnected()){
                    new LookUpImage().execute(url);
                }else{
                    System.out.println("NOT CONNECTED");
                }
            }
        }));
    }

    /**
     * Thread inner class for image search using Async
     */
    private class LookUpImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {}

        /**
         * Take in params and set url to the first index of the string array
         * @param urls
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                return loadImageFromNetwork(urls[0]);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(Integer... values) {}

        @Override
        protected void onPostExecute(Bitmap s) {
            img.setImageBitmap(s);
        }

        /**
         * Look up image of the URL
         * @param url
         * @return
         * @throws IOException
         */
        private Bitmap loadImageFromNetwork(String url) throws IOException {
            String DEBUG_TAG = "Lab2Part3";
            InputStream is = null;
            try {

                URL url2 = new URL(url);
                HttpURLConnection conn = (HttpsURLConnection) url2.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                is = conn.getInputStream();

                bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }finally {
                if(is != null){
                    is.close();
                }
            }
        }
    }
}
