package org.user.registration.ms.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ISO8601Validator {
    public static boolean isValidISO8601(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return false;
        }
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException _) {
            return false;
        }
    }
}
