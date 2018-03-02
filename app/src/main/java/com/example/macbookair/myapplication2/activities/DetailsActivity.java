package com.example.macbookair.myapplication2.activities;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.adapters.RecyclerViewAdapter;
import com.example.macbookair.myapplication2.constants.ExtraConstants;
import com.example.macbookair.myapplication2.database.PersonDatabase;
import com.example.macbookair.myapplication2.models.Person;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar toolbarDetails;
    private Button deleteButton;
    private Button editButton;
    private TextView txtProductDetails;
    private TextView txtFullNameDetails;
    private TextView txtCountryDetails;
    private TextView txtEmailDetails;
    private TextView txtLastSeenDetails;
    private ImageView profileImageDetails;
    private ImageView networkImageDetails;
    private TextView txtNameDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();

        toolbarDetails.setTitle("Details");
        toolbarDetails.setTitleTextAppearance(this, R.style.toolbarStyle);
        toolbarDetails.setTitleMarginStart(270);
        toolbarDetails.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarDetails);

        Intent i = getIntent();
        String social = i.getStringExtra(ExtraConstants.NETWORK_IMAGE);
//        if (social.equals(SocialNetworks.FACEBOOK))
//            networkImageDetails.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.facebook));
//        else
//            networkImageDetails.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.twitter));


        profileImageDetails.setImageBitmap(BitmapFactory.decodeResource(getResources(), i.getIntExtra(ExtraConstants.PROFILE_IMAGE, 0)));
        txtFullNameDetails.setText(i.getStringExtra(ExtraConstants.FULL_NAME));
        txtEmailDetails.setText(i.getStringExtra(ExtraConstants.E_MAIL));
        txtCountryDetails.setText(i.getStringExtra(ExtraConstants.COUNTRY));
        txtNameDetails.setText(i.getStringExtra(ExtraConstants.FULL_NAME));

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, EditPersonActivity.class);
                i.putExtra(ExtraConstants.FULL_NAME, txtFullNameDetails.getText().toString());
                i.putExtra(ExtraConstants.COUNTRY, txtCountryDetails.getText().toString());
                i.putExtra(ExtraConstants.E_MAIL, txtEmailDetails.getText().toString());
                startActivity(i);
            }
        });
    }


    private void initViews() {
        deleteButton = findViewById(R.id.delete_button);
        editButton = findViewById(R.id.edit_button);
        txtProductDetails = findViewById(R.id.product_details);
        txtCountryDetails = findViewById(R.id.country_details);
        txtEmailDetails = findViewById(R.id.email_details);
        txtFullNameDetails = findViewById(R.id.full_name_details);
        txtLastSeenDetails = findViewById(R.id.last_seen_details);
        profileImageDetails = findViewById(R.id.profile_image_details);
        networkImageDetails = findViewById(R.id.network_image_details);
        txtNameDetails = findViewById(R.id.txt_name_details);
        toolbarDetails = findViewById(R.id.toolbar_details);
    }

    public void delete(View v) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Delete Contact: " + txtNameDetails.getText().toString());
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "production")
                        .allowMainThreadQueries()
                        .build();
                Person person = RecyclerViewAdapter.getPersonList().get(RecyclerViewAdapter.INDEX);
                db.personDao().delete(person);
                startActivity(new Intent(DetailsActivity.this, MainActivity.class));
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}
