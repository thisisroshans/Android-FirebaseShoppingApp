package com.example.alicja.firebaseshoppingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductEditorActivity extends BasicActivity {

    private static final int EXISTING_PRODUCT_LOADER = 0;

    private EditText nameEditText;
    private EditText priceEditText;
    private EditText quantityEditText;
    private CheckBox isBoughtCheckBox;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                finish();
                return true;
//            case R.id.action_delete:
//                deleteProduct();
//                finish();
//                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveProduct() {
        String productName = nameEditText.getText().toString().trim();
        float productPrice = Float.parseFloat(priceEditText.getText().toString().trim());
        int productQuantity = Integer.parseInt(quantityEditText.getText().toString().trim());
        boolean isBought = isBoughtCheckBox.isChecked();

        //product ID from firebase database
        String productId = databaseReference.push().getKey();

        Product product = new Product(productId, productName, productPrice, productQuantity, isBought );

//        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPrice) || TextUtils.isEmpty(productQuantity)) {
//            return;
//        }

        databaseReference.child(String.valueOf(productId)).setValue(product);

    }

//    private void deleteProduct() {
//        if (currentProductUri == null) {
//            return;
//        }
//
//        int rowsDeleted = getContentResolver().delete(currentProductUri, null, null);
//        if (rowsDeleted == 0) {
//            Toast.makeText(this, getString(R.string.editor_delete_product_failed),
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, getString(R.string.editor_delete_product_successful),
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
}
