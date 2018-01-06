package com.example.alicja.firebaseshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasicActivity {

    private static final String TAG = "MainActivity";

    private ListView productsListView;
    private ProductAdapter productAdapter;


    //Firebase
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private ChildEventListener childEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize message ListView and its adapter
        productsListView = (ListView) findViewById(R.id.product_list);

        List<Product> productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, R.layout.product_list_item, productList);
        productsListView.setAdapter(productAdapter);

        // Add empty view for ListView
        productsListView.setEmptyView(
                findViewById(R.id.empty_view)
        );

        // Add clickListener to single products
        productsListView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ProductEditorActivity.class);
            Product currentProduct = productList.get(position);
            intent.putExtra("product", currentProduct);
            startActivity(intent);
        });



        //setting up firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Product product = dataSnapshot.getValue(Product.class);
                productAdapter.add(product);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String productKey = dataSnapshot.getKey();
                Log.i(TAG, "onChildRemoved: " + productKey);

                Product productToBeDeleted = productAdapter.findProductById(productKey);
                if(productToBeDeleted != null) {
                    productAdapter.remove(productToBeDeleted);
                }

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, ":onCancelled", databaseError.toException());
                Toast.makeText(getApplicationContext(), "Failed to load products.",
                        Toast.LENGTH_SHORT).show();

            }
        };

        databaseReference.addChildEventListener(childEventListener);


        // FAB for adding new products
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                (view) -> {
                    Intent intent = new Intent(MainActivity.this, ProductEditorActivity.class);
                    startActivity(intent);
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_preferences:
                goToPreferences(item.getActionView());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToPreferences(View view){
        Intent intent1 = new Intent(this, PreferencesActivity.class);
        startActivity(intent1);
    }
}
