package com.codename1.entities;

public class Wallets {
    private int id;
    private String AdressWallet;
    private String clientWallet;

    public Wallets() {
    }

    public Wallets(int id, String adressWallet) {
        this.id = id;
        AdressWallet = adressWallet;
    }

    public Wallets(String adressWallet) {
        AdressWallet = adressWallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdressWallet() {
        return AdressWallet;
    }

    public void setAdressWallet(String adressWallet) {
        AdressWallet = adressWallet;
    }

    public String getClientWallet() {
        return clientWallet;
    }

    public void setClientWallet(String clientWallet) {
        this.clientWallet = clientWallet;
    }

    @Override
    public String toString() {
        return

                AdressWallet+" "+id
                ;
    }
}

