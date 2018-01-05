package com.example.alicja.firebaseshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;

public class MainActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView productsListView = (ListView) findViewById(R.id.product_list);
        productsListView.setEmptyView(
                findViewById(R.id.empty_view)
        );

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
