package com.itai.coupons.dal;


import com.itai.coupons.dto.SuccessfulLoginDetails;
import com.itai.coupons.dto.User;
import com.itai.coupons.dto.UserLoginData;
import com.itai.coupons.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface IUserDal extends CrudRepository<UserEntity, Integer> {
    @Query("SELECT new com.itai.coupons.dto.User(u.userName, u.userPassword, u.userType, u.id, u.company.id) " +
            "FROM UserEntity u")
    List<User> getAllUsers();

    @Query("Select new com.itai.coupons.dto.SuccessfulLoginDetails(u.id, u.userName, u.userType, u.company.id) FROM UserEntity u where u.userName=:userName and u.userPassword=:password")
    SuccessfulLoginDetails login(String userName, String password);

    @Query("SELECT new com.itai.coupons.dto.User(u.userName, u.userPassword, u.userType, u.id, u.company.id) " +
            "FROM UserEntity u where u.company.id=:companyId")
    List<User> getAllUsersByCompanyId(Integer companyId);

    @Query("UPDATE com.itai.coupons.entities.UserEntity u SET userPassword =:password WHERE userName=:userName ")
    void updateLoginDetails(String userName, String password);
//
//    @Query("SELECT new com.itai.coupons.dto.User(u.userName, u.userPassword, u.userType, u.id, u.company.id) " +
//            "FROM UserEntity u where u.company.id=:companyId")
//    List<User> getAllUsersByCompanyId(Integer companyId);
}
