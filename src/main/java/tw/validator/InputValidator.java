package tw.validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class InputValidator {

    private static final int NUM_COUNT = 4;
    private static final String SPACE = " ";
    private static final int MAX_LIMIT = 10;

    public Boolean validate(String numStr) {
        List<String> numList = numStrToList(numStr);
        boolean digitCountValid = validateDigitsCount(numList);
        boolean singleDigitValid = validateSingleDigit(numList);
        return digitCountValid && singleDigitValid;
    }

    private boolean validateSingleDigit(List<String> numList) {
        return numList.stream()
                .map(num -> parseInt(num))
                .distinct()
                .filter(num -> num < MAX_LIMIT).count() == NUM_COUNT;
    }

    private boolean validateDigitsCount(List<String> numList) {
        return numList.size() == NUM_COUNT;
    }

    private List<String> numStrToList(String numStr) {
        return Arrays.stream(numStr.split(SPACE))
                .collect(Collectors.toList());
    }
}
