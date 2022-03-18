package com.codename1.entities;

public class NftComment {
    private int id;
    private String postDate;
    private int likes;
    private int dislikes;
    private String comment;
    private String nft;
    private String user;

    public NftComment() {

    }

    public NftComment(String postDate, int likes, int dislikes, String comment, String nft) {
        this.postDate = postDate;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comment = comment;
        this.nft = nft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNft() {
        return nft;
    }

    public void setNft(String nft) {
        this.nft = nft;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NftComment{" + "postDate=" + postDate + ", likes=" + likes + ", dislikes=" + dislikes + ", comment=" + comment + ", nft=" + nft + ", user=" + user + '}';
    }

}
