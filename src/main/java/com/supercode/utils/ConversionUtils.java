package com.supercode.utils;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * Conversion-related utility class
 */
public class ConversionUtils {

    /**
     * Prevents instantiation
     */
    private ConversionUtils() {}


    /**
     * Converts a char array to a String
     * @param chars Array of characters
     * @return String formed by the array of characters
     */
    public static String chars2String(char[] chars) {
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }
        return result.toString();
    }


    /**
     * Converts a string to a Document
     * @param contents String to convert
     * @return Document object containing contents
     */
    public static Document string2Document(String contents) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document result = null;
        try {
            builder = factory.newDocumentBuilder();
            result = builder.parse(new InputSource(new StringReader(contents)));
            result.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Unable to convert string to Document: " + e.getMessage());
        }
        return result;
    }


    /**
     * Converts a String array into a string separated by the separator passed
     * as parameter
     * @param separator String to put between every element of the array
     * @param array List to convert
     * @return String of the array with a separator between each elements
     */
    public static String implode(String separator, String[] array) {
        if (array == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<array.length; i++) {
            sb.append(array[i]).append(array.length - 1 == i ? "" : separator);
        }
        return sb.toString();
    }


    /**
     * Pads a number with 0 until it has the expected amount of digits.
     * If the numToPad has more than the amount of digit expected,
     * no change will be made to the String.
     * @param numToPad Number to pad with 0s
     * @param digitExpected Number of digits expected
     * @return Padded number
     */
    public static String zeroPad(String numToPad, int digitExpected) {
        if (numToPad.length() == digitExpected) {
            return numToPad;
        }
        StringBuilder sb = new StringBuilder();
        while ((digitExpected---numToPad.length()) > 0) {
            sb.append("0");
        }
        return sb.toString() + numToPad;
    }


    /**
     * Removes extra decimals.
     * @param decimalExpected Maximum number of decimal
     * @param num Number to test decimal
     * @return number with less or the same amount of decimalExpected
     */
    public static double fixedDecimal(int decimalExpected, double num) {
        String[] fullNum = (""+(num)).split("\\.", 2);
        String decimal = zeroPad(fullNum[1], decimalExpected).substring(0, decimalExpected);
        return Double.parseDouble(((int)num)+"."+decimal);
    }


    /**
     * Convert special HTML characters into their HTML entity equivalent
     * @param s String to sanitize
     * @return Sanitized/safe string
     */
    public static String htmlspecialchars(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '<': result.append("&lt;"); break;
                case '>': result.append("&gt;"); break;
                case '&': result.append("&amp;"); break;
                case '"': result.append("&quot;"); break;
                case '\'': result.append("&apos;"); break;
                default: result.append(c);
            }
        }
        return result.toString();
    }

}
