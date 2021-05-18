package service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.ResponseDao;
import com.epam.web.entity.Response;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class ResponseServiceTest {


    private static DaoHelperFactory mockDaoHelperFactory;
    private static DaoHelper mockDaoHelper;
    private static ResponseDao mockDao;
    private static ResponseService responseService;
    private static Response mockResponse;
    private final static Long MOCK_ID = 1L;

    @BeforeClass
    public static void initialize() throws DaoException {

        mockDao = Mockito.mock(ResponseDao.class);
        mockDaoHelper = Mockito.mock(DaoHelper.class);
        mockDaoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        when(mockDaoHelperFactory.create())
                .thenReturn(mockDaoHelper);
        when(mockDaoHelper.createDao(Response.TABLE_NAME))
                .thenReturn(mockDao);
        responseService = new ResponseService(mockDaoHelperFactory);

        mockResponse = Mockito.mock(Response.class);

    }


    @Test
    public void testGetAll() throws DaoException, ServiceException {
        //given
        when(mockDao.getAll())
                .thenReturn(new ArrayList<>());
        //when
        List<Response> responseList = responseService.getAll();
        //then
        Assert.assertNotNull(responseList);
    }

    @Test
    public void testGetById() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.of(mockResponse));
        //when
        Optional<Response> optionalResponse = Optional.of(responseService.getById(MOCK_ID));
        //then
        Assert.assertTrue(optionalResponse.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testGetByIdWhenEmpty() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        Optional<Response> optionalResponse = Optional.of(responseService.getById(MOCK_ID));
    }
}
