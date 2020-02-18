package com.example.DataPresistance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intent.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SecondActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "activity2";
    public static final String TAG = "SecondActivity";
    public static final String FILE_NAME = "myFile";

    private String phone;
    private String message;

    private TextView phoneText;
    private TextView messageText;
    private Button close;
    private Button writeSharedPrefs;
    private Button readSharedPrefs;
    private Button writeInputStream;
    private Button readInputStream;
    private Button writeSQL;
    private Button readSQL;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        phoneText = findViewById(R.id.phoneText);
        messageText = findViewById(R.id.messageText);
        close = findViewById(R.id.btnClose);
        writeSharedPrefs = findViewById(R.id.btnWs);
        readSharedPrefs = findViewById(R.id.btnRs);
        writeInputStream = findViewById(R.id.btnReadIS);
        readInputStream = findViewById(R.id.btnReadIS);
        writeSQL = findViewById(R.id.btnWriteeSQL);
        readSQL = findViewById(R.id.btnReadSQL);


        Intent incoming = getIntent();
        if (incoming != null) {
            phone = incoming.getStringExtra("phone");
            message = incoming.getStringExtra("message");

            setDataOnFields();
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        writeSharedPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create sp with activity name
                //sharedPreferences = getPreferences(MODE_PRIVATE);
                //create sp with choosen name
                sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                editor = sharedPreferences.edit();

                editor.putString("phone", phone);
                editor.putString("message", message);

                editor.commit();
                clearFields();
            }
        });

        readSharedPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                phone = sharedPreferences.getString("phone", "N/A");
                message = sharedPreferences.getString("message", "Empty message");

                Log.d(TAG, phone);
                Log.d(TAG, message);
                setDataOnFields();
            }
        });


        writeInputStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fileOutputStream.write(phoneText.getText().toString().getBytes());
                    fileOutputStream.close();
                    clearFields();
                } catch (java.io.FileNotFoundException nfe) {
                    Log.e(TAG, Log.getStackTraceString(nfe));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        readInputStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = openFileInput(FILE_NAME);
                    byte[] data = new byte[fileInputStream.available()];
                    fileInputStream.read(data);
                    phoneText.setText(new String(data));

                    fileInputStream.close();
                } catch (java.io.FileNotFoundException nfe) {
                    Log.e(TAG, Log.getStackTraceString(nfe));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        writeSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        readSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void clearFields() {
        phoneText.setText("");
        messageText.setText("");
    }

    private void setDataOnFields() {
        phoneText.setText(this.phone);
        messageText.setText(this.message);
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
