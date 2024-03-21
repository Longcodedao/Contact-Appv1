package com.example.contactapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;
import com.example.contactapp.repository.ContactRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<Contact>> contactList;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        contactList = repository.getContactList();
    }

    public LiveData<List<Contact>> getContactList() {
        return contactList;
    }

    public void insertContact(Contact contact){
        repository.insertContact(contact);
    }

    public LiveData<List<Contact>> searchContacts(String query) {
        return repository.searchContacts(query);
    }
}
