package com.lattice.hostpitalapp.utils;

import com.lattice.hostpitalapp.config.ConstantsValue;

import java.util.*;

public class HelperClass {
    public static Map<String, List<String>> data = new HashMap<>();
    public static void InitializeAllValues() {
        data.put("orthopedic", Arrays.asList("arthritis", "backpain","tissueinjuries"));
        data.put("gynecology", Arrays.asList("dysmenorrhea"));
        data.put("dermatology", Arrays.asList("skininfection", "skinburn"));
        data.put("ent", Arrays.asList("earpain"));
    }

    public static String removeSpaceAndSmallCases(String inputString) {
        String stringWithoutSpaces = inputString.replaceAll(" ", "");
        return stringWithoutSpaces.toLowerCase();
    }

    public static String findSpecialistViaSymptoms(String inputString) {
        return data.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(inputString))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

    }
}
