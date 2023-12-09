package advent.two.service;

import advent.two.input.CubeBag;
import advent.two.input.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GamesPartOne implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CubeBag cubeBag;

    private int redCubes = 12;
    private int greenCubes = 13;
    private int blueCubes = 14;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Parse the games and extract the information required for each game
        //cubeBag.fillTheGameMap(cubeBag.getGames(), "\n", ";");
        // Find if the game is possible to play with the given cubes
        //int possibleGameIDSummed = findGamesPossible(cubeBag.getGameMap());
        //System.out.println("Possible game IDs summed up is: " + possibleGameIDSummed);
    }

    private int findGamesPossible(Map<Integer, List<Games>> games) {
        int possibleGames = 0;

        for (Map.Entry<Integer, List<Games>> game: games.entrySet()) {
            boolean isPossible = true;
            for (Games currentGame: game.getValue()) {
                isPossible = isPossible && checkIfGamePossible(currentGame);
            }
            if(isPossible)
                possibleGames += game.getKey();
        }
        // 2377
        return possibleGames;
    }

    private boolean checkIfGamePossible(Games game) {
        boolean isPossible = true;
        isPossible = game.getGreen() <= this.greenCubes;
        isPossible = isPossible && game.getBlue() <= this.blueCubes;
        isPossible = isPossible && game.getRed() <= this.redCubes;
        return isPossible;
    }
}
