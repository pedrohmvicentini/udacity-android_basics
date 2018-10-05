package com.example.android.projeto6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pedro on 03/12/2017.
 */

public class TourAdapter extends ArrayAdapter<Tour> {
    public TourAdapter(Context context, ArrayList<Tour> attractions) {
        super(context, 0, attractions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Tour currentAttraction = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(currentAttraction.getTourDescription());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if (currentAttraction.hasImage()) {
            imageView.setImageResource(currentAttraction.gettourImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
