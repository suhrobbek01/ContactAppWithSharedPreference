package com.example.contactappwithsharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.contactappwithsharedpreference.databinding.ActivityAddContactBinding;
import com.example.contactappwithsharedpreference.model.Contact;
import com.example.contactappwithsharedpreference.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends AppCompatActivity {
    private ActivityAddContactBinding binding;
    private MySharedPreference mySharedPreference;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mySharedPreference = MySharedPreference.getInstance(this);
        gson = new Gson();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        binding.add.setOnClickListener(view -> {
            String name = binding.name.getText().toString().trim();
            String number = binding.number.getText().toString().trim();
            if (name.equals("")) {
                Toast.makeText(this, "Kontakt nomini kiriting", Toast.LENGTH_SHORT).show();
            } else if (number.equals("")) {
                Toast.makeText(this, "Kontakt raqamini kiriting", Toast.LENGTH_SHORT).show();
            } else if (!isValidPhoneNumber(number)) {
                Toast.makeText(this, "Telefon raqamini to`g`ri kiriting", Toast.LENGTH_SHORT).show();
            } else {
                Contact contact = new Contact(name, number);
                String contactList = mySharedPreference.getContactList();
                List<Contact> contacts;
                if (contactList.equals("")) {
                    contacts = new ArrayList<>();
                } else {
                    Type type = new TypeToken<List<Contact>>() {
                    }.getType();
                    contacts = gson.fromJson(contactList, type);
                }
                contacts.add(contact);
                String s = gson.toJson(contacts);
                mySharedPreference.setContactList(s);
                finish();
            }
        });
    }

    private boolean isValidPhoneNumber(String number) {
        return Patterns.PHONE.matcher(number).matches();
    }
}