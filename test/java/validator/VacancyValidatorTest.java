package validator;

import com.epam.web.entity.Vacancy;
import com.epam.web.validator.VacancyValidator;
import org.junit.Assert;
import org.junit.Test;

public class VacancyValidatorTest {

    private final static VacancyValidator VALIDATOR = new VacancyValidator();
    private final static Long MOCK_ID = 1L;
    private final static String VALID_NAME = "Java Developer";
    private final static String INVALID_NAME = "Java 5Developer";
    private final static String EMPTY_NAME = "";
    private final static String OVERSIZE_NAME = " Java DeveloperJava DeveloperJava DeveloperJava DeveloperJava Developer";
    private final static String OVERSIZE_SALARY = "Based on the results of the interviewBased on the results of the ";
    private final static String VALID_SALARY = "Based on the results of the interview";
    private final static String VALID_OTHER = "Some text here Some text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here";
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

    @Test
    public void testValidVacancyShouldReturnTrue() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME, VALID_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidVacancyWithInvalidNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, INVALID_NAME, VALID_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }


    @Test
    public void testInvalidVacancyWithOversizeNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, OVERSIZE_NAME, VALID_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeSalaryShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME, OVERSIZE_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeResponsibilityShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME, VALID_SALARY, OVERSIZE_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeDescriptionShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME, VALID_SALARY, VALID_OTHER, OVERSIZE_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeRequirementsShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME, VALID_SALARY, VALID_OTHER, VALID_OTHER, OVERSIZE_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithEmptyNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, EMPTY_NAME, VALID_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }
}
