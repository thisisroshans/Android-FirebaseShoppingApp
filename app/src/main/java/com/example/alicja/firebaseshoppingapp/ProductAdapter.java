package com.example.alicja.firebaseshoppingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.alicja.firebaseshoppingapp.BasicActivity.colorFromPreferences;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.firstColorId;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.secondColorId;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.sizeFromPreferences;
import static com.example.alicja.firebaseshoppingapp.BasicActivity.thirdColorId;

/**
 * Created by Alicja on 05.01.2018.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private Map<String, Product> keysWithProducts = new HashMap<>();

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

    @Override
    public void add(@Nullable Product object) {
        super.add(object);
        keysWithProducts.put(object.getId(), object);
    }

    public Product findProductById (String id) {
        return keysWithProducts.get(id);
    }

    @Override
    public void remove(@Nullable Product object) {
        String id = object.getId();
        super.remove(object);
        keysWithProducts.remove(id);

    }
}
