package com.example.contactapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactapp.database.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM my_contact")
    LiveData<List<Contact>> getAllContacts();

    @Insert
    void insert(Contact... contacts);

    @Query("SELECT * FROM my_contact where name LIKE :query OR mobilePhone LIKE :query OR email LIKE :query")
    LiveData<List<Contact>> searchContacts(String query);
}
