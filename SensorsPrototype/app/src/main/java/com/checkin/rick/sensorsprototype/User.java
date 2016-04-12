package com.checkin.rick.sensorsprototype;

/**
 * Created by rick on 21-3-2016.
 */
public class User {
    String username, password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserLocalStore{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
