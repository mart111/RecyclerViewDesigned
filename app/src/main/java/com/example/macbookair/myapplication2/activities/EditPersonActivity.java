package com.example.macbookair.myapplication2.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.adapters.RecyclerViewAdapter;
import com.example.macbookair.myapplication2.database.PersonDatabase;
import com.example.macbookair.myapplication2.models.Person;

public class EditPersonActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText fullNameEdit, countryEdit, emailEdit, socNetEdit;
    private Button saveEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        fullNameEdit = findViewById(R.id.full_name_edit);
        countryEdit = findViewById(R.id.country_edit);
        emailEdit = findViewById(R.id.email_edit);
        socNetEdit = findViewById(R.id.social_network_edit);
        saveEditButton = findViewById(R.id.saveButton_editContact);

        saveEditButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        Person person = RecyclerViewAdapter.getPersonList().get(RecyclerViewAdapter.INDEX);
        person.setName(fullNameEdit.getText().toString());
        person.setCountry(countryEdit.getText().toString());
        person.setImageID(0);
        person.setEmail(emailEdit.getText().toString());
        person.setSocialNet(socNetEdit.getText().toString());

        db.personDao().update(person);

        startActivity(new Intent(EditPersonActivity.this, MainActivity.class));
    }
}
