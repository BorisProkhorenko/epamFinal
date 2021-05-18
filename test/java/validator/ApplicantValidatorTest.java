package validator;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;
import com.epam.web.enums.Role;
import com.epam.web.validator.ApplicantValidator;
import org.junit.Assert;
import org.junit.Test;

public class ApplicantValidatorTest {

    private final static ApplicantValidator VALIDATOR = new ApplicantValidator();
    private final static String VALID_NAME = "Ivan Петров";
    private final static String INVALID_NAME = "Ivan 6Petrov";
    private final static String EMPTY_NAME = "";
    private final static int VALID_AGE = 20;
    private final static int OVERSIZE_AGE = 100;
    private final static int INSUFFICIENT_AGE = 19;
    private final static String VALID_OTHER = " Some text Some textSome textSome" +
            "Some textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            "Some textSome textSome textSome textSome textSome textSome textSome textSome textSome text" +
            "Some textSome textSome textSome textSome textSome textSome textSome textSome text";
    private final static String VALID_PHOTO = "C:/Users/IdeaProjects/EpamCourse/web-2021/src/main/webapp/avatar.png";
    private final static String OVERSIZE_OTHER = "Some text here Some text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here";
    private final static User MOCK_USER = new User(1L, "", "", Role.ADMIN, false);


    @Test
    public void testValidApplicantShouldReturnTrue() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidApplicantInvalidNameShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, INVALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeNameShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_OTHER, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizePhotoShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeContactsShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, OVERSIZE_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeEducationShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                OVERSIZE_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeExperienceShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, OVERSIZE_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeSkillsShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                OVERSIZE_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantInsufficientAgeShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, INSUFFICIENT_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantOversizeAgeShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, VALID_NAME, Gender.MALE, OVERSIZE_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidApplicantEmptyNameShouldReturnFalse() {
        //given
        Applicant applicant = new Applicant(MOCK_USER, EMPTY_NAME, Gender.MALE, VALID_AGE,
                VALID_PHOTO, VALID_OTHER,
                VALID_OTHER, VALID_OTHER,
                VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(applicant);
        //then
        Assert.assertFalse(isValid);
    }
}
