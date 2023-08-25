package com.itai.coupons.logic;

import com.itai.coupons.dal.IUserDal;
import com.itai.coupons.dto.SuccessfulLoginDetails;
import com.itai.coupons.dto.User;
import com.itai.coupons.dto.UserLoginData;
import com.itai.coupons.entities.UserEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.enums.UserType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.JWTUtils;
import com.itai.coupons.utils.PasswordEncryption;
import com.itai.coupons.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersLogic {
    private IUserDal usersDal;
    private CompaniesLogic companiesLogic;


    @Autowired
    public UsersLogic(IUserDal usersDal, CompaniesLogic companiesLogic) {
        this.usersDal = usersDal;
        this.companiesLogic = companiesLogic;
    }

    public String login(UserLoginData loginData) throws ApplicationException {
        validateUserName(loginData.getUserName());
        validateUserPassword(loginData.getPassword());
        String encodedPassword = PasswordEncryption.encryptPassword(loginData.getPassword());
        loginData.setPassword(encodedPassword);
        SuccessfulLoginDetails successfulLoginDetails = this.usersDal.login(loginData.getUserName(), loginData.getPassword());
        if (successfulLoginDetails == null) {
            throw new ApplicationException(ErrorType.LOGIN_FAILED, "Failed to login");
        }
        try {
            String token = JWTUtils.createJWT(successfulLoginDetails);
            StatisticsUtils.sendStatistics("Login has succeeded for user: " + loginData.getUserName());
            return token;
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.TOKEN_WAS_NOT_CREATED, "Could not create token", e);
        }

    }


    public int addUser(User user) throws ApplicationException {
        if (user.getUserType() == null) {
            user.setUserType(UserType.CUSTOMER);
        }
        validateUser(user);
        String encodedPassword = PasswordEncryption.encryptPassword(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity userEntity = new UserEntity(user);
        usersDal.save(userEntity);
        StatisticsUtils.sendStatistics("User registration, user: " + user.getUserName());
        return user.getId();
    }

    public User getUser(int userId) throws ApplicationException {
        UserEntity userEntity = usersDal.findById(userId).get();
        User user = new User(userEntity);
        StatisticsUtils.sendStatistics("User read, user: " + userId);
        return user;
    }

    public void removeUser(int userId) throws ApplicationException {
        usersDal.deleteById(userId);
        StatisticsUtils.sendStatistics("User deleted, user ID: " + userId);
    }

    public void updateLoginDetail(UserLoginData userLoginData) throws ApplicationException {
        validateUserName(userLoginData.getUserName());
        validateUserPassword(userLoginData.getPassword());
        String encodedPassword = PasswordEncryption.encryptPassword(userLoginData.getPassword());
        userLoginData.setPassword(encodedPassword);
        this.usersDal.updateLoginDetails(userLoginData.getUserName(), userLoginData.getPassword());
    }

    public void updateUser(User user) throws ApplicationException {
        validateUser(user);
        String encodedPassword = PasswordEncryption.encryptPassword(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity userEntity = new UserEntity(user);
        usersDal.save(userEntity);
        StatisticsUtils.sendStatistics("User updated, new user: " + user.toString());
    }

    public List<User> getAllUsers() throws ApplicationException {
        return usersDal.getAllUsers();
    }
//
//    public List<User> getAllUsersByCompanyId(Integer companyId) throws ApplicationException {
//        return (List<User>) usersDal.getAllUsersByCompanyId(companyId);
//    }

    private void validateUser(User user) throws ApplicationException {
        validateUserName(user.getUserName());
        validateUserPassword(user.getPassword());
        if (user.getUserType() == UserType.COMPANY) {
            validateCompanyId(user.getCompanyId());
        }
        try {
            StatisticsUtils.sendStatistics(user.getUserName() + " Has been validated");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validateCompanyId(int companyId) throws ApplicationException {

        if (!companiesLogic.isCompanyIdExist(companyId)) {
            throw new ApplicationException(ErrorType.COMPANY_ID_DOES_NOT_EXIST);
        }
    }

    private void validateUserName(String userName) throws ApplicationException {

        if (userName.length() > 45) {
            throw new ApplicationException(ErrorType.NAME_TOO_LONG);
        }
        if (userName.length() < 4) {
            throw new ApplicationException(ErrorType.NAME_TOO_SHORT);
        }
    }

    private void validateUserPassword(String userPassword) throws ApplicationException {
        char[] userPasswords = userPassword.toCharArray();
        boolean hasUpperCaseInPassword = false;
        boolean hasLowerCaseInPassword = false;
        boolean hasDigitInPassword = false;

        if (userPassword.length() > 15) {
            throw new ApplicationException(ErrorType.PASSWORD_TOO_LONG);
        }
        if (userPassword.length() < 4) {
            throw new ApplicationException(ErrorType.PASSWORD_TOO_SHORT);
        }
        for (int i = 0; i < userPassword.length(); i++) {
            if (Character.isUpperCase(userPasswords[i])) {
                hasUpperCaseInPassword = true;
            }
            if (Character.isLowerCase(userPasswords[i])) {
                hasLowerCaseInPassword = true;
            }
            if (Character.isDigit(userPasswords[i])) {
                hasDigitInPassword = true;
            }
        }
        if (!hasDigitInPassword) {
            throw new ApplicationException(ErrorType.USER_PASSWORD_MISSING_KEY);
        }
        if (!hasUpperCaseInPassword) {
            throw new ApplicationException(ErrorType.USER_PASSWORD_MISSING_KEY);
        }
        if (!hasLowerCaseInPassword) {
            throw new ApplicationException(ErrorType.USER_PASSWORD_MISSING_KEY);
        }

    }

    //getByCompanyId
    boolean isUserIdExist(int userId) throws ApplicationException {
        return usersDal.existsById(userId);
    }
//    public boolean checkPassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
}
