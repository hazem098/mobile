package com.example.projet_mobile.model;

public class user {
    String name , blood,email,adr,pass,tel,type,currentUserId;

    public user() {
    }

    public user(String name, String blood, String email, String adr, String pass, String tel, String type, String currentUserId) {
        this.name = name;
        this.blood = blood;
        this.email = email;
        this.adr = adr;
        this.pass = pass;
        this.tel = tel;
        this.type = type;
        this.currentUserId = currentUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
}
