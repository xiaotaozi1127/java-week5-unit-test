package tw.controllers;

import org.junit.*;
import org.mockito.Mockito;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String systemOut() { return outContent.toString(); }
    private GameController controller;

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        System.setOut(new PrintStream(outContent));
        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        Mockito.when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        controller = new GameController(
                new Game(answerGenerator), new GameView());
    }

    @After
    public void restore(){
        System.setOut(System.out);
    }

    @Test
    public void should_display_hint_message_when_game_begins() throws OutOfRangeAnswerException, IOException {
        controller.beginGame();
        Assert.assertTrue(systemOut().startsWith("------Guess Number Game, You have 6 chances to guess!  ------"));
    }

    @Test
    public void should_end_game_at_first_if_play_game_success_at_first_try() throws OutOfRangeAnswerException, IOException {
        InputCommand inputCommand = Mockito.mock(InputCommand.class);
        Mockito.when(inputCommand.input()).thenReturn(Answer.createAnswer("1 2 3 4"));
        controller.play(inputCommand);
        String systemOut = systemOut();
        int occurrences = findSubStringOccurrences(systemOut, "Guess History:");
        Assert.assertEquals(1, occurrences);
        Assert.assertTrue(systemOut.endsWith("Guess Result: 4A0B\n"
                + "Guess History:\n"
                + "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n"
                + "Game Status: success\n"));
    }

    @Test
    public void should_end_game_in_advance_if_guess_result_less_than_6_times() throws IOException {
        InputCommand inputCommand = Mockito.mock(InputCommand.class);
        Mockito.when(inputCommand.input())
                .thenReturn(Answer.createAnswer("1 3 4 6"))
                .thenReturn(Answer.createAnswer("1 2 3 4"));
        controller.play(inputCommand);
        String systemOut = systemOut();
        int occurrences = findSubStringOccurrences(systemOut, "Guess History:");
        Assert.assertEquals(2, occurrences);
        Assert.assertTrue(systemOut.endsWith("Game Status: success\n"));
    }

    @Test
    public void should_try_6_times_if_every_guess_failed() throws IOException {
        InputCommand inputCommand = Mockito.mock(InputCommand.class);
        Mockito.when(inputCommand.input()).thenReturn(Answer.createAnswer("1 3 4 6"));
        controller.play(inputCommand);
        String systemOut = systemOut();
        int occurrences = findSubStringOccurrences(systemOut, "Guess History:");
        Assert.assertEquals(6, occurrences);
        Assert.assertTrue(systemOut.endsWith("Game Status: fail\n"));
    }

    private int findSubStringOccurrences(String systemOut, String subString) {
        Matcher m = Pattern.compile("(?=(" + subString + "))").matcher(systemOut);
        List<Integer> pos = new ArrayList<Integer>();
        while (m.find())
        {
            pos.add(m.start());
        }
        return pos.size();
    }
}