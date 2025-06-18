package com.lsd.ui;

public class User {
    private String userName;
    //char[] passWord; //old
    private String passWord;
    private int score;

    User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.score = 0;
    }
    User(String userName, String passWord, int score) {
        this.userName = userName;
        this.passWord = passWord;
        this.score = score;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    User(){}

}
