package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView phoneText;
    TextView messageText;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        phoneText = findViewById(R.id.phoneText);
        messageText = findViewById(R.id.messageText);
        close = findViewById(R.id.btnClose);

        String phone;
        String message;

        Intent incomming = getIntent();
        if(incomming != null) {
            phone = incomming.getStringExtra("phone");
            message = incomming.getStringExtra("message");

            phoneText.setText(phone);
            messageText.setText(message);
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
