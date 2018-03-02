package com.example.macbookair.myapplication2.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.macbookair.myapplication2.models.Person;

import java.util.List;

/**
 * Created by macbookair on 2/3/18.
 */

@Dao
public interface PersonDao {

    @Query("SELECT * FROM Person")
    List<Person> getAllData();

    @Insert
    void insertAll(Person... person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);
}
