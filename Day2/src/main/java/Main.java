import java.io.*;
import java.util.*;

public class Main {

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
        String[] parts = line.split(":", 2);
        if (parts.length != 2) return 0;

        String gameName = parts[0].trim();
        String round = parts[1];

        if (!checkIfValidRound(round)) return 0;
        return Integer.parseInt(gameName.replaceAll("[^0-9]", ""));
    }
    
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

    private static boolean checkIfValid(String color, int val){
        return switch (color) {
            case "red" -> val <= 12;
            case "green" -> val <= 13;
            case "blue" -> val <= 14;
            default -> false;
        };
    }
}
