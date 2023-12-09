package advent.two.input;


import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

import static advent.two.input.Games.*;

@Component
public class CubeBag {

    @Getter
    private Map<Integer, List<Games>> gameMap = new TreeMap<>();

    public void fillTheGameMap(String games, String lineDelimeter, String gameDelimeter) {
        // Split by newLine to acquire each game
        String[] gameLines = games.split(lineDelimeter);
        int index = 1;
        for (String gameLine: gameLines) {
            // Remove the string from start to ":" since it is unused
            int indexToColumn = gameLine.indexOf(": ") + 2;
            gameLine = gameLine.substring(indexToColumn, gameLine.length());
            // Split each combination in a game
            String[] gamesOfThisLine = gameLine.split(gameDelimeter);
            List<Games> parsedGames = new ArrayList<>();
            // Get the cubes from each combination, sort and save cube numbers as games
            for (String game: gamesOfThisLine) {
                String[] cubes = game.split(",");

                Games newGame = new Games();
                for (String cube: cubes) {
                    sortToColor(cube, newGame);
                }
                parsedGames.add(newGame);
            }
            // Game ID and the List of each cube required for each combination.
            gameMap.putIfAbsent(index, parsedGames);
            index++;
        }
    }

    private void sortToColor(String cube, Games thisGame) {
        if(cube.contains(GREEN)) {
            thisGame.setGreen(extractCubeFromString(cube));
        }
        else if(cube.contains(BLUE)) {
            thisGame.setBlue(extractCubeFromString(cube));
        }
        else if(cube.contains(RED)) {
            thisGame.setRed(extractCubeFromString(cube));
        }
    }

    private int extractCubeFromString(String cube) {
        return Integer.parseInt(cube.replaceAll("[^0-9]", ""));
    }

    @Getter
    private String sampleTwo =
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

