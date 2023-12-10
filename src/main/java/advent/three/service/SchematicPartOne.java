package advent.three.service;

import advent.three.input.IndexDetails;
import advent.three.input.Schematic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

@Service
public class SchematicPartOne implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Schematic schematic;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        schematic.saveToIndexMap(schematic.getInput());
        checkIfNestToSymbol();
    }


    private void checkIfNestToSymbol() {
        int total = 0;
        for (Map.Entry<Integer, List<IndexDetails>> numMap: schematic.getNumericMapPerLine().entrySet()) {
            int lineNumberNum = numMap.getKey();
            for (IndexDetails nDetails: numMap.getValue()) {
                for (Map.Entry<Integer, List<IndexDetails>> symMap: schematic.getSymbolicMapPerLine().entrySet()) {
                    int lineNumberSym = symMap.getKey();
                    for (IndexDetails sDetails: symMap.getValue()) {
                        if(nDetails.isTagged())
                            break;
                        if(Math.abs(lineNumberSym - lineNumberNum) > 1)
                            break;

                        ValueRange range = java.time.temporal.ValueRange.of(nDetails.getBeginIndex() - 1, nDetails.getEndIndex() + 1);

                        if(range.isValidIntValue(sDetails.getEndIndex())) {
                            System.out.println(nDetails.getAlphaNumeric() + "---" + lineNumberNum + "---" + lineNumberSym + "---" + sDetails.getAlphaNumeric() + "---" + (nDetails.getBeginIndex() - 1) + "---" + (nDetails.getEndIndex() + 1) + "---" + sDetails.getEndIndex());
                            nDetails.setTagged(true);
                            total += Integer.parseInt(nDetails.getAlphaNumeric());
                        }
                    }
                }
            }
        }
        System.out.println(total);
    }

}
