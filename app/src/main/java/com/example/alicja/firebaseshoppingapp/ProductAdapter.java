package com.example.alicja.firebaseshoppingapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

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
        }

        summaryTextview.setText(productDetails);

        return convertView;
    }
}
