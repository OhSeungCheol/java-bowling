package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("마지막 프레임 종료 판단 함수 테스트 : 프레임이 끝나지 않은 경우")
    void isEndFrame1() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(5);
        assertThat(finalFrame.isEndFrame()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 종료 판단 함수 테스트 : MISS 로 끝난 경우")
    void isEndFrame2() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(5);
        finalFrame.record(4);
        assertThat(finalFrame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임 종료 판단 함수 테스트 : SPARE 이며 보너스 게임이 남은 경우")
    void isEndFrame3() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(5);
        finalFrame.record(5);
        assertThat(finalFrame.isEndFrame()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 종료 판단 함수 테스트 : STRIKE 이며 보너스 게임이 남은 경우")
    void isEndFrame4() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(10);
        assertThat(finalFrame.isEndFrame()).isFalse();
    }

    @Test
    @DisplayName("마지막 프레임 종료 판단 함수 테스트 : 보너스 게임이 끝난 경우")
    void isEndFrame5() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(10);
        finalFrame.record(5);
        assertThat(finalFrame.isEndFrame()).isTrue();
    }

    @Test
    @DisplayName("STRIKE 에 의한 보너스 투구의 점수가 제대로 저장되는지 테스트")
    void bonus_record_test1() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(10);

        List<Integer> bonusDownPins = finalFrame.getBonusScores().getDownPins();
        assertThat(bonusDownPins).hasSize(0);

        finalFrame.record(5);
        assertThat(bonusDownPins).hasSize(1);
    }

    @Test
    @DisplayName("SPARE 에 의한 보너스 투구의 점수가 제대로 저장되는지 테스트")
    void bonus_record_test2() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.record(5);
        finalFrame.record(5);

        List<Integer> bonusDownPins = finalFrame.getBonusScores().getDownPins();
        assertThat(bonusDownPins).hasSize(0);

        finalFrame.record(5);
        assertThat(bonusDownPins).hasSize(1);
    }

    @Test
    @DisplayName("마지막 프레임의 보너스 점수는 기록이 한개라도 있어야 유효함을 테스트")
    void validBonusScore() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame.validBonusScore()).isFalse();
        finalFrame.record(5);
        assertThat(finalFrame.validBonusScore()).isTrue();
    }

}