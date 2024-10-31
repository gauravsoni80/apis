package com.users.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private String id;
    private String name;
    private String lname;
    private String email;
    private String address;
    private String phone;
    private String country;
    private String state;
    private String logo;
    private int cr_number;
    private String password;
    private int status;
    private Long timestamp;

    public Account() {
    }

    public Account(String name, String lname, String email, String address, String phone, String country, String state, String logo, int cr_number, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lname = lname == "" ? "" : lname;
        this.email = email;
        this.address = address == "" ? "" : address;
        this.phone = phone == "" ? "" : phone;
        this.country = country == null ? "India" : country;
        this.state = state == null ? "Rajasthan" : state;
        this.logo = logo == "" ? "" : logo;
        this.cr_number = cr_number;
        this.password = password == "" ? "admin" + new Random().nextInt(0, 5) : password;
        this.status = 0;
        this.timestamp = Long.parseLong(String.valueOf(System.currentTimeMillis() / 1000));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getCr_number() {
        return cr_number;
    }

    public void setCr_number(int cr_number) {
        this.cr_number = cr_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", logo='" + logo + '\'' +
                ", cr_number=" + cr_number +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}