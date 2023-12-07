package advent.one.service;

import advent.one.input.CalibrationDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class Calibrator implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CalibrationDocument calibrationDocument;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        calibrateDocument();
    }

    private int calibrateDocument() {
        int calibrationNumber = 0;
        String[] calibrationLines = splitBy("\n");

        for (String calibrationLine: calibrationLines) {
            calibrationNumber += findCalibrationNumber(calibrationLine);
        }

        System.out.println("Calibration Number is: " + calibrationNumber);

        return calibrationNumber;
    }

    private String[] splitBy(String delimeter) {
        return calibrationDocument.getCalibrationText().split(delimeter);
    }

    private int findCalibrationNumber(String calibrationLine) {
        // Remove all non numerics
        String numerics = calibrationLine.replaceAll("[^0-9]", "");
        // Get first numeric
        String firstNum =numerics.substring(0, 1);
        // Get last numeric
        String lastNum = numerics.substring(numerics.length() - 1, numerics.length());
        // Return concatenated value as int
        return Integer.parseInt(firstNum.concat(lastNum));
    }
}
