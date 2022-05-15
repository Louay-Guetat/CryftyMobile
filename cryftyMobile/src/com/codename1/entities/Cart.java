
package com.codename1.entities;

import com.codename1.entities.Nft;
import com.codename1.io.Data;
import com.codename1.io.Preferences;

import java.util.ArrayList;
import java.util.Date;

public class Cart {
    private int id= Integer.parseInt(Preferences.get("id", "1"));
    private ArrayList<Nft> nftProd;
    private float total=0;
    private String dateCreation;


    public Cart() {
    }

    public Cart(ArrayList<Nft> nftProd) {
        this.nftProd = nftProd;
    }

    public Cart(int id, ArrayList<Nft> nftProd) {
        this.id = id;
        this.nftProd = nftProd;
    }

    public Cart(int id, ArrayList<Nft> nftProd, float total) {
        this.id = id;
        this.nftProd = nftProd;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Nft> getNftProd() {
        return nftProd;
    }
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setNftProd(ArrayList<Nft> nftProd) {
        this.nftProd = nftProd;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", nftProd=" + nftProd +
                '}';
    }
}