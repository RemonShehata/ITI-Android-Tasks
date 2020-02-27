package com.example.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    Button download;
    EditText urlEditText;
    ImageView imageView;
    String imageLink;
    URL url;
    Bitmap downloadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = findViewById(R.id.btnDownload);
        urlEditText = findViewById(R.id.txtUrl);
        imageView = findViewById(R.id.imageView);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLink = urlEditText.getText().toString();
                Log.d(TAG, "onClick: excute");
                new Downloader().execute(imageLink);
            }
        });
    }


    class Downloader extends AsyncTask<String, Void, Bitmap> {
        HttpsURLConnection connection;
        InputStream inputStream;

        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: start");
            try {
                url = new URL(strings[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                inputStream = connection.getInputStream();
                downloadedImage = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "doInBackground: end");
            return downloadedImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute: start");
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(MainActivity.this, "downloaded successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
