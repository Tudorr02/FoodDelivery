package com.FoodDeliveryApp.Models;


public abstract class Users{

    private String lastName;
    private String firstName;
    private String userName;
    private String password;
    private String userID;
    private String phoneNumber;

    public Users(String lastName, String firstName, String userName, String password, String userID, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.userID = userID;
        this.phoneNumber = phoneNumber;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserID() {
        return userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Users{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userID='" + userID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
