package com.ironman.restaurantmanagment.shared.util;

import java.time.LocalDate;

public class DateHelper {

    public static String LocalDateToString(LocalDate date) {
        return date!=null ? date.toString() : null;
    }
}
