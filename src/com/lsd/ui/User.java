package com.lsd.ui;

public class User {
    private String userName;
    //char[] passWord; //old
    private String passWord;

    User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }
    User(){}

}
