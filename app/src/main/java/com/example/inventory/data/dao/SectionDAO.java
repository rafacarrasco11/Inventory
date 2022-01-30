package com.example.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;

import java.util.List;

@Dao
public interface SectionDAO {
    @Insert()
    long insert(Section section);

    @Update()
    void update(Section section);

    @Delete()
    void delete(Section section);

    @Query("DELETE FROM section")
    void deleteAll();

    @Query("SELECT * FROM section")
    List<Section> select();

}
