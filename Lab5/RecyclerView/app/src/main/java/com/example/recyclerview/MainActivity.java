package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Number> numbers;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        numbers = Arrays.asList(numbersArray);
        System.out.println(numbers + "");
        adapter = new NumberAdapter(this, numbers);
        recyclerView.setAdapter(adapter);

    }
}