    @Getter
    private String games =
            "Game 1: 1 green, 1 blue, 1 red; 3 green, 1 blue, 1 red; 4 green, 3 blue, 1 red; 4 green, 2 blue, 1 red; 3 blue, 3 green\n" +
            "Game 2: 9 blue, 7 red; 5 blue, 6 green, 1 red; 2 blue, 10 red, 9 green; 3 green, 14 red, 5 blue; 8 red, 3 blue, 4 green; 8 green, 14 blue, 10 red\n" +
            "Game 3: 11 green, 8 blue, 7 red; 3 green, 4 red, 9 blue; 3 red, 4 green, 8 blue; 11 green, 1 red, 16 blue\n" +
            "Game 4: 3 blue, 20 green, 2 red; 1 green, 3 red, 3 blue; 1 blue, 9 green; 4 red, 17 green; 12 green, 3 red\n" +
            "Game 5: 2 red, 1 blue, 4 green; 6 blue, 2 green; 2 red, 5 green\n" +
            "Game 6: 1 green, 7 red; 1 blue, 3 green, 1 red; 1 blue, 2 red, 2 green; 1 blue, 1 green, 2 red; 3 red; 8 red, 1 green, 1 blue\n" +
            "Game 7: 13 green, 5 blue, 1 red; 9 green, 6 blue; 11 green, 2 blue, 2 red\n" +
            "Game 8: 2 red, 11 green, 6 blue; 5 green, 3 blue; 3 blue, 3 green, 5 red; 5 blue, 9 green, 1 red\n" +
            "Game 9: 3 blue, 5 green, 8 red; 4 green, 19 blue, 4 red; 4 red, 17 blue\n" +
            "Game 10: 2 green, 8 red, 4 blue; 1 green, 5 red, 9 blue; 3 green, 2 red, 4 blue; 2 green, 5 red, 2 blue; 6 green, 4 blue; 10 blue, 8 green, 8 red\n" +
            "Game 11: 3 green, 1 blue, 9 red; 2 blue, 1 red, 9 green; 1 blue, 9 green, 7 red; 8 red, 6 green\n" +
            "Game 12: 5 green; 8 green, 7 red, 1 blue; 8 blue, 8 green, 14 red; 6 red, 14 green; 14 green, 3 red, 8 blue\n" +
            "Game 13: 7 red, 2 green, 4 blue; 5 red, 3 blue, 8 green; 10 green, 1 red, 4 blue; 7 green, 1 red, 13 blue; 11 green, 12 blue, 2 red\n" +
            "Game 14: 9 green, 4 red; 7 green, 4 blue, 5 red; 2 red, 2 green; 3 green, 2 red, 8 blue; 7 green, 6 red, 8 blue\n" +
            "Game 15: 19 blue, 1 green; 1 red, 5 blue; 3 green, 8 blue; 1 red, 13 blue, 3 green\n" +
            "Game 16: 8 red, 11 blue, 3 green; 14 green, 1 red, 12 blue; 6 green, 1 red, 6 blue; 1 red, 6 blue, 17 green; 2 green, 8 blue, 3 red\n" +
            "Game 17: 3 red, 1 blue; 1 blue, 2 red, 1 green; 1 red; 3 red, 2 green\n" +
            "Game 18: 8 green, 2 red; 1 blue, 6 red; 8 green, 7 red, 2 blue; 1 red; 6 green, 1 blue, 3 red\n" +
            "Game 19: 4 blue, 2 green; 4 green, 18 blue, 2 red; 14 green, 4 blue; 1 red, 3 blue, 18 green; 11 blue, 3 red; 14 green, 4 red, 6 blue\n" +
            "Game 20: 7 blue; 1 blue, 6 green, 1 red; 1 red, 3 blue, 10 green; 7 green, 4 blue, 1 red; 6 green, 6 blue, 1 red; 1 red, 5 blue, 17 green\n" +
            "Game 21: 11 blue, 9 red; 8 red, 2 blue; 2 red, 11 blue, 2 green\n" +
            "Game 22: 4 green, 9 blue, 2 red; 2 blue, 8 green; 2 green, 2 red, 6 blue\n" +
            "Game 23: 7 green, 7 blue; 6 blue, 11 green; 1 red, 14 green; 15 green, 4 blue, 1 red; 1 red, 5 blue, 3 green; 1 red, 1 blue, 13 green\n" +
            "Game 24: 5 green, 2 red, 2 blue; 1 blue, 3 green, 2 red; 2 blue, 7 green, 3 red\n" +
            "Game 25: 16 red, 8 green; 2 red, 3 blue; 10 green, 5 red, 4 blue; 9 red, 7 green; 7 red, 6 blue\n" +
            "Game 26: 3 red, 1 green; 5 red, 1 blue, 10 green; 8 red, 5 green\n" +
            "Game 27: 3 red, 2 blue; 6 red, 8 green; 5 green, 13 red, 2 blue; 4 red, 1 blue, 8 green; 14 red, 1 blue, 3 green; 2 red, 1 green, 2 blue\n" +
            "Game 28: 9 red, 10 blue; 9 red, 9 blue; 1 green, 6 red, 4 blue; 12 blue, 3 green, 2 red; 2 green, 12 red, 8 blue\n" +
            "Game 29: 4 red, 15 blue; 5 blue, 3 green, 6 red; 1 green, 9 blue, 1 red; 5 green, 8 red, 1 blue\n" +
            "Game 30: 4 green, 2 blue, 10 red; 1 red, 5 green, 6 blue; 15 red, 2 blue; 5 green, 10 red, 8 blue\n" +
            "Game 31: 6 green, 2 blue, 2 red; 5 green, 6 red, 6 blue; 3 blue, 5 red, 1 green; 4 green, 6 red, 9 blue; 4 red; 3 green, 1 red, 3 blue\n" +
            "Game 32: 8 green, 17 red, 17 blue; 11 red, 6 green, 13 blue; 14 red, 1 green, 1 blue; 1 green, 17 red, 4 blue; 5 green, 14 red, 15 blue; 15 blue, 8 green\n" +
            "Game 33: 2 red, 14 blue; 3 blue, 17 red, 4 green; 9 blue, 8 red; 5 red, 2 blue; 4 green, 16 red, 5 blue; 15 blue, 6 green, 17 red\n" +
            "Game 34: 12 green, 2 red, 1 blue; 3 blue, 9 red, 13 green; 2 blue, 17 green, 6 red; 6 green, 4 red, 3 blue; 2 red, 1 blue; 7 green, 3 blue, 7 red\n" +
            "Game 35: 4 blue, 5 red, 5 green; 6 green, 12 red, 6 blue; 3 green, 18 red, 2 blue; 13 red, 6 green, 9 blue; 3 green, 10 blue, 17 red; 1 green, 3 blue, 16 red\n" +
            "Game 36: 4 green, 3 blue, 1 red; 3 green, 3 red, 10 blue; 1 red, 8 green, 8 blue; 3 blue, 1 red; 2 red, 2 blue, 14 green\n" +
            "Game 37: 16 blue, 1 green; 9 blue; 7 red, 13 blue\n" +
            "Game 38: 6 green, 8 red, 3 blue; 5 blue, 4 green, 6 red; 5 blue, 4 green; 5 red, 3 green, 1 blue; 6 green, 4 blue, 15 red\n" +
            "Game 39: 10 blue, 4 green; 1 blue, 7 green, 5 red; 8 red, 2 blue\n" +
            "Game 40: 2 blue, 2 green, 6 red; 8 green, 4 red, 2 blue; 4 blue, 12 green, 6 red\n" +
            "Game 41: 4 green, 2 blue, 11 red; 6 red, 11 green; 4 blue, 2 red, 19 green; 3 green, 2 blue, 1 red\n" +
            "Game 42: 2 blue, 2 green, 4 red; 1 red, 4 blue, 8 green; 5 red, 2 blue, 15 green; 10 green, 5 red, 1 blue; 10 green, 1 blue; 2 blue\n" +
            "Game 43: 10 red, 19 green, 11 blue; 11 green, 1 red, 2 blue; 13 red, 6 blue, 3 green; 12 red, 10 green; 5 red, 19 green, 8 blue; 2 red, 10 green, 3 blue\n" +
            "Game 44: 7 blue, 8 red; 1 green, 12 red; 19 red, 3 green, 5 blue\n" +
            "Game 45: 12 red; 2 green, 5 red, 3 blue; 10 green, 2 blue, 4 red; 10 green, 7 red\n" +
            "Game 46: 4 blue, 4 red, 2 green; 7 green, 6 blue; 6 blue, 1 red, 4 green\n" +
            "Game 47: 4 green, 8 red, 1 blue; 4 green, 1 blue, 11 red; 14 red, 3 blue, 10 green; 15 green, 2 blue, 7 red\n" +
            "Game 48: 6 blue, 3 green, 1 red; 15 blue, 11 red, 3 green; 17 blue, 14 red; 2 green, 13 red; 5 red, 2 green, 4 blue\n" +
            "Game 49: 7 blue, 1 green; 8 red, 2 blue, 1 green; 1 red, 1 green, 2 blue; 3 red, 2 blue, 1 green\n" +
            "Game 50: 13 red, 2 green, 2 blue; 13 red, 6 green, 1 blue; 12 red, 8 green; 1 red, 3 green; 13 red; 2 blue, 11 red, 2 green\n" +
            "Game 51: 7 green, 4 blue, 2 red; 3 red, 7 green, 5 blue; 10 green, 2 blue; 14 green, 3 red, 4 blue; 2 blue, 2 red, 10 green\n" +
            "Game 52: 7 blue, 10 red; 7 red, 4 blue, 8 green; 12 red, 4 blue, 7 green; 7 green, 7 red; 17 green, 11 blue, 6 red; 8 green, 8 red, 18 blue\n" +
            "Game 53: 6 green, 2 red; 10 red, 13 green; 2 blue, 3 green, 5 red; 4 red, 4 green; 8 green, 1 red; 13 green, 2 blue, 10 red\n" +
            "Game 54: 5 red, 9 green, 5 blue; 6 red, 15 green, 10 blue; 8 blue, 3 green, 1 red; 12 blue, 3 red, 13 green\n" +
            "Game 55: 10 green, 4 red, 2 blue; 2 green, 1 red, 2 blue; 2 blue, 4 red, 8 green; 5 blue, 3 green\n" +
            "Game 56: 7 green, 9 red, 2 blue; 4 red, 1 blue, 3 green; 3 red, 4 blue, 6 green; 7 green, 2 red; 5 blue, 2 red, 3 green; 7 green, 7 red, 5 blue\n" +
            "Game 57: 11 blue, 5 green, 6 red; 18 red, 15 green, 10 blue; 5 green, 14 red, 6 blue; 1 green, 11 red, 7 blue; 11 blue, 7 red, 12 green\n" +
            "Game 58: 9 red, 6 blue, 6 green; 6 blue, 12 red, 3 green; 8 red, 1 blue, 20 green; 3 green, 3 red, 15 blue; 4 blue, 15 green, 3 red\n" +
            "Game 59: 18 red, 4 blue, 7 green; 11 blue, 19 red, 7 green; 10 red, 9 blue, 1 green; 8 red, 12 green, 4 blue; 6 green, 5 blue, 12 red; 2 blue, 14 green, 2 red\n" +
            "Game 60: 1 blue, 9 green, 6 red; 1 red, 1 blue, 13 green; 15 green, 4 red; 1 blue, 2 red, 15 green\n" +
            "Game 61: 9 green, 3 red, 2 blue; 1 green, 5 blue, 10 red; 17 red, 9 green, 5 blue; 10 red, 5 green, 5 blue\n" +
            "Game 62: 4 red, 2 green; 2 red; 5 red, 2 green, 2 blue; 3 green, 1 blue; 2 blue, 3 red, 3 green\n" +
            "Game 63: 4 red, 6 blue, 2 green; 3 green, 1 red, 5 blue; 7 blue, 5 green\n" +
            "Game 64: 8 blue, 12 red; 1 green, 6 red, 14 blue; 12 red, 13 blue\n" +
            "Game 65: 2 red, 8 green; 1 blue, 2 red, 5 green; 2 blue, 3 green; 1 green, 1 red\n" +
            "Game 66: 6 red, 8 blue, 2 green; 6 blue, 7 green; 7 green, 11 blue; 5 green, 4 red, 10 blue; 5 blue, 4 green; 6 blue, 6 green, 5 red\n" +
            "Game 67: 12 green, 4 red; 2 blue, 11 green, 6 red; 9 red, 2 green, 6 blue; 2 red, 8 blue, 18 green; 17 green, 7 blue, 6 red; 12 blue, 9 green\n" +
            "Game 68: 3 red, 9 blue, 1 green; 3 green, 11 blue; 12 blue, 9 red; 6 red, 13 green, 8 blue\n" +
            "Game 69: 3 red, 8 green, 3 blue; 6 green, 3 red; 11 green, 3 blue; 4 red, 3 green; 7 green, 4 blue, 6 red; 1 red, 2 blue\n" +
            "Game 70: 6 green, 4 blue; 7 red, 9 green, 14 blue; 12 red; 9 green, 10 red; 6 green, 16 blue, 8 red\n" +
            "Game 71: 4 blue, 6 red, 9 green; 6 green, 2 red; 8 green, 4 blue, 2 red; 1 red, 3 blue, 8 green; 9 green, 3 red, 3 blue; 4 red, 6 green\n" +
            "Game 72: 3 red, 3 green, 3 blue; 4 red, 1 green, 3 blue; 2 red, 2 green, 1 blue\n" +
            "Game 73: 7 green, 6 red, 7 blue; 2 green, 4 blue; 2 blue, 15 green, 4 red; 1 blue, 4 green, 2 red; 7 blue, 14 green\n" +
            "Game 74: 4 green, 2 red, 2 blue; 9 blue, 13 green, 4 red; 10 green, 12 blue, 7 red; 4 blue, 8 green, 7 red\n" +
            "Game 75: 3 red, 3 green; 3 green, 12 red; 18 red, 2 blue; 3 green, 9 red, 1 blue; 14 red, 1 green; 15 red\n" +
            "Game 76: 1 blue, 7 red, 8 green; 3 blue, 4 red, 1 green; 6 green, 6 red\n" +
            "Game 77: 2 green, 8 red; 5 green, 11 red, 1 blue; 5 red, 2 blue, 2 green; 6 red, 5 green, 2 blue; 11 red, 2 green, 1 blue; 2 red, 8 green, 2 blue\n" +
            "Game 78: 1 blue, 4 red, 10 green; 13 green, 4 red, 9 blue; 12 green, 6 blue, 3 red; 5 blue, 8 green, 6 red\n" +
            "Game 79: 9 red, 1 blue, 17 green; 9 green, 6 red; 15 green, 1 blue, 9 red; 1 blue, 8 red, 12 green; 11 green, 1 blue, 1 red\n" +
            "Game 80: 3 red, 3 blue, 1 green; 1 blue, 8 green; 10 green, 16 blue\n" +
            "Game 81: 15 blue, 2 red; 1 red, 8 blue; 2 green, 7 red, 11 blue; 19 blue, 8 red, 2 green; 20 red, 19 blue\n" +
            "Game 82: 3 red, 17 blue, 9 green; 10 red, 2 green, 17 blue; 13 red, 3 blue, 10 green; 11 red, 10 green, 18 blue; 1 green, 12 blue, 9 red; 3 red, 10 blue, 8 green\n" +
            "Game 83: 4 green, 2 blue, 14 red; 7 red, 2 blue, 7 green; 16 red, 7 green; 16 red, 2 green; 3 blue, 4 green, 15 red; 6 red, 1 blue, 4 green\n" +
            "Game 84: 4 blue, 1 green, 2 red; 7 blue, 6 red; 1 blue, 4 red, 3 green\n" +
            "Game 85: 5 blue, 1 red, 4 green; 14 blue, 7 green; 9 blue, 1 red, 7 green; 15 blue, 9 green; 8 blue, 6 green, 1 red\n" +
            "Game 86: 12 red, 12 blue, 7 green; 16 red, 11 green, 4 blue; 14 blue, 8 green, 8 red; 2 blue, 15 red, 6 green; 1 green, 6 red, 5 blue; 11 blue, 9 green\n" +
            "Game 87: 4 blue, 2 green, 6 red; 8 red, 3 green, 5 blue; 10 red, 1 green; 1 green, 3 blue, 1 red\n" +
            "Game 88: 2 green, 2 red, 4 blue; 4 blue, 4 green, 12 red; 2 green, 3 blue, 4 red; 2 green, 2 blue, 12 red; 4 blue, 8 red, 2 green\n" +
            "Game 89: 13 blue, 1 red, 5 green; 8 green, 16 blue, 5 red; 12 green, 2 red, 18 blue\n" +
            "Game 90: 7 red, 11 blue, 1 green; 8 green, 13 blue; 7 green, 16 blue; 5 green, 13 red, 11 blue; 5 blue, 10 green, 3 red\n" +
            "Game 91: 1 blue; 1 blue, 3 green; 1 green, 2 red\n" +
            "Game 92: 16 red, 4 blue, 14 green; 15 red, 7 blue, 13 green; 7 green, 13 red, 8 blue; 4 blue, 9 red, 5 green; 6 red, 7 blue, 8 green; 14 green, 7 red, 10 blue\n" +
            "Game 93: 2 red, 6 blue, 7 green; 8 green, 3 red, 10 blue; 1 green, 4 red; 2 red, 2 green; 8 blue, 7 green\n" +
            "Game 94: 2 green, 3 blue, 5 red; 10 blue; 1 green, 7 red; 3 blue, 1 green, 12 red\n" +
            "Game 95: 13 blue, 5 red; 9 blue, 3 red, 7 green; 10 green, 4 red, 12 blue; 14 blue; 7 green, 2 blue, 1 red\n" +
            "Game 96: 5 red, 2 blue; 4 red; 1 green, 2 blue\n" +
            "Game 97: 9 red, 10 green, 3 blue; 2 green, 15 red, 1 blue; 2 blue, 16 green, 16 red; 8 green, 14 red; 16 red\n" +
            "Game 98: 18 green, 16 red, 1 blue; 3 red, 2 blue, 20 green; 1 blue, 20 green, 14 red; 14 red, 2 green\n" +
            "Game 99: 7 red, 9 green, 5 blue; 6 blue, 1 green; 4 green, 5 blue, 1 red; 8 green, 6 red, 7 blue; 1 blue, 2 red, 9 green\n" +
            "Game 100: 10 blue, 2 red; 7 green, 20 blue, 9 red; 8 red, 6 green, 2 blue";

}
