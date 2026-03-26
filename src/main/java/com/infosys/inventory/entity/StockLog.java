package com.infosys.inventory.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StockLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;
    private String itemName;
    private int quantity;
    private LocalDateTime time;

    public StockLog(){
        this.time = LocalDateTime.now();
    }

    public Long getId(){ return id; }

    public String getAction(){ return action; }

    public String getItemName(){ return itemName; }

    public int getQuantity(){ return quantity; }

    public LocalDateTime getTime(){ return time; }

    public void setAction(String action){ this.action = action; }

    public void setItemName(String itemName){ this.itemName = itemName; }

    public void setQuantity(int quantity){ this.quantity = quantity; }
}