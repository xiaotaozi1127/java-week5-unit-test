package tw.core;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void show_throw_exception_if_need_number_count_larger_than_max_limit(){
        RandomIntGenerator generator = new RandomIntGenerator();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can't ask for more numbers than are available");
        generator.generateNums(3, 4);
    }

    @Test
    public void show_generate_required_number_count_and_with_in_max_limit(){
        RandomIntGenerator generator = new RandomIntGenerator();
        String generateNums = generator.generateNums(5, 4);
        String[] split = generateNums.split(" ");
        boolean allMatch = Arrays.stream(split).allMatch(i -> Integer.parseInt(i) <= 5);
        assertEquals(4, split.length);
        Assert.assertTrue(allMatch);
    }
}