package service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.VacancyDao;
import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class VacancyServiceTest {

    private static DaoHelperFactory mockDaoHelperFactory;
    private static DaoHelper mockDaoHelper;
    private static VacancyDao mockDao;
    private static VacancyService vacancyService;
    private static Vacancy mockVacancy;
    private final static Long MOCK_ID = 1L;

    @BeforeClass
    public static void initialize() throws DaoException {

        mockDao = Mockito.mock(VacancyDao.class);
        mockDaoHelper = Mockito.mock(DaoHelper.class);
        mockDaoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        when(mockDaoHelperFactory.create())
                .thenReturn(mockDaoHelper);
        when(mockDaoHelper.createDao(Vacancy.TABLE_NAME))
                .thenReturn(mockDao);
        vacancyService = new VacancyService(mockDaoHelperFactory);

        mockVacancy = Mockito.mock(Vacancy.class);

    }


    @Test
    public void testGetAll() throws DaoException, ServiceException {
        //given
        when(mockDao.getAll())
                .thenReturn(new ArrayList<>());
        //when
        List<Vacancy> vacancyList = vacancyService.getAll();
        //then
        Assert.assertNotNull(vacancyList);
    }

    @Test
    public void testGetById() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.of(mockVacancy));
        //when
        Optional<Vacancy> optionalVacancy = Optional.of(vacancyService.getById(MOCK_ID));
        //then
        Assert.assertTrue(optionalVacancy.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testGetByIdWhenEmpty() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        Optional<Vacancy> optionalVacancy = Optional.of(vacancyService.getById(MOCK_ID));
    }

    @Test
    public void testGetVacanciesByPage() throws DaoException, ServiceException {
        //given
        when(mockDao.getWithLimit(anyInt(), anyInt()))
                .thenReturn(new ArrayList<>());
        int page = 5;
        //when
        List<Vacancy> vacancyList = vacancyService.getVacanciesByPage(page);
        //then
        Assert.assertNotNull(vacancyList);
    }

    @Test(expected = ServiceException.class)
    public void testGetVacanciesByPageWithLessThanFirstPage() throws DaoException, ServiceException {
        //given
        when(mockDao.getWithLimit(anyInt(), anyInt()))
                .thenReturn(new ArrayList<>());
        int page = 0;
        //when
        List<Vacancy> vacancyList = vacancyService.getVacanciesByPage(page);

    }

}
