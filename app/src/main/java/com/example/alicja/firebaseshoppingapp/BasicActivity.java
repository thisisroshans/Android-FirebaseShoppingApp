package com.example.alicja.firebaseshoppingapp;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BasicActivity extends AppCompatActivity {

    //text to shared preferences:
    static final String PREFERENCES_NAME = "settingsPreferences";
    static final String PREFERENCES_COLOR_FIELD = "colorPreference";
    static final String PREFERENCES_SIZE_FIELD = "sizePreference";
    static final String PREFERENCE_FIRST_COLOR_ID = "firstColorId";
    static final String PREFERENCE_SECOND_COLOR_ID = "secondColorId";
    static final String PREFERENCE_THIRD_COLOR_ID = "thirdColorId";

    //sharedPreferences
    SharedPreferences preferences;

    //sharedPreference values
    public static int sizeFromPreferences;
    public static int colorFromPreferences;
    public static int firstColorId;
    public static int secondColorId;
    public static int thirdColorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_basic);

        getSharedPreferences();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPreferences();


    }

    public void getSharedPreferences() {
        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sizeFromPreferences = preferences.getInt(PREFERENCES_SIZE_FIELD, 0);
        colorFromPreferences = preferences.getInt(PREFERENCES_COLOR_FIELD, 0);
        firstColorId = preferences.getInt(PREFERENCE_FIRST_COLOR_ID, 0);
        secondColorId = preferences.getInt(PREFERENCE_SECOND_COLOR_ID, 0);
        thirdColorId = preferences.getInt(PREFERENCE_THIRD_COLOR_ID, 0);
    }

    public void changeColor(ViewGroup parentLayout) {
        getSharedPreferences();

        for (int count = 0; count < parentLayout.getChildCount(); count++) {
            View view = parentLayout.getChildAt(count);
            if ((view instanceof TextView) && !(view instanceof RadioGroup)) {
                if (colorFromPreferences == firstColorId) {
                    ((TextView) view).setTextColor(Color.BLACK);
                } else if (colorFromPreferences == secondColorId) {
                    ((TextView) view).setTextColor(Color.rgb(255, 102, 153));
                } else if (colorFromPreferences == thirdColorId) {
                    ((TextView) view).setTextColor(Color.rgb(95, 104, 206));
                }
            } else if (view instanceof ViewGroup && !(view instanceof RadioGroup)) {
                changeColor((ViewGroup) view);
            }
        }
    }

    public void changeFontSize(ViewGroup parentLayout) {
        getSharedPreferences();
        int textSize = 14;
        switch(sizeFromPreferences) {
            case 0:
                textSize = 14;
                break;
            case 1:
                textSize = 20;
        }

        for (int count = 0; count < parentLayout.getChildCount(); count++) {
            View view = parentLayout.getChildAt(count);
            if (view instanceof TextView) {
                ((TextView) view).setTextSize(textSize);
            } else if (view instanceof ViewGroup ) {
                changeFontSize((ViewGroup) view);
            }
        }
    }

    public void changeFont() {
        ViewGroup parentLayout = (ViewGroup) findViewById(android.R.id.content);

        changeFontSize(parentLayout);
        changeColor(parentLayout);

    }

}
