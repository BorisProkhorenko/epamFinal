package service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    private static DaoHelperFactory mockDaoHelperFactory;
    private static DaoHelper mockDaoHelper;
    private static UserDao mockDao;
    private static UserService userService;
    private static User mockUser;
    private final static Long MOCK_ID = 1L;

    @BeforeClass
    public static void initialize() throws DaoException {

        mockDao = Mockito.mock(UserDao.class);
        mockDaoHelper = Mockito.mock(DaoHelper.class);
        mockDaoHelperFactory = Mockito.mock(DaoHelperFactory.class);
        when(mockDaoHelperFactory.create())
                .thenReturn(mockDaoHelper);
        when(mockDaoHelper.createDao(User.TABLE_NAME))
                .thenReturn(mockDao);
        userService = new UserService(mockDaoHelperFactory);

        mockUser = Mockito.mock(User.class);

    }


    @Test
    public void testGetAll() throws DaoException, ServiceException {
        //given
        when(mockDao.getAllSorted())
                .thenReturn(new ArrayList<>());
        //when
        List<User> userList = userService.getAll();
        //then
        Assert.assertNotNull(userList);
    }

    @Test
    public void testGetById() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.of(mockUser));
        //when
        Optional<User> optionalUser = Optional.of(userService.getById(MOCK_ID));
        //then
        Assert.assertTrue(optionalUser.isPresent());
    }

    @Test(expected = ServiceException.class)
    public void testGetByIdWhenEmpty() throws DaoException, ServiceException {
        //given
        when(mockDao.getById(anyLong()))
                .thenReturn(Optional.empty());
        //when
        Optional<User> optionalUser = Optional.of(userService.getById(MOCK_ID));
    }


}
