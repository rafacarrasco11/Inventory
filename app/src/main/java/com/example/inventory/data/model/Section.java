package com.example.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.google.android.material.transition.platform.SlideDistanceProvider;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Dependency.class, parentColumns = "id", childColumns = "dependency_id"))
public class Section implements Comparable, Serializable {
    public static final String TAG = "Seccion";

    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    String name;
    @NonNull
    String shortName;
    @ColumnInfo(name = "dependency_id")
    public long dependency;

    @NonNull
    String description;
    String image;


    public Section(long id, @NonNull String name, @NonNull String shortName, long dependency, @NonNull String description, String image) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.image = image;
    }

    @Ignore
    public Section(@NonNull String name, @NonNull String shortName, long dependency, @NonNull String description, String image) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.image = image;
    }

    @Ignore
    public Section() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getShortName() {
        return shortName;
    }

    public void setShortName(@NonNull String shortName) {
        this.shortName = shortName;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDependency() {
        return dependency;
    }

    public void setDependency(long dependency) {
        this.dependency = dependency;
    }

    @Override
    public int compareTo(Object o) {
        if (equals(o))
            return ((Section)o).getDescription().compareTo(getDescription());

        return ((Section)o).getName().compareTo(getName());
    }
}
