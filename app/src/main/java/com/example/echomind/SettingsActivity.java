package com.example.echomind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        LinearLayout btnClear = findViewById(R.id.btnClearHistory);
        btnClear.setOnClickListener(v ->
                Toast.makeText(this, "تم مسح سجل المحادثات", Toast.LENGTH_SHORT).show());

        LinearLayout btnAbout = findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class)));
    }
}
