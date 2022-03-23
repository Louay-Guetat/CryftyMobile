package com.codename1.entities;

public class Nft {
    private int id;
    private String title;
    private String description;
    private float price;
    private String creationDate;
    private String image;
    private int likes;
    private Category category;
    private SubCategory subCategory;
    private String owner;
    private Node currency;

    public Nft() {
    }

    public Nft(String title, String description, float price, String creationDate, String image, int likes,
               Category category,SubCategory subCategory,String owner, Node currency) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.image = image;
        this.likes = likes;
        this.category=category;
        this.subCategory= subCategory;
        this.owner = owner;
        this.currency=currency;
    }

    public Nft(String title){
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Node getCurrency() {
        return currency;
    }

    public void setCurrency(Node currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Nft:" + "\n-Title: " + title + "\n-Description: " + description + "\n-Price: " + price + "\n-CreationDate: " + creationDate + "\n-Image: " + image + "\n-Likes: " + likes + "\n-Category: " + category + "\n-SubCategory: " + subCategory + "\n-Owner: " + owner + "\n-Currency: " + currency+"\n********************************\n";
    }
}
