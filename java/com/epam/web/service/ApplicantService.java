package com.epam.web.service;

import com.epam.web.dao.ApplicantDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entity.Applicant;
import com.epam.web.validator.ApplicantValidator;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class ApplicantService extends UserService {


    private static final String JPG = "jpg";
    private final static int MAX_PHOTO = 255;

    public ApplicantService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, new ApplicantValidator(), Applicant.APPLICANT);
    }

    public void SavePhoto(Long id, String filepath, FileItem item) throws ServiceException {
        if (filepath.length() <= MAX_PHOTO) {
            uploadFile(item, filepath);
            updatePhoto(id, filepath);
        } else {
            throw new ServiceException("Too long photo filepath");
        }
    }

    private void updatePhoto(Long id, String photo) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            ApplicantDao applicantDao = (ApplicantDao) helper.createDao(getDaoType());
            applicantDao.updatePhoto(photo, id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void uploadFile(FileItem item, String path) throws ServiceException {
        try {
            InputStream fileContent = item.getInputStream();
            BufferedImage image = ImageIO.read(fileContent);
            ImageIO.write(image, JPG, new File(path));
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
