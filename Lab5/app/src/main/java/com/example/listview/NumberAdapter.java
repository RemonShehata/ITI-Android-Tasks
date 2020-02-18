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

        View rowView = null;
        ViewHolder viewHolder = null;

        if (rowView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.custom_cell, parent, false);
            viewHolder = new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        this.currentNumber = this.numbers.get(position);
        viewHolder.getTitle().setText(currentNumber.getTitle());
        viewHolder.getDesc().setText(currentNumber.getDesc());
        viewHolder.getImg().setImageResource(currentNumber.getImg());

        return rowView;
    }

    public class ViewHolder {
        private View convertView;
        private TextView title;
        private TextView desc;
        private ImageView img;

        ViewHolder(View view) {
            this.convertView = view;
        }

        public TextView getTitle() {
            if (title == null) {
                title = convertView.findViewById(R.id.txtTitle);
            }
            return title;
        }

        public TextView getDesc() {
            if (desc == null) {
                desc = convertView.findViewById(R.id.txtDesc);
            }
            return desc;
        }

        public ImageView getImg() {
            if (img == null) {
                img = convertView.findViewById(R.id.imageView);
            }
            return img;
        }
    }
}
