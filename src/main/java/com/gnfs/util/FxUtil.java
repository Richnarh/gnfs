/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Pascal
 */
public class FxUtil {

    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String yyyyMMdd = "yyyy/MM/dd";
    public static final String _ddMMyyyy = "dd-MM-yyyy";
    public static final String _yyyyMMdd = "yyyy-MM-dd";
    public static final String mmmyyyy = "mmm/yyyy";
    public static final String ddMMyyyyhm = "dd/MM/yyyy - hh:mm";
    public static final String ddMMyyyyhma = "dd/MM/yyyy - hh:mm a";
    public static final String ddMMyyhhmmsss = "dd/MM/yyyy - hh:mm:ss";
    public static final String ddMMyyhhmmsssa = "dd/MM/yyyy - hh:mm:ss a";
    public static final String EEEMMMMdyyyyhhmma = "EEE, MMMM d, yyyy hh:mm a";
    public static final String EEEMMMdyyyyhhmma = "EEE, MMM d, yyyy hh:mm a";
    public static final String EEEMMMdyyyy = "EEE, MMM d, yyyy";

    public static String parseLocalDateString(LocalDate localDate, String pattern) {
        try {
            if (localDate == null) {
                return null;
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
            String text = localDate.format(dateFormat);

            return text;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String generateShortCode() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }

    public static String genId() {
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                boolean uuidStringMatcher = id.matches(".*[a-zA-Z]+.*");

                if (uuidStringMatcher == false) {
                    Random random = new Random();
                    char cha = (char) (random.nextInt(26) + 'a');
                    int numToReplace = random.nextInt(9);

                    id = id.replaceAll(String.valueOf(numToReplace), String.valueOf(cha));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
