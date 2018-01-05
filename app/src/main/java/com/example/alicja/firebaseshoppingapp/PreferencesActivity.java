package com.example.alicja.firebaseshoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class PreferencesActivity extends BasicActivity {

    //colors:
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    //size
    Spinner sizespinner;


    //shared preferences
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //initalizing radiogroup and radiobuttons
        radioGroup = (RadioGroup) findViewById(R.id.colorRadioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton11);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton12);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton13);

        //initializing size spinner
        sizespinner = (Spinner) findViewById(R.id.spinner2);
        String[] fontSizes = new String[]{"small font", "big font"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fontSizes);
        sizespinner.setAdapter(adapter);

//
//        //initializing submitButton
//        submitBtn = (Button) findViewById(R.id.submitBtn);

        //initializing shared preferences
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);

        //storing radioButtons IDs in sharedPreferences
        storeRadioButtonsId();

        //preselecting inputs
        preSelectInputs(colorFromPreferences, sizeFromPreferences);

        //changing color
        changeFont();


    }

    public void storeRadioButtonsId() {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(PREFERENCE_FIRST_COLOR_ID, radioButton1.getId());
        editor.putInt(PREFERENCE_SECOND_COLOR_ID, radioButton2.getId());
        editor.putInt(PREFERENCE_THIRD_COLOR_ID, radioButton3.getId());

        editor.commit();
    }

    public void preSelectInputs(int selectedColor, int selectedSize) {

        if (selectedColor == radioButton2.getId()) {
            radioGroup.check(radioButton2.getId());
        } else if (selectedColor == radioButton3.getId()) {
            radioGroup.check(radioButton3.getId());
        } else {
            radioGroup.check(radioButton1.getId());
        }

        //sizespipnner - preset
        sizespinner.setSelection(selectedSize);

    }

    public void saveSettings(View view) {
        SharedPreferences.Editor editor = preferences.edit();

        //getting values from radiogroup (color)
        int selectedTextColor = radioGroup.getCheckedRadioButtonId();

        //getting values from size spinner
        int selectedTextSize = sizespinner.getSelectedItemPosition();


        //saving values to shared preferences
        editor.putInt(PREFERENCES_COLOR_FIELD, selectedTextColor);
        editor.putInt(PREFERENCES_SIZE_FIELD, selectedTextSize);

        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveSettings(item.getActionView());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
