package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 1 of Advent of Code.
 * @author Ebba Nimér
 */
public class Main {

    // Regex pattern containing numbers in script and ints.
    static Pattern p = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine|zero|\\d))");

    // Map to store strings and corresponding int.
    static Map<String, Integer> map = new HashMap<>();

    /**
     * Reads the file containing the calibration values and sum up the values.
     * @param args args.
     */
    public static void main(String[] args) {
        initializeMap();
        try {
            System.out.println(calculateTotalValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the map with values.
     */
    private static void initializeMap() {
        map.put("zero", 0); map.put("one", 1); map.put("two", 2); map.put("three", 3); map.put("four", 4);
        map.put("five", 5); map.put("six", 6); map.put("seven", 7); map.put("eight", 8); map.put("nine", 9);
    }

    /**
     * Calculates the total calibration value.
     * @return Total value.
     * @throws IOException Whether there's an exception reading the file.
     */
    private static int calculateTotalValue() throws IOException {
        int val = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\com\\company\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                //val += Integer.parseInt(partOne(line));
                val += Integer.parseInt(partTwo(line));
            }
        }
        return val;
    }

    /**
     * Extracts the integers from each line and add them together.
     * @param str Line from file.
     * @return String containing first and last value of string.
     */
    private static String partOne(String str){
        // Remove all non-digits from the string.
        str = str.replaceAll("[^0-9]", ""); // regular expression
        int n = str.length();
        char first = str.charAt(0);
        char last = str.charAt(n-1);
        return first + String.valueOf(last);
    }

    /**
     * Gets the matches of the provided string, containing both alphabetic characters and integers.
     * @param str Line from file.
     * @return String containing first and last value of string.
     */
    private static String partTwo(String str) {
        Matcher matcher = p.matcher(str);
        String first = null;
        String last = null;

        // If the matcher finds a result in the string.
        if (matcher.find()) {
            // Set the first match to first
            first = matcher.group(1);
            last = first; // In case there's only one match
        }

        // While there are matches in the string, assign the last match to last.
        while (matcher.find()) {
            last = matcher.group(1);
        }

        // Verify whether the found value is a string or digits. If it is a string, retrieve the digit.
        first = map.containsKey(first) ? String.valueOf(map.get(first)) : first;
        last = map.containsKey(last) ? String.valueOf(map.get(last)) : last;

        return first + last;
    }
}
