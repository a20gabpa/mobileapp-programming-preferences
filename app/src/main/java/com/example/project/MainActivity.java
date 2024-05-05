package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    /* ===== VARIABLES ===== */
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;
    /* ===================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get preference
        myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        // Store initial preference
        myPreferenceEditor.putString("MyAppPreferenceString", "An preference with only one value at start");
        myPreferenceEditor.apply();

        // Read from preferences
        TextView prefTextView = findViewById(R.id.prefText);
        prefTextView.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found..."));

        // Get button and add event listener
        Button btn = findViewById(R.id.firstBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
        SharedPreferences.Editor myPreferenceEditor = myPreferenceRef.edit();

        // Read from preferences
        TextView prefTextView = findViewById(R.id.prefText);
        prefTextView.setText(myPreferenceRef.getString("MyAppPreferenceString", "No preference found..."));
    }
}
