package com.example.macbookair.myapplication2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.macbookair.myapplication2.models.Person;

/**
 * Created by macbookair on 2/3/18.
 */

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDao personDao();


}
