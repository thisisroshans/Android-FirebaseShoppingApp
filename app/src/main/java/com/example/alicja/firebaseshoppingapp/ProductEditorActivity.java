package com.example.alicja.firebaseshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductEditorActivity extends BasicActivity {

    private static final String TAG = "ProductEditorActivity";

    private EditText nameEditText;
    private EditText priceEditText;
    private EditText quantityEditText;
    private CheckBox isBoughtCheckBox;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private boolean isEditionMode;
    private String productId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_editor);

        nameEditText = (EditText) findViewById(R.id.edit_product_name);
        priceEditText = (EditText) findViewById(R.id.edit_product_price);
        quantityEditText = (EditText) findViewById(R.id.edit_product_quantity);
        isBoughtCheckBox = (CheckBox) findViewById(R.id.edit_checkbox_is_bought);

        //setting up firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");

        //checking if editing exisitng item
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getParcelableExtra("product") != null) {

            isEditionMode = true;
            Log.i(TAG, "onCreate: existing product");

            Product product = intent.getParcelableExtra("product");
            productId = product.getId();
            Log.i(TAG, "onCreate: product id:" + productId);

            //if it is an existing item - fill the editTexts
            setFieldsValues(product.getName(), product.getPrice() + "", product.getQuantity() + "", product.getIsBought());

        } else {
            isEditionMode = false;
        }

        //changing color
        changeFont();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (!isEditionMode) {
            menu.findItem(R.id.action_delete).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                finish();
                return true;
            case R.id.action_delete:
                deleteProduct();
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveProduct() {
        String productName = nameEditText.getText().toString().trim();
        String productPrice = priceEditText.getText().toString().trim();
        String productQuantity = quantityEditText.getText().toString().trim();
        boolean isBought = isBoughtCheckBox.isChecked();

        //return if one of fields is empty
        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPrice) || TextUtils.isEmpty(productQuantity)) {
            return;
        }

        if (isEditionMode) {

            //update fields of current product
            databaseReference.child(productId).child("name").setValue(productName);
            databaseReference.child(productId).child("price").setValue(Float.parseFloat(productPrice));
            databaseReference.child(productId).child("quantity").setValue(Integer.parseInt(productQuantity));
            databaseReference.child(productId).child("isBought").setValue(isBought);

        } else {
            //new product ID from firebase database
            String productId = databaseReference.push().getKey();

            //create and save new product
            Product product = new Product(productId, productName, Float.parseFloat(productPrice), Integer.parseInt(productQuantity), isBought);
            databaseReference.child(String.valueOf(productId)).setValue(product);
        }

    }

    private void setFieldsValues(String name, String price, String quantity, boolean isBought) {

        nameEditText.setText(name);
        priceEditText.setText(price);
        quantityEditText.setText(quantity);
        isBoughtCheckBox.setChecked(isBought);

    }


    private void deleteProduct() {
        if (productId == null) {
            return;
        }

        databaseReference.child(productId).removeValue();
    }
}
