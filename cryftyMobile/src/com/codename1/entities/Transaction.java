package com.codename1.entities;

public class Transaction {
    private int id;
    private String wallet;
    private String cart;
    private int cart2;

    public Transaction() {
    }

    public Transaction(String wallet, int cart2) {
        this.wallet = wallet;
        this.cart2 = cart2;
    }


    public Transaction(String wallet, String cart) {
        this.wallet = wallet;
        this.cart = cart;
    }




    public Transaction(String wallet) {
        this.wallet = wallet;
    }

    public int getCart2() {
        return cart2;
    }

    public void setCart2(int cart2) {
        this.cart2 = cart2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return " \n Transaction{" + "id=" + id +  ", wallet=" + wallet + ", cart=" + cart + "} \n ";
    }

}