import java.io.*;
import java.util.*;

import static java.lang.Math.max;

/**
 * Day 2 of advent of code.
 * @author Ebba Nimér
 */
public class Main {

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
        // If part 2, reset the max-values.
        maxRed = 0;
        maxGreen = 0;
        maxBlue = 0;

        String[] parts = line.split(":", 2);
        if (parts.length != 2) return 0;

        String gameName = parts[0].trim();
        String round = parts[1];

        // Part 1; get the game ID if the game is possible
        //if (!checkIfValidRound(round)) return 0;
        //else return Integer.parseInt(gameName.replaceAll("[^0-9]", ""));

        // Part 2; get the power set of the game; max red, max blue, and max green.
        return getPowerSet(round);
    }

    /**
     * Calculates the power set of the round.
     * @param round The game-round.
     * @return The power set.
     */
    private static int getPowerSet(String round) {
        Arrays.stream(round.split(";"))
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(",")))
                .map(String::trim)
                .forEach(Main::splitValueAndColor);

        return maxBlue * maxRed * maxGreen;
    }

    /**
     * Part 2.
     * Splits the string to color and value. Gets the amount.
     * @param str String containing amount of cubes and color.
     */
    private static void splitValueAndColor(String str) {
        String[] parts = str.split("\\s+");
        try {
            int val = Integer.parseInt(parts[0]);
            String color = parts[1];
            getValueForColor(color, val);
        } catch (NumberFormatException ignored) {
        }
    }

    /**
     * Part 2
     * Gets the amount of cubes for corresponding color.
     * @param color The color of the cubes.
     * @param value The amount of cubes.
     */
    private static void getValueForColor(String color, int value){
        switch (color) {
            case "red" -> maxRed = max(value, maxRed);
            case "blue" -> maxBlue = max(value, maxBlue);
            case "green" -> maxGreen = max(value, maxGreen);
        }
    }

    // PART 1

    /**
     * Part 1
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
     * Part 1
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
     * Part 1
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
