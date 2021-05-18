package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.validator.RecruitingProcessValidator;
import com.epam.web.validator.ResponseValidator;


import java.util.List;

public class RecruitingProcessService extends AbstractService<RecruitingProcess> {

    private final ResponseValidator responseValidator;

    public RecruitingProcessService(DaoHelperFactory daoHelperFactory, ResponseValidator responseValidator) {
        super(daoHelperFactory, new RecruitingProcessValidator(), RecruitingProcess.TABLE_NAME);
        this.responseValidator = responseValidator;
    }

    public boolean isEmpty(Long userId, Long vacancyId) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.createDao(getDaoType());
            List<RecruitingProcess> items = dao.getByUserAndVacancyId(userId, vacancyId);
            return items.isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<RecruitingProcess> getByVacancyId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.createDao(getDaoType());
            return dao.getByVacancyId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateWithFeedback(RecruitingProcess process, Response feedback) throws ServiceException {
        if (getValidator().validate(process) &&
                responseValidator.validate(feedback)) {
            try (DaoHelper helper = getDaoHelperFactory().create()) {
                helper.startTransaction();
                RecruitingProcessDao processDao = (RecruitingProcessDao) helper.createDao(getDaoType());
                ResponseDao responseDao = (ResponseDao) helper.createDao(Response.TABLE_NAME);
                processDao.save(process);
                responseDao.save(feedback);
                helper.endTransaction();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else if (!getValidator().validate(process)) {
            throw new ServiceException("invalid recruiting process");
        } else {
            throw new ServiceException("invalid feedback response");
        }
    }

}
