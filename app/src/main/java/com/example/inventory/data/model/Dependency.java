package com.example.inventory.data.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Dependency implements Comparable, Serializable {
    public static final String TAG="Dependency";
    private String name;
    private String shortName;
    private String description;
    private String image;

    public Dependency() {

    }

    public Dependency(String name, String shortName, String description, String image) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Dependency)obj).getName().equals(getName());
    }


    @Override
    public int compareTo(Object o) {
        if (equals(o))
            return ((Dependency)o).getDescription().compareTo(getDescription());

        return ((Dependency)o).getName().compareTo(getName());

    }
}
