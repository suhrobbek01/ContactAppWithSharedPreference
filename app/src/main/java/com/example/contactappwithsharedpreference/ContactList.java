package com.example.contactappwithsharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.contactappwithsharedpreference.databinding.ActivityContactListBinding;
import com.example.contactappwithsharedpreference.model.Contact;
import com.example.contactappwithsharedpreference.utils.MySharedPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ContactList extends AppCompatActivity {
    private ActivityContactListBinding binding;
    private MySharedPreference mySharedPreference;
    private Gson gson;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.my_statusbar_color));

        mySharedPreference = MySharedPreference.getInstance(this);
        gson = new Gson();

        String contactList = mySharedPreference.getContactList();
        List<Contact> contacts;
        if (!contactList.isEmpty()) {
            binding.empty.setVisibility(View.INVISIBLE);
            binding.name1.setVisibility(View.VISIBLE);
            binding.name1.setText("");
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            contacts = gson.fromJson(contactList, type);
            for (int i = 0; i < contacts.size(); i++) {
                if (i == 0) {
                    binding.name1.setText(binding.name1.getText().toString() + contacts.get(i).getName() + "\n" + contacts.get(i).getNumber());
                } else {
                    binding.name1.setText(binding.name1.getText().toString() + "\n \n" + contacts.get(i).getName() + "\n" + contacts.get(i).getNumber());
                }
            }
        } else {
            binding.empty.setVisibility(View.VISIBLE);
            binding.name1.setVisibility(View.INVISIBLE);
        }
    }
}