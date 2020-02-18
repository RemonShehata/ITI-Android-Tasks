package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NumberAdapter extends ArrayAdapter<Number> {
    private Context context;
    private List<Number> numbers;
    private Number currentNumber;

    public NumberAdapter(@NonNull Context context, int resource, @NonNull List<Number> numbers) {
        super(context, resource, numbers);
        this.context = context;
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;
        LayoutInflater layoutInflater = (LayoutInflater)
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.custom_cell, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView title = view.findViewById(R.id.txtTitle);
        TextView desc = view.findViewById(R.id.txtDesc);

        this.currentNumber = this.numbers.get(position);
        imageView.setImageResource(currentNumber.getImg());
        title.setText(currentNumber.getTitle());
        desc.setText(currentNumber.getDesc());
        return view;
    }
}
