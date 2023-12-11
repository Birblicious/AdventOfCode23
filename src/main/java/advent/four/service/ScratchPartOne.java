package advent.four.service;

import advent.four.input.ScratchCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ScratchPartOne implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScratchCard scratchCard;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String[] splitNumbers = scratchCard.splitCard(scratchCard.getInput());
        scratchCard.parseAndFillMaps(splitNumbers);
        scratchCard.calculatePoints();
    }
}
