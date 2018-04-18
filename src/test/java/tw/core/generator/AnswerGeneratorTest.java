package tw.core.generator;

import org.junit.Assert;
import org.junit.Test;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    @Test
    public void show_generate_4_numbers_less_than_10() throws OutOfRangeAnswerException {
        AnswerGenerator generator = new AnswerGenerator(new RandomIntGenerator());
        Answer answer = generator.generate();
        String toString = answer.toString();
        String[] split = toString.split(" ");
        Assert.assertEquals(4, split.length);
        boolean allMatch = Arrays.stream(split).allMatch(i -> Integer.parseInt(i) < 10);
        Assert.assertTrue(allMatch);
    }
}

