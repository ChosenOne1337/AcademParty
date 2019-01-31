package ru.ftc.android.shifttemple.features.parties.domain.model;

public class Item {
    private String name = "";
    private Integer amount = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
