package controllers;
import DAO.ProfileDAO;
import DAO.UserDAO;
import exceptions.ProfileDAOException;
import models.User;
import com.google.gson.Gson;
import java.sql.SQLException;
import exceptions.UserDAOException;
public class ProfileController
{
    UserDAO userDAO;
    ProfileDAO profileDAO;
    Gson gson;

    public ProfileController() throws SQLException {
        this.userDAO = new UserDAO();
        this.profileDAO = new ProfileDAO();
        this.gson = new Gson();
    }

    public void createprofile(String username, String firstname, String lastName , String additionalName, String birthDate, String profilePicture, String bg_picture, String title, String place, String career, String jobAiming) throws ProfileDAOException, UserDAOException {

            if (!userDAO.userExist_username(username)) {
                throw new ProfileDAOException("User does not exist");
            } else if (profileDAO.profileExist(userDAO.getUserId(username))) {
                throw new ProfileDAOException("Profile already exists");
            } else {
                profileDAO.createProfile(userDAO.getUserId(username), firstname, lastName, additionalName, birthDate, profilePicture, bg_picture, title, place, career, jobAiming);
            }
    }
    public void deleteProfile(String username) throws UserDAOException, ProfileDAOException {
        if (!userDAO.userExist_username(username)) {
            throw new ProfileDAOException("User does not exist");
        } else if (!profileDAO.profileExist(userDAO.getUserId(username))) {
            throw new ProfileDAOException("Profile does not exist");
        } else {
            profileDAO.deleteProfile(userDAO.getUserId(username));
        }
    }
    public void updateProfile(String username, String firstname, String lastName , String additionalName, String birthDate, String profilePicture, String bg_picture, String title, String place, String career, String jobAiming) throws ProfileDAOException, UserDAOException {
        if (!userDAO.userExist_username(username)) {
            throw new ProfileDAOException("User does not exist");
        } else if (!profileDAO.profileExist(userDAO.getUserId(username))) {
            throw new ProfileDAOException("Profile does not exist");
        } else {
            profileDAO.updateProfile(userDAO.getUserId(username), firstname, lastName, additionalName, birthDate, profilePicture, bg_picture, title, place, career, jobAiming);
        }
    }
    public void createSchooling(String username, String schoolName ,String fieldOfStudy ,String degree ,String start ,String end ,double grade ,String description ,String activities) throws ProfileDAOException, UserDAOException {
        if (!userDAO.userExist_username(username)) {
            throw new ProfileDAOException("User does not exist");
        } else {
            profileDAO.createSchooling(userDAO.getUserId(username), schoolName, fieldOfStudy, degree, start, end, grade, description, activities);
        }
    }
    public void addLastSchooling(String schoolName ,String username) throws UserDAOException, ProfileDAOException {
        int sc_id = profileDAO.getSchoolingId(schoolName);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (profileDAO.schoolingExistInProfile(sc_id)) {
            throw new ProfileDAOException("Schooling already exists in profile");
        }
        else {
            profileDAO.addSchoolingToProfile( userDAO.getUserId(username) ,sc_id);
        }
    }
    public void changeLastSchooling(String schoolName,String username) throws UserDAOException, ProfileDAOException {
        int sc_id  = profileDAO.getSchoolingId(schoolName);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.schoolingExistInProfile(sc_id)) {
            throw new ProfileDAOException("Schooling does not exist in profile");
        }
        else {
            profileDAO.removeSchoolingFromProfile( userDAO.getUserId(username));
            profileDAO.addSchoolingToProfile( userDAO.getUserId(username) ,sc_id);
        }
    }
    public void deleteSchooling(String schoolName,String username) throws UserDAOException, ProfileDAOException {
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.schoolingExistInProfile(userDAO.getUserId(username))) {
            throw new ProfileDAOException("Schooling does not exist in profile");
        }
        else {
            profileDAO.removeSchoolingFromProfile( userDAO.getUserId(username));
            profileDAO.deleteSchooling(profileDAO.getSchoolingId(schoolName));
        }
    }
    public void updateSchooling(String username ,String schoolName ,String fieldOfStudy ,String degree ,String start ,String end ,double grade ,String description ,String activities) throws UserDAOException, ProfileDAOException {
        int sc_id = profileDAO.getSchoolingId(username);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.schoolingExistInProfile(sc_id)) {
            throw new ProfileDAOException("Schooling does not exist in profile");
        }
        else {
            profileDAO.updateSchooling(userDAO.getUserId(username),schoolName, fieldOfStudy, degree, start, end, grade, description, activities);
        }
    }
    public void createJob_Statement(String username ,String title ,String workingState ,String companyName ,String companyAddress ,String workingType ,byte isWorking ,String start ,String end ,String description) throws ProfileDAOException, UserDAOException {
        if (!userDAO.userExist_username(username)) {
            throw new ProfileDAOException("User does not exist");
        } else {
            profileDAO.createJobStatement(userDAO.getUserId(username), title, workingState, companyName, companyAddress, workingType, isWorking, start, end, description);
        }
    }
    public void addCurrent_job(String title,String username) throws UserDAOException, ProfileDAOException {
        int js_id = profileDAO.getJobStatementId(title);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (profileDAO.jobStatementExistInProfile(js_id)) {
            throw new ProfileDAOException("Job Statement already exists in profile");
        }
        else {
            profileDAO.addJobStatementToProfile( userDAO.getUserId(username) ,js_id);
        }
    }
    public void changeCurrent_job(String title,String username) throws UserDAOException, ProfileDAOException {
        int js_id  = profileDAO.getJobStatementId(title);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.jobStatementExistInProfile(js_id)) {
            throw new ProfileDAOException("Job Statement does not exist in profile");
        }
        else {
            profileDAO.removeJobStatementFromProfile( userDAO.getUserId(username));
            profileDAO.addJobStatementToProfile( userDAO.getUserId(username) ,js_id);
        }
    }
    public void deleteJob_Statement(String title,String username) throws UserDAOException, ProfileDAOException {
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.jobStatementExistInProfile(userDAO.getUserId(username))) {
            throw new ProfileDAOException("Job Statement does not exist in profile");
        }
        else {
            profileDAO.removeJobStatementFromProfile( userDAO.getUserId(username));
            profileDAO.deleteJobStatement(profileDAO.getJobStatementId(title));
        }
    }
    public void updateJob_Statement(String username ,String title ,String workingState ,String companyName ,String companyAddress ,String workingType ,byte isWorking ,String start ,String end ,String description) throws UserDAOException, ProfileDAOException {
        int js_id = profileDAO.getJobStatementId(username);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.jobStatementExistInProfile(js_id)) {
            throw new ProfileDAOException("Job Statement does not exist in profile");
        }
        else {
            profileDAO.updateJobStatement(userDAO.getUserId(username),title, workingState, companyName, companyAddress, workingType, isWorking, start, end, description);
        }
    }
    public void addContact_info(String username ,String address ,String email ,String phoneNumber_home ,String phoneNumber_work ,String phoneNumber_personal ,String socialMedia1 ,String socialMedia2 ,String socialMedia3 ,String website) throws ProfileDAOException, UserDAOException {
        if (!userDAO.userExist_username(username)) {
            throw new ProfileDAOException("User does not exist");
        } else {
            profileDAO.createContactInfo(userDAO.getUserId(username), address, email, phoneNumber_home, phoneNumber_work, phoneNumber_personal, socialMedia1, socialMedia2, socialMedia3, website);
            profileDAO.addContactInfoToProfile(userDAO.getUserId(username), profileDAO.getContactInfoId(address));
        }
    }

    public void updateContact_info(String username ,String address ,String email ,String phoneNumber_home ,String phoneNumber_work ,String phoneNumber_personal ,String socialMedia1 ,String socialMedia2 ,String socialMedia3 ,String website) throws UserDAOException, ProfileDAOException {
        int co_id = profileDAO.getContactInfoId(username);
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.contactInfoExistInProfile(co_id)) {
            throw new ProfileDAOException("Contact Info does not exist in profile");
        }
        else {
            profileDAO.updateContactInfo(userDAO.getUserId(username),address, email, phoneNumber_home, phoneNumber_work, phoneNumber_personal, socialMedia1, socialMedia2, socialMedia3, website);
        }
    }
    public void deleteContact_info(String username) throws UserDAOException, ProfileDAOException {
        if (!userDAO.userExist_username(username))
        {
            throw new ProfileDAOException("User does not exist");
        }else if (!profileDAO.contactInfoExistInProfile(userDAO.getUserId(username))) {
            throw new ProfileDAOException("Contact Info does not exist in profile");
        }
        else {
            profileDAO.removeContactInfoFromProfile( userDAO.getUserId(username));
        }
    }


//get methods will be added in the future.



}
