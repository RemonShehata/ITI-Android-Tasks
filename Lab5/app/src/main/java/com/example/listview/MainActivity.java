package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    //String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    Number[] numbersArray = {new Number("one", "first Desc", R.drawable.num1),
            new Number("two", "second Desc", R.drawable.num2),
            new Number("three", "third Desc", R.drawable.num3),
            new Number("four", "fourth Desc", R.drawable.num4),
            new Number("five", "fifth Desc", R.drawable.num5),
            new Number("six", "sixth Desc", R.drawable.num6),
            new Number("seven", "seventh Desc", R.drawable.num7),
            new Number("eight", "eights Desc", R.drawable.num8),

            new Number("one", "first Desc", R.drawable.num1),
            new Number("two", "second Desc", R.drawable.num2),
            new Number("three", "third Desc", R.drawable.num3),
            new Number("four", "fourth Desc", R.drawable.num4),
            new Number("five", "fifth Desc", R.drawable.num5),
            new Number("six", "sixth Desc", R.drawable.num6),
            new Number("seven", "seventh Desc", R.drawable.num7),
            new Number("eight", "eights Desc", R.drawable.num8),

            new Number("one", "first Desc", R.drawable.num1),
            new Number("two", "second Desc", R.drawable.num2),
            new Number("three", "third Desc", R.drawable.num3),
            new Number("four", "fourth Desc", R.drawable.num4),
            new Number("five", "fifth Desc", R.drawable.num5),
            new Number("six", "sixth Desc", R.drawable.num6),
            new Number("seven", "seventh Desc", R.drawable.num7),
            new Number("eight", "eights Desc", R.drawable.num8)};

    List<Number> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.myList);
        numbers = Arrays.asList(numbersArray);
        //final ArrayAdapter<Number> adapter = new ArrayAdapter<>(this,
        //R.layout.custom_cell, R.id.txtTitle, days);

        NumberAdapter adapter = new NumberAdapter(this, R.layout.custom_cell, numbers);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
