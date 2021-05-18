package com.epam.web.service;


import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.ResponseDao;
import com.epam.web.entity.Response;
import com.epam.web.validator.ResponseValidator;

import java.util.List;


public class ResponseService extends AbstractService<Response> {

    public ResponseService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, new ResponseValidator(), Response.TABLE_NAME);
    }

    public List<Response> getResponsesByUserId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            ResponseDao dao = (ResponseDao) helper.createDao(getDaoType());
            return dao.getByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Response> getResponsesByProcessId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            ResponseDao dao = (ResponseDao) helper.createDao(getDaoType());
            return dao.getByProcessId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
