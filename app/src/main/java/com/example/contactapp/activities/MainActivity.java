package com.example.contactapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;
import com.example.contactapp.adapters.ContactAdapter;
import com.example.contactapp.database.ContactDao;
import com.example.contactapp.databinding.ActivityMainBinding;
import com.example.contactapp.viewmodel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;


    private ContactViewModel contactViewModel;
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

        contactViewModel = new ViewModelProvider(MainActivity.this).get(ContactViewModel.class);
        contactViewModel.getContactList().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactList.addAll(contacts);
                contactAdapter.notifyDataSetChanged();
            }
        });



//        contactList.add(new Contact("Nguyen Van A", "0907042194", "a@gmail.com"));
//        contactList.add(new Contact("Nguyen Van B", "0907042194", "b@gmail.com"));
//        contactList.add(new Contact("Nguyen Van C", "0907042194", "c@gmail.com"));
//        contactAdapter.notifyDataSetChanged();


//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase = AppDatabase.getInstance(getApplicationContext());
//                contactDao = appDatabase.contactDao();
//
//                contactDao.insert(new Contact ("Nguyen Van A",
//                        "092334243",
//                        "a@gmail.com"));
//
//
//            }
//        });

        binding.addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);

                // Start new Activity
                startActivity(intent);
            }
        });


    }



}