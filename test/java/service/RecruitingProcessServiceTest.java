package service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.RecruitingProcessDao;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.service.RecruitingProcessService;
import com.epam.web.service.ServiceException;
import com.epam.web.validator.ResponseValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class RecruitingProcessServiceTest {

    private static DaoHelperFactory mockDaoHelperFactory;
    private static DaoHelper mockDaoHelper;
    private static RecruitingProcessDao mockDao;
    private static RecruitingProcessService recruitingProcessService;
    private static RecruitingProcess mockRecruitingProcess;
    private final static Long MOCK_ID = 1L;

    @BeforeClass
    public static void initialize() throws DaoException {

        mockDao = Mockito.mock(RecruitingProcessDao.class);
        mockDaoHelper = Mockito.mock(DaoHelper.class);
        mockDaoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        when(mockDaoHelperFactory.create())
                .thenReturn(mockDaoHelper);
        when(mockDaoHelper.createDao(RecruitingProcess.TABLE_NAME))
                .thenReturn(mockDao);
        recruitingProcessService = new RecruitingProcessService(mockDaoHelperFactory, new ResponseValidator());

        mockRecruitingProcess = Mockito.mock(RecruitingProcess.class);

    }

    @Test
    public void testGetAll() throws DaoException, ServiceException {
        //given
        when(mockDao.getAll())
                .thenReturn(new ArrayList<>());
        //when
        List<RecruitingProcess> processList = recruitingProcessService.getAll();
        //then
        Assert.assertNotNull(processList);
    }

    @Test
    public void testGetById() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.of(mockRecruitingProcess));
        //when
        Optional<RecruitingProcess> optionalProcess = Optional.of(recruitingProcessService.getById(MOCK_ID));
        //then
        Assert.assertTrue(optionalProcess.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testGetByIdWhenEmpty() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        Optional<RecruitingProcess> optionalProcess = Optional.of(recruitingProcessService.getById(MOCK_ID));
    }
}
