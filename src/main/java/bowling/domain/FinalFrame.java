package bowling.domain;

public class FinalFrame extends Frame {
    private boolean bonus;
    private Scores bonusScores = new Scores();

    FinalFrame() {
        this.bonus = false;
    }

    public boolean isEndFrame() {
        if (isBonus()) {
            return false;
        }
        return super.isEndFrame();
    }

    public void record(int downPinCount) {
        if (isBonus()) {
            bonus = true;
            bonusScores.record(downPinCount);
            return;
        }

        scores.record(downPinCount);
    }

    public boolean validBonusScore() {
        return bonusScores.getTryCount() != 0;
    }

    public Scores getBonusScores() {
        return bonusScores;
    }

    public int getPinScore() {
        return scores.sumOfDownPins() + bonusScores.sumOfDownPins();
    }

    private boolean isBonus() {
        return !bonus && (scores.isStrike() || scores.isSpare());
    }

}
