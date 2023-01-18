package se2203b.assignments.adminapp;

import javafx.beans.property.*;


public class Customer {
    private StringProperty name;
    private StringProperty userName;
    private StringProperty address;
    private StringProperty emailAddress;
    private StringProperty password;

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    public StringProperty addressProperty() {
        return address;
    }
    public String getAddress() {
        return address.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setPassword(String pass) {
        this.password.set(pass);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setUserName(String name) {
        this.userName.set(name);
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public String getUserName() {
        return userName.get();
    }

    //A constructor for Customer
    public Customer(String customerName, String userName, String address, String email, String password){
        this.name = new SimpleStringProperty(customerName);
        this.userName = new SimpleStringProperty(userName);
        this.address = new SimpleStringProperty(address);
        this.emailAddress = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);


    }

}
