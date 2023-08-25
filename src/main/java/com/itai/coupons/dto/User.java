package com.itai.coupons.dto;

import com.itai.coupons.entities.UserEntity;
import com.itai.coupons.enums.UserType;

import java.util.Objects;

public class User {
    private String userName;
    private String password;
    private UserType userType;
    private int id;
    private Integer companyId;

    private String companyName;

    public User(UserEntity user) {
        this.userName = user.getUserName();
        this.password = user.getUserPassword();
        this.userType = user.getUserType();
        this.id = user.getId();
        if (this.userType == UserType.COMPANY) {
            this.companyId = user.getCompany().getId();
            this.companyName = user.getCompany().getCompanyName();
        }

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userType = null;
    }

    public User(String userName, String password, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.companyId = null;
    }

    public User(String userName, String password, UserType userType, int id, Integer companyId) {
        this(userName, password, userType);
        this.id = id;
        this.companyId = companyId;
    }

    public User(String userName, String password, UserType userType, int id) {
        this(userName, password, userType);
        this.id = id;
    }

    public User(String userName, String password, UserType userType, Integer companyId) {
        this(userName, password, userType);
        this.companyId = companyId;
    }

    public User(String userName, String password, UserType userType, int id, Integer companyId, String companyName) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public User() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + password + '\'' +
                ", userType=" + userType +
                ", userId=" + id +
                ", companyId=" + companyId +
                '}';
    }

}
