package com.example.contactapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactapp.R;
import com.example.contactapp.database.AppDatabase;
import com.example.contactapp.database.Contact;
import com.example.contactapp.databinding.ActivityNewContactBinding;
import com.example.contactapp.viewmodel.ContactViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class NewContactActivity extends AppCompatActivity {

    private ActivityNewContactBinding binding;
    private ContactViewModel contactViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_new_contact);

        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        setSupportActionBar(binding.toolbar);

        contactViewModel = new ViewModelProvider(NewContactActivity.this).get(ContactViewModel.class);

//        newContactToolBar = findViewById(R.id.toolbar);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to return to the previous screen
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_save){

            String first_name = binding.editTextFirstName.getText().toString();
            String last_name = binding.editTextLastname.getText().toString();
            String mobile_phone = binding.editPhoneNumber.getText().toString();
            String email = binding.editEmail.getText().toString();
            String name = last_name + " " + first_name;
            Contact contact = new Contact(name, mobile_phone, email);

            saveContactToDatabase(contact);

            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    private void saveContactToDatabase(Contact contact) {

        contactViewModel.insertContact(contact);

        // Optionally, you can show a toast or perform any other action after saving
        Toast.makeText(this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
    }


}