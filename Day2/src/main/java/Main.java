import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        startGame();
    }

    /**
     * Reads file, and processes each line into the Map.
     */
    private static void startGame() {
        int totalVal = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/input.txt"))));){
            String line;
            while ((line = br.readLine()) != null) {
                totalVal = totalVal + calculateVals(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(totalVal);
    }

    /**
     * Processes each game-round.
     * @param line game-round.
     */
    private static int calculateVals(String line){
        String[] parts = line.split(":", 2);
        String gameName = null;
        String round;
        if (parts.length == 2) {
            gameName = parts[0].trim();
            round = parts[1]; // get the round
            if (!checkIfValidRound(gameName, round))
                return 0;
        }

        assert gameName != null;
        List<String> gameId = List.of(gameName.split(" "));
        return Integer.parseInt(gameId.get(1));
    }


    /**
     * Check if game-round is valid.
     * @return ID of the game if valid.
     * @param data
     */
    private static boolean checkIfValidRound(String game, String data){
        // Split the string into substrings within the , sign
        String[] sublist = data.split(";");

        for (String s : sublist){
            s = s.trim();
            // Split the string into a list, so it should be; 5 red
            List<String> list = List.of(s.split(","));

            for (String str : list){
                str = str.trim();
                List<String> colorVal = List.of(str.split(" "));
                int val = Integer.parseInt(colorVal.get(0));
                String color = colorVal.get(1);
                if (!checkIfValid(color, val)){
                    return false;
                }

            }
        }

        return true;
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
