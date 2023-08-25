package com.itai.coupons.entities;

import com.itai.coupons.dto.User;
import com.itai.coupons.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String userPassword;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private CompanyEntity company;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<PurchaseEntity> purchasesList;

    public UserEntity() {
    }

    public UserEntity(int id) {
        this.id = id;
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.userPassword = user.getPassword();
        this.userName = user.getUserName();
        this.userType = user.getUserType();
        if (this.userType==UserType.COMPANY) {
            this.company = new CompanyEntity();
            this.company.setId(user.getCompanyId());
        }
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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


    public CompanyEntity getCompany() {
        return company;

    }


    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public List<PurchaseEntity> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<PurchaseEntity> purchasesList) {
        this.purchasesList = purchasesList;
    }
}
