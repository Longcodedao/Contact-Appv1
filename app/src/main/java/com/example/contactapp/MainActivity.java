package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;


    private AppDatabase appDatabase;
    private ContactDao contactDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);

        contactList.add(new Contact("Nguyen Van A", "0907042194", "a@gmail.com"));
        contactList.add(new Contact("Nguyen Van B", "0907042194", "b@gmail.com"));
        contactList.add(new Contact("Nguyen Van C", "0907042194", "c@gmail.com"));
        contactAdapter.notifyDataSetChanged();


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();

                contactDao.insert(new Contact ("Nguyen Van A",
                        "092334243",
                        "a@gmail.com"));
            }
        });





    }

}