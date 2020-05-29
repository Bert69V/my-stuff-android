package de.telekom.sea.mystuff.frontend.android.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private Long id;

    private String name;
    private int amount;
    private String location;
    private String description;
    private Date lastUsed;


    public Item (String name, int amount, String location, String description, Date lastUsed ){
        this.name = name;
        this.amount = amount;
        this.location = location;
        this.description = description;
        this.lastUsed = lastUsed;
    }

    public Item (){

    }
}
