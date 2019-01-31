package ru.ftc.android.shifttemple.features.parties.domain.model;

import java.util.List;

public class Person {
    private String name = "";
    private List<Item> items = null;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
