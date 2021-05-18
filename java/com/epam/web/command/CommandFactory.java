package com.epam.web.command;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.service.*;
import com.epam.web.validator.ResponseValidator;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CommandFactory {

    private final static String LOGIN = "login";
    private final static String INVALID_LOGIN = "invalidLogin";
    private final static String MAIN = "mainPage";
    private final static String CV = "cv";
    private final static String RESPONSES = "responses";
    private final static String RESPONSE_DETAILS = "responseDetails";
    private final static String LOGOUT = "logout";
    private final static String VACANCY = "vacancy";
    private final static String EDIT_CV = "editCv";
    private final static String UPDATE_CV = "updateCv";
    private final static String EDIT_VACANCY = "editVacancy";
    private final static String UPDATE_VACANCY = "updateVacancy";
    private final static String CREATE_VACANCY = "createVacancy";
    private final static String DELETE_VACANCY = "deleteVacancy";
    private final static String CREATE_RESPONSE = "createResponse";
    private final static String UPDATE_RESPONSE = "updateResponse";
    private final static String PHOTO = "photo";
    private final static String EMPTY = "empty";
    private final static String VACANCY_LIST = "vacancyList";
    private final static String RESPONSE_LIST = "responseList";
    private final static String USER_LIST = "userList";
    private final static String GET_VACANCY = "getVacancy";
    private final static String GET_USER = "getUser";
    private final static String GET_RESPONSE = "getResponse";
    private final static String GET_RECRUITING_PROCESS = "getProcess";
    private final static String IMAGE = "image";
    private final static String APPLICANTS = "applicants";
    private final static String APPLICANT_LIST = "applicantList";
    private final static String APPLY = "apply";
    private final static String FEEDBACK = "feedback";
    private final static String UPDATE_RESPONSE_PROCESS = "updateResponseAndProcess";
    private final static String UPDATE_PROCESS = "updateProcess";
    private final static String BLOCK = "block";

    private final static String INDEX_PAGE = "index.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/main.jsp";
    private final static String CV_PAGE = "WEB-INF/view/cv.jsp";
    private final static String RESPONSES_PAGE = "WEB-INF/view/responses.jsp";
    private final static String RESPONSE_DETAILS_PAGE = "WEB-INF/view/responseDetails.jsp";
    private final static String VACANCY_PAGE = "WEB-INF/view/vacancy.jsp";
    private final static String EDIT_CV_PAGE = "WEB-INF/view/editCv.jsp";
    private final static String EDIT_VACANCY_PAGE = "WEB-INF/view/editVacancy.jsp";
    private final static String CREATE_RESPONSE_PAGE = "WEB-INF/view/createResponse.jsp";
    private final static String EMPTY_PAGE = "WEB-INF/view/fragments/empty.jsp";
    private final static String APPLICANTS_PAGE = "WEB-INF/view/applicants.jsp";
    private final static String UPDATE_AND_FEEDBACK_PAGE = "WEB-INF/view/updateAndFeedback.jsp";
    private final UserService userService;
    private final ApplicantService applicantService;
    private final VacancyService vacancyService;
    private final ResponseService responseService;
    private final RecruitingProcessService processService;

    public CommandFactory() {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        userService = new UserService(daoHelperFactory);
        applicantService = new ApplicantService(daoHelperFactory);
        vacancyService = new VacancyService(daoHelperFactory);
        responseService = new ResponseService(daoHelperFactory);
        processService = new RecruitingProcessService(daoHelperFactory, new ResponseValidator());

    }


    public Command create(String type) {
        switch (type) {
            case LOGIN:
                return new LoginCommand(userService);
            case INVALID_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case EMPTY:
                return new ShowPageCommand(EMPTY_PAGE);
            case FEEDBACK:
                return new ShowPageCommand(UPDATE_AND_FEEDBACK_PAGE);
            case UPDATE_PROCESS:
                return new UpdateRecruitingProcessCommand(processService);
            case UPDATE_RESPONSE_PROCESS:
                return new UpdateProcessWithFeedbackCommand(processService);
            case APPLY:
                return new ApplyCommand(processService, userService, vacancyService);
            case VACANCY_LIST:
                return new GetVacanciesByPageCommand(vacancyService);
            case RESPONSE_LIST:
                return new GetResponsesList(responseService);
            case USER_LIST:
                return new GetAllUsersCommand(userService);
            case APPLICANT_LIST:
                return new GetApplicantsByVacancyCommand(processService, vacancyService);
            case GET_VACANCY:
                return new GetVacancyCommand(vacancyService);
            case GET_USER:
                return new GetUserCommand(userService);
            case GET_RESPONSE:
                return new GetResponseCommand(responseService);
            case GET_RECRUITING_PROCESS:
                return new GetRecruitingProcessCommand(processService);
            case CV:
                return new ShowPageCommand(CV_PAGE);
            case RESPONSES:
                return new ShowPageCommand(RESPONSES_PAGE);
            case VACANCY:
                return new ShowPageCommand(VACANCY_PAGE);
            case RESPONSE_DETAILS:
                return new ShowPageCommand(RESPONSE_DETAILS_PAGE);
            case APPLICANTS:
                return new ShowPageCommand(APPLICANTS_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case EDIT_CV:
                return new ShowPageCommand(EDIT_CV_PAGE);
            case UPDATE_CV:
                return new EditCvCommand(applicantService);
            case EDIT_VACANCY:
                return new ShowPageCommand(EDIT_VACANCY_PAGE);
            case UPDATE_VACANCY:
                return new EditVacancyCommand(vacancyService);
            case CREATE_VACANCY:
                return new CreateVacancyCommand();
            case DELETE_VACANCY:
                return new DeleteVacancyCommand(vacancyService);
            case CREATE_RESPONSE:
                return new ShowPageCommand(CREATE_RESPONSE_PAGE);
            case UPDATE_RESPONSE:
                return new CreateResponseCommand(responseService, processService);
            case IMAGE:
                return new GetPhotoCommand(applicantService);
            case PHOTO:
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                return new UploadFileCommand(applicantService, servletFileUpload);
            case BLOCK:
                return new BlockCommand(userService);
            default:
                throw new IllegalArgumentException("Unknown type of command" + type);
        }
    }
}
