package com.case3.model;

public class Category {
    private int id;
    private String name;
    private String link_icon;

    public Category() {
    }

    public Category(int id, String name, String link_icon) {
        this.id = id;
        this.name = name;
        this.link_icon = link_icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_icon() {
        return link_icon;
    }

    public void setLink_icon(String link_icon) {
        this.link_icon = link_icon;
    }
}
