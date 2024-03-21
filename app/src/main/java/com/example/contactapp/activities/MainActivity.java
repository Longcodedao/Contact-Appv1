package com.example.contactapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.example.contactapp.R;
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
//    private AppDatabase appDatabase;
//    private ContactDao contactDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        setSupportActionBar(binding.contactMenu);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);

        contactViewModel = new ViewModelProvider(MainActivity.this).get(ContactViewModel.class);
        contactViewModel.getContactList().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
//                contactList.addAll(contacts);
                // Clear the existing list
                contactList.clear();

                // Add all contacts from the new list
                contactList.addAll(contacts);
                contactAdapter.notifyDataSetChanged();
            }
        });

        binding.addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);

                // Start new Activity
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        // Get the icon from the menu item
        Drawable searchIcon = searchItem.getIcon();

        // Set the icon tint color to white
        if (searchIcon != null) {
            searchIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            searchItem.setIcon(searchIcon);
        }
        // Customize the SearchView as needed
        searchView.setIconifiedByDefault(true); // Expand the SearchView by default
        searchView.setQueryHint("Search"); // Set the hint text

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle query submission (if needed)
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter your list based on newText and update the UI accordingly
                // For example, you can call a method in your ViewModel to perform the search
                contactViewModel.searchContacts(newText).observe(MainActivity.this, new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(List<Contact> contacts) {
                        // Clear the existing list
                        contactList.clear();

                        // Add all contacts from the new list
                        contactList.addAll(contacts);
                        contactAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks

        return super.onOptionsItemSelected(item);


    }
}