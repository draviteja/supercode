package com.supercode.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Cryptography-related utility class
 */
public class CryptUtils {

    /**
     * Prevents instantiation
     */
    private CryptUtils() {}


    /**
     * Hashes a String using Secure Hash Algorithm 512 bits
     * @param str String to hash
     * @return hash
     */
    public static String sha512(String str) {
        return sha(512, str);
    }


    /**
     * Hashes a String using Secure Hash Algorithm 256 bits
     * @param str String to hash
     * @return hash
     */
    public static String sha256(String str) {
        return sha(256, str);
    }


    /**
     * Hashes a String using the given version of Secure Hash Algorithm
     * @param bits SHA version (bits) ex: 256, 512, ...
     * @param str String to hash
     * @return hash
     */
    private static String sha(int bits, String str) {
        String result = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-" + bits);
            byte[] bytes = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            result = new BigInteger(1, bytes).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Uses the SHA512 algorithm to create a salted hash for the given password
     * @param str String to hash
     * @return hash:salt
     */
    public static String sha512WithRandomSalt(String str) {
        String salt = getSalt();
        return sha512(str + salt) + ":" + salt;
    }


    /**
     * Uses the SHA256algorithm to create a salted hash for the given password
     * @param str String to hash
     * @return hash:salt
     */
    public static String sha256WithRandomSalt(String str) {
        String salt = getSalt();
        return sha256(str + salt) + ":" + salt;
    }


    /**
     * Validates a string generated with CryptUtils.sha512WithRandomSalt
     *
     * USAGE:
     * String hashedPassword = sha512WithRandomSalt("lol"); // this is what you store in the db
     * boolean isPasswordGood = validateSha512WithSalt("lol", hashedPassword);
     *
     * @param str String (not hashed)
     * @param hashSalt hash:salt
     * @return Whether the password matches or not
     */
    public static boolean validateSha512WithSalt(String str, String hashSalt) {
        String hash = hashSalt.split(":")[0];
        String salt = hashSalt.split(":")[1];
        return sha512(str + salt).equalsIgnoreCase(hash);
    }


    /**
     * Validates a string generated with CryptUtils.sha256WithRandomSalt
     *
     * USAGE:
     * String hashedPassword = sha256WithRandomSalt("lol"); // this is what you store in the db
     * boolean isPasswordGood = validateSha256WithSalt("lol", hashedPassword);
     *
     * @param str String (not hashed)
     * @param hashSalt hash:salt
     * @return Whether the password matches or not
     */
    public static boolean validateSha256WithSalt(String str, String hashSalt) {
        String hash = hashSalt.split(":")[0];
        String salt = hashSalt.split(":")[1];
        return sha256(str + salt).equalsIgnoreCase(hash);
    }


    /**
     * Generates a secure salt
     * @return Random salt
     */
    public static String getSalt() {
        byte[] salt = new byte[24];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        return base64encode(salt);
    }



    /**
     * Rotates the alphabetic characters in a string by 13 positions
     * @param str String to encrypt with rot13
     * @return Encrypted String
     */
    public static String rot13(String str) {
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) {
                c += 13;
            } else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) {
                c -= 13;
            }
            str = str.substring(0, i) + c + str.substring(i+1);
        }
        return str;
    }


    /**
     * Encodes a byte array using base64
     * @param bytes Byte array
     * @return Base64 encoded string
     */
    public static String base64encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }


    /**
     * Encodes a String using base64
     * @param str String to encode
     * @return Base64 encoded string
     */
    public static String base64encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }


    /**
     * Decodes a base64 encoded byte array
     * @param bytes Byte array
     * @return Decoded String
     */
    public static String base64decode(byte[] bytes) {
        String decodedString = "";
        try {
            decodedString = new String(Base64.getDecoder().decode(bytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }


    /**
     * Decodes a base64 encoded String
     * @param str Base64 encoded String
     * @return Decoded String
     */
    public static String base64decode(String str) {
        String decodedString = "";
        try {
            decodedString = new String(Base64.getDecoder().decode(str.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }

}
