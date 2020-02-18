package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {

    private List<Number> numbers;
    private final Context context;
    private Number currentNumber;

    public NumberAdapter(Context context, List<Number> dataSet) {
        this.context = context;
        this.numbers = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTitle;
        public TextView textDesc;
        public ImageView imageView;
        public LinearLayout linearLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            textTitle = itemView.findViewById(R.id.txtTitle);
            textDesc = itemView.findViewById(R.id.txtDesc);
            imageView = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.row);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.currentNumber = this.numbers.get(position);
        holder.textTitle.setText(currentNumber.getTitle());
        holder.textDesc.setText(currentNumber.getDesc());
        holder.imageView.setImageResource(currentNumber.getImg());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, currentNumber.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
