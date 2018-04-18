package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

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

}