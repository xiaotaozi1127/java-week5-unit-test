package tw.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void should_display_hint_message_when_game_begins() throws OutOfRangeAnswerException, IOException {
        GameController controller = new GameController(
                new Game(new AnswerGenerator(new RandomIntGenerator())), new GameView());
        controller.beginGame();
        Assert.assertTrue(outContent.toString().contains("------Guess Number Game, You have 6 chances to guess!  ------"));
    }

    @Test
    public void should_give_guess_result_when_play_game() throws OutOfRangeAnswerException, IOException {
        AnswerGenerator mock = Mockito.mock(AnswerGenerator.class);
        GuessInputCommand command = Mockito.mock(GuessInputCommand.class);
        Mockito.when(mock.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        Mockito.when(command.input()).thenReturn(Answer.createAnswer("1 3 4 6"));
        GameController controller = new GameController(
                new Game(mock), new GameView());
        controller.beginGame();
        controller.play(command);
        Assert.assertTrue(outContent.toString().contains("1A2B"));
    }



    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

}