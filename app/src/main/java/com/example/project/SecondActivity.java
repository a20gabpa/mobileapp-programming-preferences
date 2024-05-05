package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SecondActivity  extends AppCompatActivity {
    /* ===== VARIABLES ===== */
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;
    /* ===================== */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        // Get button and add event listener
        Button btn = findViewById(R.id.secondBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get ref to preference
                myPreferenceRef = getSharedPreferences("SharedPreferencesName", MODE_PRIVATE);
                myPreferenceEditor = myPreferenceRef.edit();

                // Store new preference
                myPreferenceEditor.putString("MyAppPreferenceString", "Seems like things have changed here");
                myPreferenceEditor.apply();

                finish();
            }
        });
    }
}
