package com.example.contactapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.contactapp.R;
import com.google.android.material.appbar.MaterialToolbar;

public class NewContactActivity extends AppCompatActivity {

    private MaterialToolbar newContactToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        newContactToolBar = findViewById(R.id.toolbar);
        newContactToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to return to MainActivity
                finish();
            }

        });

    }
}