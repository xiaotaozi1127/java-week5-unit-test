package tw.core;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void show_generate_required_number_count_and_within_max_limit(){
        RandomIntGenerator generator = new RandomIntGenerator();
        String generateNums = generator.generateNums(5, 4);
        String[] split = generateNums.split(" ");
        boolean allMatch = Arrays.stream(split).allMatch(i -> Integer.parseInt(i) <= 5);
        assertEquals(4, split.length);
        Assert.assertTrue(allMatch);
    }

    @Test
    public void should_generate_required_integer_numbers(){
        RandomIntGenerator generator = new RandomIntGenerator();
        String generateNums = generator.generateNums(5, 4);
        String[] split = generateNums.split(" ");
        assertEquals(4, split.length);
        List<Integer> list = Arrays.stream(split).map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        assertEquals(4, list.size());
    }

    @Test
    public void should_generate_required_distinct_numbers(){
        RandomIntGenerator generator = new RandomIntGenerator();
        String generateNums = generator.generateNums(5, 4);
        String[] split = generateNums.split(" ");
        assertEquals(4, split.length);
        int distinctSize = Arrays.stream(split).distinct().collect(Collectors.toList()).size();
        assertEquals(4, distinctSize);
    }

    @Test
    public void should_randomly_generate_required_numbers(){
        List<String> generates = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            RandomIntGenerator generator = new RandomIntGenerator();
            String generateNums = generator.generateNums(5, 4);
            generates.add(generateNums);
        }
        assertNotEquals(generates.get(0), generates.get(1));
        assertNotEquals(generates.get(0), generates.get(2));
        assertNotEquals(generates.get(1), generates.get(2));
    }
}