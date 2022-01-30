package com.example.contactappwithsharedpreference.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

public class MySharedPreference {
    private static MySharedPreference mySharedPreference = new MySharedPreference();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreference getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Contact", Context.MODE_PRIVATE);
        }
        return mySharedPreference;
    }

    public void setContactList(String contactList) {
        editor = sharedPreferences.edit();
        editor.putString("contactList", contactList).commit();
    }

    public String getContactList() {
        return sharedPreferences.getString("contactList", "");
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.clear();
    }
}
