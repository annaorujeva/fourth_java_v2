package app.domain;

import java.util.ArrayList;

public class Account {
    private int id;
    private String holder;
    private int amount;
    public static ArrayList <Account> accounts = new ArrayList<>();

    public Account(){
    }

    public Account(int id, String holder, int amount){
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getHolder() {
        return holder;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setId(int id) {
        this.id = id;
    }
}
