package com.epam.web.service;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entity.Identifiable;
import com.epam.web.validator.Validator;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> {

    private final DaoHelperFactory daoHelperFactory;
    private final Validator validator;
    private final String daoType;

    public AbstractService(DaoHelperFactory daoHelperFactory, Validator validator, String daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.validator = validator;
        this.daoType = daoType;
    }

    public List<T> getAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public T getById(Long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
            Optional<T> item = dao.getById(id);
            if (item.isPresent()) {
                return item.get();
            } else {
                throw new ServiceException("Item not found");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(T item) throws ServiceException {
        if (validator.validate(item)) {
            try (DaoHelper helper = getDaoHelperFactory().create()) {
                AbstractDao<T> dao = helper.createDao(daoType);
                dao.save(item);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            throw new ServiceException("Validation failed");
        }
    }

    public void deleteById(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public String getDaoType() {
        return daoType;
    }

    protected DaoHelperFactory getDaoHelperFactory() {
        return daoHelperFactory;
    }

    protected Validator<T> getValidator() {
        return validator;
    }
}
