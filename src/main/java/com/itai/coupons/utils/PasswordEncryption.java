package com.itai.coupons.utils;

import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryption {


    public static String encryptPassword(String password) throws ApplicationException {
        try {
            byte[] salt = "Religion is stupid anyway. I mean, a virgin gets pregnant by a ghost! You would never get away with that in a divorce court, would you?".getBytes();
            byte[] saltedPassword = concatenateByteArrays(salt, password.getBytes(StandardCharsets.UTF_8));
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedPassword = digest.digest(saltedPassword);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedPasswordString = Base64.getEncoder().encodeToString(encodedPassword);
            return encodedSalt + encodedPasswordString;
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to encrypt password", e);
        }
    }
    public static boolean verifyPassword(String password, String hashedPassword) throws ApplicationException {
        try {
            String encodedSalt = hashedPassword.substring(0, 24);
            String encodedStoredPassword = hashedPassword.substring(24);

            byte[] salt = Base64.getDecoder().decode(encodedSalt);
            byte[] saltedPassword = concatenateByteArrays(salt, password.getBytes(StandardCharsets.UTF_8));
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedPassword = digest.digest(saltedPassword);
            String encodedInputPassword = Base64.getEncoder().encodeToString(encodedPassword);

            return encodedStoredPassword.equals(encodedInputPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to verify password", e);
        }
    }

    private static byte[] concatenateByteArrays(byte[] array1, byte[] array2) {
        byte[] combined = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, combined, 0, array1.length);
        System.arraycopy(array2, 0, combined, array1.length, array2.length);
        return combined;
    }
}
