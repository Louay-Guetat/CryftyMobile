/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HP
 */
public class User {
    protected int id ;
    protected String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return   username ;
    }
    
}
