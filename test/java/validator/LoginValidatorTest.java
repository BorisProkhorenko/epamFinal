package validator;

import com.epam.web.entity.User;
import com.epam.web.enums.Role;
import com.epam.web.validator.LoginValidator;
import org.junit.Assert;
import org.junit.Test;


public class LoginValidatorTest {

    private final static LoginValidator VALIDATOR = new LoginValidator();
    private final static Long MOCK_ID = 1L;
    private final static String EMPTY = "";
    private final static String VALID = "Admin123";
    private final static String INVALID = "Admin12-3";
    private final static String OVERSIZE = "Some textSome textSome textSome textSome textSome textSome textSome" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text";


    @Test
    public void testValidLoginShouldReturnTrue() {
        //given
        User user = new User(MOCK_ID, VALID, VALID, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidUsernameShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, INVALID, VALID, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidPasswordShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, VALID, INVALID, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testOversizeUsernameShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, OVERSIZE, VALID, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testOversizePasswordShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, VALID, OVERSIZE, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testEmptyPasswordShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, VALID, EMPTY, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testEmptyUsernameShouldReturnFalse() {
        //given
        User user = new User(MOCK_ID, EMPTY, VALID, Role.ADMIN, false);
        //when
        boolean isValid = VALIDATOR.validate(user);
        //then
        Assert.assertFalse(isValid);
    }
}
