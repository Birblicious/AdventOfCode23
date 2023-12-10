package advent.three.input;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class IndexDetails {

    private int beginIndex = 0;
    private int endIndex = 0;
    private String alphaNumeric = "";
    private int type = -3;
    private boolean isTagged = false;
    private List<Integer> cogs = new ArrayList<>();

}
