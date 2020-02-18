package com.example.DataPresistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intent.R;

public class MainActivity extends AppCompatActivity {

    Button next;
    EditText phoneText;
    EditText messageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = findViewById(R.id.btnNext);
        phoneText = findViewById(R.id.phoneText);
        messageText = findViewById(R.id.messageText);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(MainActivity.this, SecondActivity.class);
                sendIntent.putExtra("phone", phoneText.getText().toString());
                sendIntent.putExtra("message", messageText.getText().toString());
                startActivity(sendIntent);
            }
        });
    }

    public void close(View view) {
        finish();
    }
}
