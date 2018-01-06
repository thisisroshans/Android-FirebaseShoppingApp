package com.example.alicja.firebaseshoppingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.alicja.firebaseshoppingapp.BasicActivity.colorFromPreferences;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.firstColorId;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.secondColorId;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.sizeFromPreferences;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.thirdColorId;

/**
 * Created by Alicja on 05.01.2018.
 */

public class ProductAdapter extends ArrayAdapter<Product> {


    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.product_list_item, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView summaryTextview = (TextView) convertView.findViewById(R.id.summary);

        Product product = getItem(position);

        // filling product name:
        nameTextView.setText(product.getName());

        // filling product details:
        String productDetails = "Price: " + product.getPrice() + " PLN, " + " Quantity: " + product.getQuantity();

        if (product.getIsBought()) {
            productDetails =  "\u2713" + productDetails;
            convertView.setBackgroundColor(Color.parseColor("#D3D3D3"));
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        summaryTextview.setText(productDetails);

        //change color
        if(colorFromPreferences == firstColorId) {
            nameTextView.setTextColor(Color.BLACK);
        } else if (colorFromPreferences == secondColorId) {
            nameTextView.setTextColor(Color.rgb(255, 102, 153));
        } else if (colorFromPreferences == thirdColorId) {
            nameTextView.setTextColor(Color.rgb(95, 104, 206));
        }

        //change size
        if(sizeFromPreferences == 0) {
            nameTextView.setTextSize(14);
        } else if (sizeFromPreferences ==1){
            nameTextView.setTextSize(20);
        }


        return convertView;
    }


}
