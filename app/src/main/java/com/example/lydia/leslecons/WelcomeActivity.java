package com.example.lydia.leslecons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    public static final int EDIT_TEXT_FOR_NAME_ID = 123;
    private SharedPreferences prefs;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        prefs = getPreferences(MODE_PRIVATE);
        name = prefs.getString("name", null);
        setUpWelcomeText();
    }

    private void setUpWelcomeText() {
        LinearLayout layout = findViewById(R.id.welcome_layout);
        TextView welcomeTextView = new TextView(this);
        welcomeTextView.setTextColor(Color.BLACK); // colour
        welcomeTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER); // alignment
        welcomeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20); // text size
        welcomeTextView.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25,  getResources().getDisplayMetrics()), 1); // spacing between lines
        setLayoutDimensionsParams(welcomeTextView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); // width and height
        setMarginParams(welcomeTextView, 0, 350, 0, 0); // margins

        if (name == null) {
            // question
            welcomeTextView.setText("hello.\nhow would you like to be addressed?");
            // answer
            EditText getNameText = new EditText(this);
            getNameText.setId(EDIT_TEXT_FOR_NAME_ID); // id
            getNameText.setHint("your name"); // hint
            getNameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15); // text size
            setLayoutDimensionsParams(getNameText, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); // width and height
            setMarginParams(getNameText, 200, 100, 200, 0); // margins

            layout.addView(welcomeTextView, 0);
            layout.addView(getNameText, 1);
        } else {
            welcomeTextView.setText("Welcome back,\n" + name.toLowerCase());
            layout.addView(welcomeTextView, 0);
        }
    }

    private ViewGroup.LayoutParams setLayoutDimensionsParams(View view, int width, int height) {
        // wrap_content for width and height
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        view.setLayoutParams(params);
        return params;
    }

    private void setMarginParams(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        params.setMargins(left, top, right, bottom);
        view.setLayoutParams(params);
    }

    public void startApplication(View view) {
        if (name == null) {
            EditText editText = findViewById(EDIT_TEXT_FOR_NAME_ID);
            String enteredName = editText.getText().toString();
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString("name", enteredName);
            prefsEditor.apply();
        }
        // switch to next activity
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
