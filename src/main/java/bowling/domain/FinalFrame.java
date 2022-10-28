package bowling.domain;

public class FinalFrame extends Frame {
    private boolean bonus;
    private Scores bonusScores;

    FinalFrame() {
        this.bonus = false;
        this.bonusScores = new Scores();
    }

    public Scores getScores() {
        return scores;
    }

    public boolean isEndFrame() {
        if (isBonus()) {
            return false;
        }
        return super.isEndFrame();
    }

    void record(int downPinCount) {
        if (isBonus()) {
            bonus = true;
            bonusScores.record(downPinCount);
            return;
        }

        scores.record(downPinCount);
    }

    public boolean isBonus() {
        return !bonus && (scores.isStrike() || scores.isSpare());
    }

    public boolean validBonusScore() {
        return scores.getTryCount() != 0;
    }

    public Scores getBonusScores() {
        return bonusScores;
    }
}
