package advent.one.service;

import advent.one.input.CalibrationBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class CalibratorPartOne implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CalibrationBook calibrationBook;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       // calibrateDocument();
    }

    private int calibrateDocument() {
        int calibrationNumber = 0;
        String[] calibrationLines = CalibrationBook.splitBy(calibrationBook.getCalibrationText(), "\n");

        for (String calibrationLine: calibrationLines) {
            calibrationNumber += CalibrationBook.findCalibrationNumber(calibrationLine);
        }

        // 54634
        System.out.println("Calibration Number is: " + calibrationNumber);

        return calibrationNumber;
    }
}
