package com.example.inventory.data.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory.data.model.Section;
import com.example.inventory.data.model.User;

import java.util.List;

public interface UserDAO {
    @Query("SELECT * FROM user")
    User select(String email, String passwd);
}
