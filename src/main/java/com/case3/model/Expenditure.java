package com.case3.model;

import java.time.LocalDate;

public class Expenditure {
    private int id;
    private Category category;
    private LocalDate date;
    private int money;
    private String note;

    public Expenditure() {
    }

    public Expenditure(int id, Category category, LocalDate date, int money, String note) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.money = money;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
