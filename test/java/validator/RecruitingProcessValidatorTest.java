package validator;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;
import com.epam.web.validator.RecruitingProcessValidator;
import org.junit.Assert;
import org.junit.Test;

public class RecruitingProcessValidatorTest {

    private final static RecruitingProcessValidator VALIDATOR = new RecruitingProcessValidator();
    private final static Long MOCK_ID = 1L;
    private final static int VALID_RATING = 50;
    private final static int OVERSIZE_RATING = 101;
    private final static int NEGATIVE_RATING = -1;
    private final static Vacancy MOCK_VACANCY = null;
    private final static User MOCK_USER = null;

    @Test
    public void testValidRecruitingProcessShouldReturnTrue() {
        //given
        RecruitingProcess recruitingProcess = new RecruitingProcess(MOCK_ID, MOCK_USER, MOCK_VACANCY,
                ApplicantState.NEW, VALID_RATING);
        //when
        boolean isValid = VALIDATOR.validate(recruitingProcess);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidRecruitingProcessOversizeRatingShouldReturnFalse() {
        //given
        RecruitingProcess recruitingProcess = new RecruitingProcess(MOCK_ID, MOCK_USER, MOCK_VACANCY,
                ApplicantState.NEW, OVERSIZE_RATING);
        //when
        boolean isValid = VALIDATOR.validate(recruitingProcess);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidRecruitingProcessNegativeRatingShouldReturnFalse() {
        //given
        RecruitingProcess recruitingProcess = new RecruitingProcess(MOCK_ID, MOCK_USER, MOCK_VACANCY,
                ApplicantState.NEW, NEGATIVE_RATING);
        //when
        boolean isValid = VALIDATOR.validate(recruitingProcess);
        //then
        Assert.assertFalse(isValid);
    }
}
