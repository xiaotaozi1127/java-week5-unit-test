package tw.controllers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void should_display_hint_message_when_game_begins() throws OutOfRangeAnswerException, IOException {
        GameView gameView = Mockito.mock(GameView.class);
        Game game = Mockito.mock(Game.class);
        GameController controller = new GameController(game, gameView);
        controller.beginGame();
        Mockito.verify(gameView).showBegin();
    }

    @Test
    public void should_give_guess_result_when_play_game() throws OutOfRangeAnswerException, IOException {
        GameView gameView = Mockito.mock(GameView.class);
        Game game = Mockito.mock(Game.class);
        GuessInputCommand inputCommand = Mockito.mock(GuessInputCommand.class);
        Answer answer = Answer.createAnswer("1 2 3 4");
        Mockito.when(inputCommand.input()).thenReturn(answer);
        Mockito.when(game.checkContinue()).thenReturn(true).thenReturn(false);
        GuessResult guessResult = new GuessResult("1A2B", answer);
        Mockito.when(game.guess(answer)).thenReturn(guessResult);
        GameController controller = new GameController(game, gameView);
        controller.beginGame();
        controller.play(inputCommand);
        Mockito.verify(game, Mockito.times(2)).checkContinue();
        Mockito.verify(game).guess(answer);
        Mockito.verify(gameView).showGuessResult(guessResult);
    }

    @AfterClass
    public static void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

}