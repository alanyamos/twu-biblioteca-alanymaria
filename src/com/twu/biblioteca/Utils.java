package com.twu.biblioteca;

import java.text.Normalizer;

public class Utils {
    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    public static boolean isAvailable(IsAvailable content, String status) {
        return !content.getStatus().equals(status);
    }
}
