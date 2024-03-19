package com.example.contactapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private LiveData<List<Contact>> contactList;
    private AppDatabase appDatabase;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
        contactList = appDatabase.contactDao().getAllContacts();
    }

    public LiveData<List<Contact>> getContactList() {
        return contactList;
    }
}
