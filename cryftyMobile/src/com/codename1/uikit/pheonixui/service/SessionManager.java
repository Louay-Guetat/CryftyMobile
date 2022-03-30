/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui.service;

import com.codename1.io.Preferences;

/**
 * @author chemez
 */
public class SessionManager {

    public static Preferences pref; // 3ibara memoire sghira nsajlo fiha data


    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id;
    private static String userName;
    private static String email;
    private static String passowrd;
    private static String photo;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return Preferences.get("id", id);// kif nheb njib id user connecté apres njibha men pref
    }

    public static void setId(int id) {
        Preferences.set("id", id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserName() {
        return Preferences.get("username", userName);
    }

    public static void setUserName(String userName) {
        Preferences.set("username", userName);
    }

    public static String getEmail() {
        return Preferences.get("email", email);
    }

    public static void setEmail(String email) {
        Preferences.set("email", email);
    }

    public static String getPassowrd() {
        return Preferences.get("passowrd", passowrd);
    }

    public static void setPassowrd(String passowrd) {
        Preferences.set("passowrd", passowrd);
    }

    public static String getPhoto() {
        return Preferences.get("photo", photo);
    }

    public static void setPhoto(String photo) {
        Preferences.set("photo", photo);
    }


}
