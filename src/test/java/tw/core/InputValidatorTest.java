package tw.core;

import org.junit.Assert;
import org.junit.Test;
import tw.validator.InputValidator;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    @Test
    public void should_be_invalid_if_input_number_count_more_than_4(){
        InputValidator validator = new InputValidator();
        Boolean validate = validator.validate("1 2 3 4 5");
        Assert.assertFalse(validate);
    }

    @Test
    public void should_be_invalid_if_input_number_larger_than_9(){
        InputValidator validator = new InputValidator();
        Boolean validate = validator.validate("1 2 4 10");
        Assert.assertFalse(validate);
    }

    @Test
    public void should_be_invalid_if_distinct_number_count_less_than_4(){
        InputValidator validator = new InputValidator();
        Boolean validate = validator.validate("1 3 4 4");
        Assert.assertFalse(validate);
    }
}
