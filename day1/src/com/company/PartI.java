package com.company;

import java.io.*;

public class PartI {

    /**
     * Take the file and read line by line. The rules are;
     * The calibration value is;
     * Combine the first digit and the last digit to form a single two-digit number.
     * Then, sum them all up.
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // First, read the file.
        File file = new File("src\\com\\company\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Next, retrieve the calibration values
        String st;
        int val = 0;
        while ((st = br.readLine()) != null){
            String s = extractIntFromString(st);
            // Add the calibration value to val.
            val += Integer.parseInt(s);
        }

        System.out.println(val);
    }

    private static String extractIntFromString(String str){
        // Remove all non-digits from the string.
        str = str.replaceAll("[^0-9]", ""); // regular expression
        int n = str.length();
        char first = str.charAt(0);
        char last = str.charAt(n-1);
        return first + String.valueOf(last);
    }
}
