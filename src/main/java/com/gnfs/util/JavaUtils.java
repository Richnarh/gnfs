/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

/**
 *
 * @author khoders
 */
public class JavaUtils {

    static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JavaUtils.class.getName());

    public static String genId() {
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                boolean uuidStringMatcher = id.matches(".*[a-zA-Z]+.*");
                if (!uuidStringMatcher) {
                    Random random = new Random();
                    char cha = (char) (random.nextInt(26) + 'a');
                    int numToReplace = random.nextInt(9);
                    id = id.replaceAll(String.valueOf(numToReplace), String.valueOf(cha));
                }
            } catch (Exception e) {
                e.getMessage();
            }
            return id;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static String generateCode() {

        String code = DateUtil.localDateToString(LocalDate.now(), "ddMMyy")
                + UUID.randomUUID().toString().substring(0, 7).toUpperCase();

        return code;
    }

    public static String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    public static String generateId() {
        String id = DateUtil.localDateToString(LocalDate.now(), "ddMM")
                + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        return id;
    }

    public static String generateRefNo() {
        String uuid = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String uuidDate = DateUtil.localDateToString(LocalDate.now(), "ddMMyy");

        return uuidDate + "/" + uuid;
    }

    public static String generatePO() {
        String uuid = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String uuidDate = DateUtil.localDateToString(LocalDate.now(), "ddMMyy");

        return "PO" + uuidDate + uuid;
    }

    public static String generateIN() {
        String uuid = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String uuidDate = DateUtil.localDateToString(LocalDate.now(), "ddMMyy");

        return "INV" + uuidDate + uuid;
    }
    
    public static String generateReceipt() {
        String uuid = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String uuidDate = DateUtil.localDateToString(LocalDate.now(), "ddMMyy");

        return uuidDate + uuid;
    }
    
    public static String serverIP() {
        try {
            InetAddress IP = InetAddress.getLocalHost();
            logger.log(Level.INFO, "IP : {0}", IP.getHostAddress());
            return IP.getHostAddress();
        } catch (UnknownHostException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public static boolean pathExist(Path path) {
        boolean isCreated = false;
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
                isCreated = true;
            } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }
        return isCreated;
    }

    public static String computeTotalSize(double totalSize) {
        double size = 0;
        double bytes = 0;
        double kb = 0;
        double mb = 0;
        double gb = 0;
        double tb = 0;
        String fileSize = "Bytes";
        if (totalSize < 1024) {
            bytes = totalSize;
            size = bytes;
            fileSize = "Bytes";
        } else if (totalSize > 1024) {
            kb = (totalSize / 1024);
            size = kb;
            fileSize = "KB";
        }
        if (kb > 1024) {
            mb = (kb / 1024);
            size = mb;
            fileSize = "MB";
        }
        if (mb > 1024) {
            gb = (mb / 1024);
            size = gb;
            fileSize = "GB";
        }
        if (gb > 1024) {
            tb = (gb / 1024);
            size = tb;
            fileSize = "TB";
        }
        String result = String.format("%.2f", size) + " " + fileSize;
        return result;
    }
    
    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }
    
    public static Integer toInteger(String str) {
        Integer intValue = null;
        if(str != null && !str.isEmpty()){
            try {
                intValue =  Integer.parseInt(str);
            } catch (NumberFormatException  e) {
                e.getMessage();
            }
        }
        return intValue;
    }
    
    public static String removeTrailingZero(String input) 
    {
         input = !input.contains(".") ? input : input.replaceAll("0*$", "").replaceAll("\\.$", "");
         
         return input;
    }
    public static String objectToString(Object object){
        if(object != null){
            return String.valueOf(object);
        }
        return null;
    }
    public static String capitalizeOnlyFirst(String str){
        return str != null && !str.isEmpty() ? str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase() : null;
    }
    public static String capitalizeFirst2nd(String str){
        return str != null && !str.isEmpty() ? str.substring(0,1).toUpperCase() + str.substring(0,1).toUpperCase() : null;
    }
    public static String capitalizeAll(String str){
        return str != null && !str.isEmpty() ? str.toUpperCase() : null;
    }
    public static String lowerCaseAll(String str){
        return str != null && !str.isEmpty() ? str.toLowerCase() : null;
    }
    
    public static String first2UpperCase(String str){
        if (isNotNullOrEmpty(str)){
            char[] charArray = str.toCharArray();
            boolean foundSpace = true;
            
            for (int i = 0; i < charArray.length; i++)
            {
                if (Character.isLetter(charArray[i]))
                {
                    if (foundSpace)
                    {
                        charArray[i] = Character.toUpperCase(charArray[i]);
                        foundSpace = false;
                    }
                } else
                {
                    foundSpace = true;
                }
            }
           return str = String.valueOf(charArray);
    }
    return "";
   }
    public static String removeCharBefore(String str, String charr) {
        return str != null ? str.replaceAll(".*" + charr + "", "") : null;
    }

    public static String[] splitStr(String str, String charr) {
        return str != null ? str.split(charr) : null;
    }
}
