package com.example.macbookair.myapplication2.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.database.PersonDatabase;
import com.example.macbookair.myapplication2.models.Person;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddContactActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText txtFullName;
    private EditText txtCountry;
    private EditText txtSocial;
    private EditText txtEmail;
    private ImageView img;
    private byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        saveButton = findViewById(R.id.saveButton_addContact);
        txtFullName = findViewById(R.id.full_name_add);
        txtCountry = findViewById(R.id.country_add);
        txtEmail = findViewById(R.id.email_add);
        txtSocial = findViewById(R.id.social_network_add);
        img = findViewById(R.id.addContactImage);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "production")
                        .allowMainThreadQueries()
                        .build();
                db.personDao().insertAll(new Person(txtFullName.getText().toString(),
                        0,
                        txtSocial.getText().toString(),
                        txtCountry.getText().toString(),
                        txtEmail.getText().toString(), bytes));
                startActivity(new Intent(AddContactActivity.this, MainActivity.class));
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            img.setImageURI(null);
            //img.setImageURI(uri);

            try {
                InputStream is = getContentResolver().openInputStream(uri);
                bytes = getBytes(is);

                img.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
