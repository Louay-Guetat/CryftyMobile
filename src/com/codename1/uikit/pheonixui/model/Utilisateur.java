package com.codename1.uikit.pheonixui.model;

public class Utilisateur {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int age;
    private String address;
    private String motdepasse;

    public Utilisateur(String username, String firstName, String lastName, String email, String phoneNumber, int age, String address, String motdepasse) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
        this.motdepasse = motdepasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", age=" + age + ", address=" + address + ", motdepasse=" + motdepasse + '}';
    }

    public Utilisateur() {
    }

    public Utilisateur(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Utilisateur(int id) {
        this.id = id;
    }


    public Utilisateur(int id, String username, String firstName, String lastName, String email, String phoneNumber, int age, String address, String motdepasse) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
        this.motdepasse = motdepasse;
    }

    public Utilisateur(int id, String username, String email, String address, String motdepasse) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.motdepasse = motdepasse;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }


}
