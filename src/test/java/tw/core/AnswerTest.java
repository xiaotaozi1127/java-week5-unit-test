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
    public void show_create_number_list_according_to_input_string(){
        Answer answer = Answer.createAnswer("1 2 3 4");
        int index = answer.getIndexOfNum("1");
        assertEquals(0, index);

        index = answer.getIndexOfNum("2");
        assertEquals(1, index);

        index = answer.getIndexOfNum("3");
        assertEquals(2, index);

        index = answer.getIndexOfNum("4");
        assertEquals(3, index);

        String tostring = answer.toString();
        assertEquals("1 2 3 4", tostring);
    }

    @Test
    public void show_throw_exception_if_input_contains_duplicate_numbers() throws OutOfRangeAnswerException {
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
    public void show_increase_correct_number_if_answer_contains_matched_number_and_position(){
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record check = answer.check(Answer.createAnswer("1 5 6 7"));
        int correctNumber = check.getValue()[0];
        assertEquals(1, correctNumber);
    }

    @Test
    public void should_increase_include_only_number_if_answer_matches_number_with_wrong_position(){
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record check = answer.check(Answer.createAnswer("5 6 7 1"));
        int includeOnly = check.getValue()[1];
        assertEquals(1, includeOnly);
    }

    @Test
    public void should_set_correct_number_and_include_only_number_for_given_answer(){
        Answer answer = Answer.createAnswer("1 2 3 4");
        Record check = answer.check(Answer.createAnswer("0 2 3 1"));
        int correctNumber = check.getValue()[0];
        int includeOnly = check.getValue()[1];
        assertEquals(2, correctNumber);
        assertEquals(1, includeOnly);
    }

}