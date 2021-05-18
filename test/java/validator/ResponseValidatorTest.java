package validator;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.validator.ResponseValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ResponseValidatorTest {

    private final static ResponseValidator VALIDATOR = new ResponseValidator();
    private final static Long MOCK_ID = 1L;
    private final static String VALID = "Some textSome textSome textSome textSome textSome textSome";
    private final static String OVERSIZE_SUBJECT = "Some textSome textSome textSome textSome textSome textSome textSome" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text";
    private final static String OVERSIZE_DETAILS = "Some textSome textSome textSome textSome textSome textSome textSome" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            " textSome textSome textSome textSome textSome textSome textSome textSome textSome textSome text";

    private final static RecruitingProcess MOCK_RECRUITING_PROCESS = null;
    private final static Date MOCK_DATE = null;

    @Test
    public void testValidResponseShouldReturnTrue() {
        //given
        Response response = new Response(MOCK_ID, VALID, OVERSIZE_SUBJECT, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidResponseOversizeSubjectShouldReturnFalse() {
        //given
        Response response = new Response(MOCK_ID, OVERSIZE_SUBJECT, OVERSIZE_SUBJECT, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidResponseOversizeDetailsShouldReturnFalse() {
        //given
        Response response = new Response(MOCK_ID, OVERSIZE_SUBJECT, OVERSIZE_DETAILS, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertFalse(isValid);
    }

}
