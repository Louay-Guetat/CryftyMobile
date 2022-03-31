package com.mycomany.entites;

public class BlogArticle {

    private int id;
    private String title,contents,author,category;
    private String date;
    private String image;

    public BlogArticle() {
    }

    public BlogArticle(int id, String title, String contents, String category, String author, String date) {
        this.id = id;
        this.title=title;
        this.contents=contents;
        this.category=category;
        this.author=author;
        this.date=date;

    }
  /*  public BlogArticle(String title, String contents, String category, String author, String date) {
        this.title=title;
        this.contents=contents;
        this.category=category;
        this.author=author;
        this.date=date;

    }*/

    public BlogArticle(String toString, String title, String contents, String category, String format, String format1) {
    }

    public BlogArticle(String title, String contents, String category, String author, String toString) {
    }

    public BlogArticle(String title,String contents,String category,String author) {
        this.title = title;
        this.contents = contents;
        this.category = category;
        this.author = author;
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
