package com.lsd.ui;

public class User {
    String userName;
    char[] passWord;

    User(String userName, char[] passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }
    public char[] getPassWord() {
        return passWord;
    }

}
