/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author khoders
 */
public class DateUtil {

    public static LocalDate dateToLocalDate(Date date) {
        try {
            if (date == null) {
                return null;
            }
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static Date localDateToDate(LocalDate localDate) {
        try {
            if (localDate == null) {
                return null;
            }
            return Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static LocalDate dateToLocalDate(Date date, String pattern) {
        try {
            if (date == null) {
                return null;
            }
            if (date instanceof java.sql.Date) {
                date = new java.util.Date(date.getTime());
            }
            LocalDate newDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            newDate = strToLocalDate(localDateToString(newDate, pattern), pattern);
            return newDate;
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Err with date: " + e.getMessage());
            return null;
        }
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            date = new Date();
            Instant currentInstant = date.toInstant();
            ZonedDateTime zonedDateTime = currentInstant.atZone(ZoneId.systemDefault());
            LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

            return localDateTime;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static Date strToDate(String str, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            return dateFormat.parse(str.trim());

        } catch (ParseException e) {
            e.getMessage();
        }

        return null;
    }

    public static String localDateToString(LocalDate localDate, String pattern) {
        try {
            if (localDate == null) {
                return null;
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
            String text = localDate.format(dateFormat);

            return text;

        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String pattern) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
            String text = localDateTime.format(dateFormat);

            return text;

        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public static LocalDate strToLocalDate(String str, String pattern) {
        try {
            if (!str.equals("")) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);
                LocalDate localDate = LocalDate.parse(str.trim(), dateFormat);

                return localDate;
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public static LocalDate toNewDateFormat(String str, String prevPattern, String newPattern) {
        try {
            LocalDate newLocalDate = null;
            if (str != null || !str.equals("")) {
                LocalDate prevFormattedDate = LocalDate.parse(str.trim(), DateTimeFormatter.ofPattern(prevPattern));
                String newDate = DateTimeFormatter.ofPattern(newPattern).format(prevFormattedDate);
                newLocalDate = LocalDate.parse(newDate);
                return newLocalDate;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static LocalDateTime strToLocalDateTime(String str, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime dateTime = LocalDateTime.parse(str.trim(), formatter);
            return dateTime;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static LocalDate parseLocalDateWithPattern(String str, String pattern) {
        try {
            if (!str.equals("")) {
                String datePattern = "\\d{2}/\\d{2}/\\d{4}";
                boolean isDate1 = str.trim().matches(datePattern);

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(pattern);

                if (isDate1) {
                    LocalDate localDate = LocalDate.parse(str.trim(), dateFormat);

                    return localDate;
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public static LocalDate _7DaysFromToday() {
        return LocalDate.now().plusDays(7);
    }

    public static LocalDate _7DaysBeforeToday() {
        return LocalDate.now().minusDays(7);
    }

    public static LocalDate nextWeekDate() {
        return LocalDate.now().plusDays(LocalDate.now().getDayOfWeek().getValue());
    }

    public static LocalDate previousWeekDate() {
        return LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue());
    }
}
