package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */

public class GameTest {
    Game game;
    @Before
    public void setUp() throws OutOfRangeAnswerException {
        AnswerGenerator mockGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(mockGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game = new Game(mockGenerator);
    }

    @Test
    public void should_generate_correct_guess_result_for_given_guess_answer() {
        GuessResult guessResult = game.guess(Answer.createAnswer("1 3 5 6"));
        Assert.assertEquals("1A1B", guessResult.getResult());
    }

    @Test
    public void should_be_success_if_guess_answer_is_correct(){
        game.guess(Answer.createAnswer("1 2 3 4"));
        String status = game.checkStatus();
        Assert.assertEquals("success", status);
    }

    @Test
    public void should_allow_continue_if_guess_number_less_than_6(){
        game.guess(Answer.createAnswer("1 4 3 5"));
        String status = game.checkStatus();
        Assert.assertEquals("continue", status);
    }

    @Test
    public void should_be_failed_if_guess_number_exceed_allowed_limit(){
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("1 4 3 5"));
        String status = game.checkStatus();
        Assert.assertEquals("fail", status);
    }


}
