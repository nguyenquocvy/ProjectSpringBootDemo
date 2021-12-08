package com.example.apidemo.Springboot.models;

import javax.persistence.*;

@Entity(name = "demo")
@Table(name = "username")
public class Account {
    @Id // chu thich khoa chinh
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tu tang gan gia trị id tăng
    private int id;

    @Column(name = "name")
    private String username;

    @Column(name = "pass")
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
