package Entities;

import java.util.Date;

public class Message {
private int id ;
private String contenu;
private String  createdAt;
private String  Sender;
    public Message() {
    }

    public Message(int id, String contenu,String createdAt) {
        this.id = id;
        this.contenu = contenu;
        this.createdAt = createdAt;
    }

    public Message(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    @Override
    public String toString() {
        return

                ", contenu='" + contenu + '\'' +
                ", createdAt=" + createdAt
                ;
    }
}
