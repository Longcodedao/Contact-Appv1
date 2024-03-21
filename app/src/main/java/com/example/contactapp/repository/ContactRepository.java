package com.example.contactapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;
import com.example.contactapp.database.ContactDao;

import java.util.List;

public class ContactRepository {


    private ContactDao contactDao;
    private LiveData<List<Contact>> contactList;

    public ContactRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        contactDao = database.contactDao();
        contactList = contactDao.getAllContacts();
    }


    public LiveData<List<Contact>> getContactList() {
        return contactList;
    }

    public LiveData<List<Contact>> searchContacts(String query) {

        Log.d("ContactRepository", "Executing search query with query: " + query);
        LiveData<List<Contact>> searchResults = contactDao.searchContacts('%' + query + '%');
        // Log the number of results returned
        searchResults.observeForever(new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                Log.d("ContactRepository", "Search query returned " + contacts.size() + " results");
            }
        });
        return searchResults;
    }

    public void insertContact(Contact contact){
        AppDatabase.databaseWriterExecutor.execute(() -> contactDao.insert(contact));
    }



}
