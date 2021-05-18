package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.validator.LoginValidator;
import com.epam.web.validator.Validator;

import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User> {

    public UserService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, new LoginValidator(), User.TABLE_NAME);
    }

    protected UserService(DaoHelperFactory daoHelperFactory, Validator validator, String daoType) {
        super(daoHelperFactory, validator, daoType);
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            return userDao.getAllSorted();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void changeBlock(Long id, boolean isBlocked) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            isBlocked = !isBlocked;
            userDao.setUserBlockById(id, isBlocked);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            return userDao.findUserByUsernameAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}
