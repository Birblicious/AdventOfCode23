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
public class GamesPartTwo implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CubeBag cubeBag;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Parse the games and extract the information required for each game
        cubeBag.fillTheGameMap(cubeBag.getGames(), "\n", ";");
        int sumOfPowerSet = findTheMinCubes(cubeBag.getGameMap());
        System.out.println("Teh powa set iz: " + sumOfPowerSet);
    }

    private int findTheMinCubes(Map<Integer, List<Games>> games) {
        int sumOfPowerSet = 0;
        for (Map.Entry<Integer, List<Games>> game: games.entrySet()) {
            int minGreen = 0;
            int minBlue = 0;
            int minRed = 0;

            for (Games currentGame: game.getValue()) {
                if(minGreen <= currentGame.getGreen())
                    minGreen = currentGame.getGreen();
                if(minBlue <= currentGame.getBlue())
                    minBlue = currentGame.getBlue();
                if(minRed <= currentGame.getRed())
                    minRed = currentGame.getRed();
            }
            sumOfPowerSet += minGreen * minBlue * minRed;
        }

        // 71220
        return sumOfPowerSet;
    }
}
