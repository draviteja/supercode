package com.supercode.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class related to files
 */
public class FileUtils {

    /**
     * Prevents instantiation
     */
    private FileUtils() {}


    /**
     * Puts the content of a file in a String
     * @param fullPath Path to the file
     * @return Content of the file
     */
    public static String getFileContents(String fullPath) {
        String contents = "";
        try {
            contents = new String(Files.readAllBytes(Paths.get(fullPath)));
        } catch (IOException e) {
            System.out.println("Unable to convert file to string: " + e.getMessage());
        }
        return contents;
    }


    /**
     * Function that gets the size of a file in bytes
     * @param fullPath Path of the file
     * @return File size in bytes
     */
    public static long getFileSizeInByte(String fullPath) {
        return (new File(fullPath).length());
    }


    /**
     * Function that gets the size of a file in KB
     * @param fullPath Path of the file
     * @return File size in KB
     */
    public static double getFileSizeInKb(String fullPath) {
        return ConversionUtils.fixedDecimal(3, (double)getFileSizeInByte(fullPath)/1024d);
    }


    /**
     * Function that gets the size of a file in MB
     * @param fullPath Path of the file
     * @return File size in MB
     */
    public static double getFileSizeInMb(String fullPath) {
        return ConversionUtils.fixedDecimal(3, getFileSizeInKb(fullPath)/1024d);
    }


    /**
     * Writes data a file
     * @param data Data to put in file
     * @param fName File name
     * @param append Appends to the end of the file or not
     * @return Whether the file exists or not
     */
    public static boolean writeInFile(String data, String fName, boolean append) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fName, append)));
            out.println(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (new File(fName).exists());
    }


    /**
     * Gets the extension of a file or a path
     * @param fileNameOrPath Name of the file or path toward the file
     * @return The extension of the file
     */
    public static String getExtension(String fileNameOrPath) {
        return (fileNameOrPath != null && fileNameOrPath.contains(".")) ?
                fileNameOrPath.substring(fileNameOrPath.lastIndexOf(".") + 1) : "";
    }


    /**
     * Gets the name of a file without its path
     * <pre>
     * {@code
     * String path = "C:/some/path/example.txt";
     * System.out.println(getBaseName(path));
     * > example.txt
     * }
     * </pre>
     * @param fileNameOrPath Name of the file or path toward the file
     * @return A file name without any extension
     */
    public static String getBaseName(String fileNameOrPath) {
        if (SearchUtils.isInString(fileNameOrPath, "/") || SearchUtils.isInString(fileNameOrPath, "\\")) {
            Character separator = (fileNameOrPath.lastIndexOf('/')>=0) ?
                    '/' : (fileNameOrPath.lastIndexOf('\\')>=0) ? '\\' : null;
            return fileNameOrPath.substring(fileNameOrPath.lastIndexOf(separator)+1);
        }
        return fileNameOrPath;
    }


    /**
     * Removes everything after the last "." in a String
     * WARNING: If you pass a file such as example.exe.png is passed as
     * parameter, you might NOT get the desired effect!
     * <pre>
     * {@code
     * String path = "C:/some/path/example.txt";
     * System.out.println(stripExtension(path));
     * > C:/some/path/example
     * }
     * </pre>
     * @param s String to remove the extension from
     * @return String without its previous
     */
    public static String stripExtension(String s) {
        return s.indexOf('.') > -1 ? s.substring(0, s.lastIndexOf('.')) : s;
    }


    /**
     * Creates a directory at the given path if it doesn't already exist
     * @param pathToDir Path to the directory (e.g. /home/twin/DIRECTORY_NAME)
     * @return Whether the directory was created (true) or already existed (false)
     */
    public static boolean mkdirIfNotExist(String pathToDir) {
        File dir = new File(pathToDir);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                return true; // directory was created
            }
        }
        return false; // directory already exists
    }

}
