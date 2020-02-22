package com.example.DataPresistance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intent.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "activity2";
    public static final String TAG = "SecondActivity";
    public static final String FILE_NAME = "myFile";
    private static final String SEPERATOR = "*";

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

    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        phoneText = findViewById(R.id.phoneText);
        messageText = findViewById(R.id.messageText);
        close = findViewById(R.id.btnClose);
        writeSharedPrefs = findViewById(R.id.btnWs);
        readSharedPrefs = findViewById(R.id.btnRs);
        writeInputStream = findViewById(R.id.btnWriteIS);
        readInputStream = findViewById(R.id.btnReadIS);
        writeSQL = findViewById(R.id.btnWriteeSQL);
        readSQL = findViewById(R.id.btnReadSQL);

        dataBaseAdapter = new DataBaseAdapter(SecondActivity.this);


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
                    fileOutputStream.write(SEPERATOR.getBytes());
                    fileOutputStream.write(messageText.getText().toString().getBytes());
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
                    String strData = new String(data);
                    int sepIndex = strData.indexOf('*');
                    phoneText.setText(strData.substring(0, sepIndex));
                    messageText.setText(strData.substring(sepIndex + 1, strData.length()));

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
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setName(messageText.getText().toString());
                contactDTO.setPhone(phoneText.getText().toString());

                if (dataBaseAdapter.insertContact(contactDTO) == -1) {
                    Toast.makeText(SecondActivity.this,
                            "couldn't insert in db. try again", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SecondActivity.this,
                            "Successfully inserted row in db", Toast.LENGTH_SHORT).show();
                }

                clearFields();
            }
        });

        readSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactDTO retContact = new ContactDTO();
                retContact = dataBaseAdapter.getLastContact();
                messageText.setText(retContact.getName());
                phoneText.setText(retContact.getPhone());
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
}
