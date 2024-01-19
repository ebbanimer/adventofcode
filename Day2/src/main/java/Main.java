import java.io.*;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.round;

public class Main {

    /**
     * Part 2;
     * In each game; what is the fewest number of cubes of each color that could have been in the bag to make the game
     * possible?
     *
     * This means;
     * Check the max number of blue, red, and green for each game-round.
     *
     * Power of a set; red * green * blue
     *
     * Task;
     * For each game, find the power and sum all of them.
     * @param args
     */

    private static int maxRed;
    private static int maxBlue;
    private static int maxGreen;

    public static void main(String[] args) {
        int totalVal = startGame();
        System.out.println(totalVal);
    }

    /**
     * Reads the file and calculates the total value by processing each line in file.
     * @return Total value.
     */
    private static int startGame() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/input.txt"))))){
            return br.lines().mapToInt(Main::getValueFromRound).sum();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Processes the game round.
     * @param line The game round.
     * @return The ID if it's a valid game.
     */
    private static int getValueFromRound(String line){
        maxRed = 0;
        maxGreen = 0;
        maxBlue = 0;
        String[] parts = line.split(":", 2);
        if (parts.length != 2) return 0;

        String gameName = parts[0].trim();
        String round = parts[1];

        //if (!checkIfValidRound(round)) return 0;
        //else return getPowerSet(round);
        return getPowerSet(round);

        // Part 2;
        // return the power set and reset maxRed, maxGreen, maxBlue

        //return Integer.parseInt(gameName.replaceAll("[^0-9]", ""));
    }

    private static int getPowerSet(String round){

        // split the round using;
        // split the
        // power set = maxRed * maxBlue * maxGreen
        // Loop through the round
        // get the value for each color
        String[] hej = round.split(";");
        for (String s : hej){
            s = s.trim();
            String[] baj = s.split(",");
            for (String b : baj){
                b = b.trim();
                splitValueAndColor(b);
                //System.out.println(b);
            }
        }

        int powerset = maxBlue * maxRed * maxGreen;

        System.out.println("Maxred: " + maxRed);


        /*Arrays.stream(round.split(";")) // here, the ";" is cut out
                .map(String::trim)
                .flatMap(data -> Arrays.stream(data.split(","))
                        .map(String::trim)
                        .map(Main::splitValueAndColor));*/



        //hej1.forEach(sequence -> sequence.split(","));


        //colVal.forEach(System.out::println);
        //System.out.println(colVal);
        //hej1.forEach(Main::splitValueAndColor);

        //System.out.println("Max red: " + maxRed);




        return powerset;
    }

    /**
     * Check if game-round is valid.
     * @return ID of the game if valid.
     * @param data data
     */
    /*private static int getPowerSetForRound(String data) {

        return Arrays.stream(data.split(";"))
                .map(String::trim)
                .allMatch(round -> Arrays.stream(round.split(","))
                        .map(String::trim)
                        .allMatch(Main::checkIfValidColorAndValue));
    }*/

    /**
     * Splits the string to color and value. Get's the value.
     * @param str String containing amount of cubes and color.
     */
    private static void splitValueAndColor(String str) {
        String[] parts = str.split("\\s+");
        try {
            int val = Integer.parseInt(parts[0]);
            String color = parts[1];
            //System.out.println("Val: " + val + ", color: " + color);
            getValueForColor(color, val);
        } catch (NumberFormatException ignored) {
        }
    }

    private static void getValueForColor(String color, int value){
        switch (color) {
            case "red" -> {
                maxRed = max(value, maxRed);
            }
            // maxRed = max(value, maxRed)
            case "blue" -> {
                maxBlue = max(value, maxBlue);
            }
            // maxBlue = max(value, maxBlue)
            case "green" -> {
                maxGreen = max(value, maxGreen);
            }
            default -> {
            }
        }
    }

    // PART 1

    /**
     * Check if game-round is valid.
     * @return ID of the game if valid.
     * @param data data
     */
    private static boolean checkIfValidRound(String data) {
        return Arrays.stream(data.split(";"))
                .map(String::trim)
                .allMatch(round -> Arrays.stream(round.split(","))
                        .map(String::trim)
                        .allMatch(Main::checkIfValidColorAndValue));
    }

    /**
     * Checks if the color and value are valid.
     * @param str String containing amount of cubes and color.
     * @return Whether it is valid or not.
     */
    private static boolean checkIfValidColorAndValue(String str) {
        String[] parts = str.split("\\s+");
        if (parts.length != 2) return false;

        try {
            int val = Integer.parseInt(parts[0]);
            String color = parts[1];
            return checkIfValid(color, val);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifies if the round is valid by checking amount of cubes and the color.
     * @param color The color.
     * @param val Amount of cubes.
     * @return Whether it is valid or not.
     */
    private static boolean checkIfValid(String color, int val){
        return switch (color) {
            case "red" -> val <= 12;
            case "green" -> val <= 13;
            case "blue" -> val <= 14;
            default -> false;
        };
    }
}
