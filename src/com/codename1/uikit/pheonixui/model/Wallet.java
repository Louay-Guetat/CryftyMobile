package com.codename1.uikit.pheonixui.model;

public class Wallet {
    private String id;
    private String walletAddress;
    private String walletLabel;
    private String imgPath;
    private String code;
    private Float balance;
    private String client;
    private boolean isMain;
    private boolean isActive;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Wallet() {
    }

    public Wallet(String id, String walletLabel, String code, Float balance) {
        this.id = id;
        this.walletLabel = walletLabel;
        this.code = code;
        this.balance = balance;
    }

    public Wallet(String id, String walletLabel, String imgPath, String code, Float balance) {
        this.id = id;
        this.walletLabel = walletLabel;
        this.imgPath = imgPath;
        this.code = code;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWalletLabel() {
        return walletLabel;
    }

    public void setWalletLabel(String walletLabel) {
        this.walletLabel = walletLabel;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                ", walletLabel='" + walletLabel + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", code='" + code + '\'' +
                ", balance=" + balance +
                ", client='" + client + '\'' +
                ", isMain=" + isMain +
                ", isActive=" + isActive +
                '}';
    }
}
