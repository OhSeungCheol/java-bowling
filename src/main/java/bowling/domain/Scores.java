package bowling.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private static final int TRY_COUNT_TO_STRIKE = 1;
    private static final int TRY_COUNT_TO_SPARE = 2;

    private List<Integer> downPins = new ArrayList<>();

    public List<Integer> getDownPins() {
        return downPins;
    }

    public void record(int downPinCount) {
        checkDownPinCountValidation(downPinCount);
        downPins.add(downPinCount);
    }

    public int getTryCount() {
        return downPins.size();
    }

    public boolean isStrike() {
        return downPins.size() == TRY_COUNT_TO_STRIKE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean isSpare() {
        return downPins.size() == TRY_COUNT_TO_SPARE && sumOfDownPins() == RuleConfig.NUMBER_OF_PIN;
    }

    public boolean tryOver() {
        return downPins.size() >= RuleConfig.PITCH_COUNT;
    }

    public boolean isValid() {
        return downPins.size() != 0;
    }

    public int sumOfDownPins() {
        return downPins
                .stream()
                .reduce(0, Integer::sum);
    }

    public int getFirstScore() {
        if (CollectionUtils.isEmpty(downPins)) {
            return 0;
        }
        return downPins.get(0);
    }

    public int getSecondPitchScore() {
        if (downPins.size() < 2) {
            return 0;
        }
        return downPins.get(1);
    }

    private void checkDownPinCountValidation(int downPinCount) {
        if (sumOfDownPins() + downPinCount > RuleConfig.NUMBER_OF_PIN) {
            throw new RuntimeException("Sum of down pin count must not more than 10");
        }
    }
}
