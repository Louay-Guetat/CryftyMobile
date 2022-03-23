package com.codename1.entities;

public class Category {
    private int id;
    private String name;
    private String creationDate;
    private int nbrNft;
    private int nbrSubCategory;

    public Category(){

    }

    public Category(int id,String name,String creationDate, int nbrNft, int nbrSubCategory) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.nbrNft = nbrNft;
        this.nbrSubCategory = nbrSubCategory;
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

    public int getNbrSubCategory() {
        return nbrSubCategory;
    }

    public void setNbrSubCategory(int nbrSubCategory) {
        this.nbrSubCategory = nbrSubCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", nbrNft=" + nbrNft +
                ", nbrSubCategory=" + nbrSubCategory +
                '}';
    }

    public String get(){
        return "{id="+(float)id+"}";
    }
}
