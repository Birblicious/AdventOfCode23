package advent.one.service;

import advent.one.input.CalibrationBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CalibratorPartTwo implements ApplicationListener<ApplicationReadyEvent> {

    private Map<String, Integer> letterToNumeric = new HashMap<>();
    private Map<Integer, String> indexOfNumber = new TreeMap<>();

    @Autowired
    private CalibrationBook calibrationBook;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addTranslation();
        translateToNumeric();
    }

    private void translateToNumeric() {
        String[] calibrationLines = CalibrationBook.splitBy(calibrationBook.getCalibrationText(), "\n");
        int calibrationNumber = 0;

        for (String calibrationLine: calibrationLines) {
            // Find and sort by index the contained numbers as letters.
            for (Map.Entry<String, Integer> element: letterToNumeric.entrySet()) {
                if(calibrationLine.contains(element.getKey())) {
                    indexOfNumber.putIfAbsent(calibrationLine.indexOf(element.getKey()), element.getKey());
                }
            }

            // Convert from number to letter and the last char back due to not mentioned rule.
            for(Map.Entry<Integer, String> foundElement: indexOfNumber.entrySet()) {
                String toReplace = Integer.toString(letterToNumeric.get(foundElement.getValue()));
                toReplace = toReplace.concat(foundElement.getValue().substring(foundElement.getValue().length() - 1, foundElement.getValue().length()));
                calibrationLine = calibrationLine.replace(foundElement.getValue(), toReplace);
            }

            // Clear the indexes of the numbers for the next line
            indexOfNumber.clear();
            calibrationNumber += calibrationBook.findCalibrationNumber(calibrationLine);
        }

        // 53855
        System.out.println("Calibration Number Part Two is: " + calibrationNumber);
    }

    private void addTranslation() {
        letterToNumeric.putIfAbsent("one", 1);
        letterToNumeric.putIfAbsent("two", 2);
        letterToNumeric.putIfAbsent("three", 3);
        letterToNumeric.putIfAbsent("four", 4);
        letterToNumeric.putIfAbsent("five", 5);
        letterToNumeric.putIfAbsent("six", 6);
        letterToNumeric.putIfAbsent("seven", 7);
        letterToNumeric.putIfAbsent("eight", 8);
        letterToNumeric.putIfAbsent("nine", 9);
    }
}
