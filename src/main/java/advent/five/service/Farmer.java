package advent.five.service;

import advent.five.input.Bonanza;
import advent.five.input.Mapperino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Farmer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Mapperino mapperino;

    private List<Long> seedList = new ArrayList<>();

    List<Bonanza> goingBananas = new ArrayList<>();
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        String[] seeds = mapperino.getSeedString().split(" ");
        for (String seed: seeds) {
            seedList.add(Long.parseLong(seed));
        }

        long bonanzaStart = 3960442213L;
        long bonanzaEnd = 4066309213L;

        for(int i = 0; i < 2; i = i + 2) {
            //long bonanzaStart = seedList.get(i);
            //long bonanzaEnd = bonanzaStart + seedList.get(i + 1) - 1;
            Bonanza bonanza = new Bonanza(bonanzaStart, bonanzaEnd);
            goingBananas.add(bonanza);
            //bonanzaStart = bonanzaStart + 600000000L;
            //bonanzaEnd = bonanzaEnd + 600000000L;
        }

        for (Bonanza b: goingBananas) {
            b.start();
        }

    }
}
