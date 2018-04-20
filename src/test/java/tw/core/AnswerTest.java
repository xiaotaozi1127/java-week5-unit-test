package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import static org.junit.Assert.assertEquals;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void show_create_valid_answer_according_to_input_string(){
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        String tostring = actualAnswer.toString();
        assertEquals("1 2 3 4", tostring);
    }

    @Test
    public void show_throw_exception_if_input_distinct_number_less_than_4() throws OutOfRangeAnswerException {
        Answer answer = Answer.createAnswer("1 1 3 4");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void show_throw_exception_if_input_contains_number_larger_than_9() throws OutOfRangeAnswerException {
        Answer answer = Answer.createAnswer("1 10 3 4");
        exception.expect(OutOfRangeAnswerException.class);
        exception.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void show_set_correct_number_count_and_include_only_number_count_when_answer_check(){
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("1 5 6 7");
        Record check = actualAnswer.check(inputAnswer);
        int correctNumber = check.getValue()[0];
        int includeOnly = check.getValue()[1];
        assertEquals(1, correctNumber);
        assertEquals(0, includeOnly);
    }

    @Test
    public void should_set_correct_number_as_4_for_given_correct_answer(){
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("1 2 3 4");
        Record check = actualAnswer.check(inputAnswer);
        int correctNumber = check.getValue()[0];
        int includeOnly = check.getValue()[1];
        assertEquals(4, correctNumber);
        assertEquals(0, includeOnly);
    }

    @Test
    public void should_set_include_only_number_as_4_if_given_answer_position_all_wrong(){
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("4 3 2 1");
        Record check = actualAnswer.check(inputAnswer);
        int correctNumber = check.getValue()[0];
        int includeOnly = check.getValue()[1];
        assertEquals(0, correctNumber);
        assertEquals(4, includeOnly);
    }

    @Test
    public void should_set_correct_number_and_include_only_number_as_zero_if_all_wrong(){
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");
        Record check = actualAnswer.check(inputAnswer);
        int correctNumber = check.getValue()[0];
        int includeOnly = check.getValue()[1];
        assertEquals(0, correctNumber);
        assertEquals(0, includeOnly);
    }

}