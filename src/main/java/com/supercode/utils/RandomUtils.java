package com.supercode.utils;

import java.security.SecureRandom;

/**
 * Utility class with multiple methods related to the generation of
 * random elements
 */
public class RandomUtils {

    /**
     * Prevents instantiation
     */
    private RandomUtils() {}


    /**
     * Generates a random lowercase character
     * @return Random lowercase char
     */
    public static char randomLowercaseLetter() {
        return (char)((int)'a'+(new SecureRandom()).nextInt(('z'-'a')+1));
    }


    /**
     * Generates a random uppercase character
     * @return Random uppercase char
     */
    public static char randomUppercaseLetter() {
        return (char)((int)'A'+(new SecureRandom()).nextInt(('Z'-'A')+1));
    }


    /**
     * Generates a random character
     * @return Random char
     */
    public static char randomLetter() {
        return new SecureRandom().nextBoolean() ? randomLowercaseLetter() : randomUppercaseLetter();
    }


    /**
     * Generates a random integer between min and max
     * @param min Minimum value
     * @param max Maximum value
     * @return Random integer
     */
    public static int randomInteger(int min, int max) {
        return (min+(new SecureRandom()).nextInt(max-min+1));
    }


    /**
     * Generates a random password
     * @param length How much characters to randomly generate
     * @return Random password
     */
    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while (length --> 0) {
            sb.append(random.nextBoolean() ? randomLetter() : ""+randomInteger(0, 9));
        }
        return sb.toString();
    }

}
