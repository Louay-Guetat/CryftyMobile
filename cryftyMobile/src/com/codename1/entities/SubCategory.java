package com.codename1.entities;

public class SubCategory {
    private int id;
    private String name;
    private String creationDate;
    private int nbrNft;
    private Category category;

    public SubCategory(int id,String name, String creationDate, int nbrNft, Category category) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.nbrNft = nbrNft;
        this.category = category;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getNbrNft() {
        return nbrNft;
    }

    public void setNbrNft(int nbrNft) {
        this.nbrNft = nbrNft;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
