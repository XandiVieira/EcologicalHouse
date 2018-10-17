package com.example.xandi.ecologicalhouse;

import android.media.Image;

public class Item {

    private Image image;
    private String name;
    private String description;
    private String info;
    private String recipe;

    public Item() {
    }

    public Item(Image image, String name, String description, String info, String recipe) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.info = info;
        this.recipe = recipe;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
