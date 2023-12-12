package advent.five.service;

import advent.five.input.Mapperino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class Farmer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Mapperino mapperino;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        mapperino.setSeedToSoilMap();
    }
}
