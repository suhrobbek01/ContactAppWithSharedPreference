package com.example.contactappwithsharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.contactappwithsharedpreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.my_statusbar_color));

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        binding.add.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddContact.class);
            startActivity(intent);
        });
        binding.list.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ContactList.class);
            startActivity(intent);
        });
    }
}