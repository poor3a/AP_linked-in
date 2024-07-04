package controllers;

import DAO.ProfileDAO;
import DAO.UserDAO;
import exceptions.ProfileDAOException;
import models.Profile;
import models.User;
import com.google.gson.Gson;
import java.sql.SQLException;
import exceptions.UserDAOException;

public class ProfileController {
	UserDAO userDAO;
	ProfileDAO profileDAO;
	Gson gson;

	public ProfileController() throws SQLException {
		this.userDAO = new UserDAO();
		this.profileDAO = new ProfileDAO();
		this.gson = new Gson();
	}

	public void createprofile(String email, String firstname, String lastName, String additionalName, String birthDate,
			String profilePicture, String bg_picture, String title, String place, String career, String jobAiming)
			throws ProfileDAOException, UserDAOException {

		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile already exists");
		} else {
			profileDAO.createProfile(userDAO.getUserId(email), firstname, lastName, additionalName, birthDate,
					profilePicture, bg_picture, title, place, career, jobAiming);
		}
	}

	public void deleteProfile(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			profileDAO.deleteProfile(userDAO.getUserId(email));
		}
	}

	public void updateProfile(String email, String firstname, String lastName, String additionalName, String birthDate,
			String profilePicture, String bg_picture, String title, String place, String career, String jobAiming)
			throws ProfileDAOException, UserDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			profileDAO.updateProfile(userDAO.getUserId(email), firstname, lastName, additionalName, birthDate,
					profilePicture, bg_picture, title, place, career, jobAiming);
		}
	}

	public void createSchooling(String email, String schoolName, String fieldOfStudy, String degree, String start,
			String end, double grade, String description, String activities)
			throws ProfileDAOException, UserDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else {
			profileDAO.createSchooling(userDAO.getUserId(email), schoolName, fieldOfStudy, degree, start, end, grade,
					description, activities);
		}
	}

	public void addLastSchooling(String schoolName, String email) throws UserDAOException, ProfileDAOException {
		int sc_id = profileDAO.getSchoolingId(schoolName);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (profileDAO.schoolingExistInProfile(sc_id)) {
			throw new ProfileDAOException("Schooling already exists in profile");
		} else {
			profileDAO.addSchoolingToProfile(userDAO.getUserId(email), sc_id);
		}
	}

	public void changeLastSchooling(String schoolName, String email) throws UserDAOException, ProfileDAOException {
		int sc_id = profileDAO.getSchoolingId(schoolName);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.schoolingExistInProfile(sc_id)) {
			throw new ProfileDAOException("Schooling does not exist in profile");
		} else {
			profileDAO.removeSchoolingFromProfile(userDAO.getUserId(email));
			profileDAO.addSchoolingToProfile(userDAO.getUserId(email), sc_id);
		}
	}

	public void deleteSchooling(String schoolName, String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.schoolingExistInProfile(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Schooling does not exist in profile");
		} else {
			profileDAO.removeSchoolingFromProfile(userDAO.getUserId(email));
			profileDAO.deleteSchooling(profileDAO.getSchoolingId(schoolName));
		}
	}

	public void updateSchooling(String email, String schoolName, String fieldOfStudy, String degree, String start,
			String end, double grade, String description, String activities)
			throws UserDAOException, ProfileDAOException {
		int sc_id = profileDAO.getSchoolingId(email);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.schoolingExistInProfile(sc_id)) {
			throw new ProfileDAOException("Schooling does not exist in profile");
		} else {
			profileDAO.updateSchooling(userDAO.getUserId(email), schoolName, fieldOfStudy, degree, start, end, grade,
					description, activities);
		}
	}

	public void createJob_Statement(String email, String title, String workingState, String companyName,
			String companyAddress, String workingType, boolean isWorking, String start, String end, String description)
			throws ProfileDAOException, UserDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else {
			profileDAO.createJobStatement(userDAO.getUserId(email), title, workingState, companyName, companyAddress,
					workingType, isWorking, start, end, description);
		}
	}

	public void addCurrent_job(String title, String email) throws UserDAOException, ProfileDAOException {
		int js_id = profileDAO.getJobStatementId(title);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (profileDAO.jobStatementExistInProfile(js_id)) {
			throw new ProfileDAOException("Job Statement already exists in profile");
		} else {
			profileDAO.addJobStatementToProfile(userDAO.getUserId(email), js_id);
		}
	}

	public void changeCurrent_job(String title, String email) throws UserDAOException, ProfileDAOException {
		int js_id = profileDAO.getJobStatementId(title);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.jobStatementExistInProfile(js_id)) {
			throw new ProfileDAOException("Job Statement does not exist in profile");
		} else {
			profileDAO.removeJobStatementFromProfile(userDAO.getUserId(email));
			profileDAO.addJobStatementToProfile(userDAO.getUserId(email), js_id);
		}
	}

	public void deleteJob_Statement(String title, String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.jobStatementExistInProfile(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Job Statement does not exist in profile");
		} else {
			profileDAO.removeJobStatementFromProfile(userDAO.getUserId(email));
			profileDAO.deleteJobStatement(profileDAO.getJobStatementId(title));
		}
	}

	public void updateJob_Statement(String email, String title, String workingState, String companyName,
			String companyAddress, String workingType, boolean isWorking, String start, String end, String description)
			throws UserDAOException, ProfileDAOException {
		int js_id = profileDAO.getJobStatementId(email);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.jobStatementExistInProfile(js_id)) {
			throw new ProfileDAOException("Job Statement does not exist in profile");
		} else {
			profileDAO.updateJobStatement(userDAO.getUserId(email), title, workingState, companyName, companyAddress,
					workingType, isWorking, start, end, description);
		}
	}

	public void addContact_info(String email, String address, String email2, String phoneNumber_home,
			String phoneNumber_work, String phoneNumber_personal, String socialMedia1, String socialMedia2,
			String socialMedia3, String website) throws ProfileDAOException, UserDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else {
			profileDAO.createContactInfo(userDAO.getUserId(email), address, email2, phoneNumber_home, phoneNumber_work,
					phoneNumber_personal, socialMedia1, socialMedia2, socialMedia3, website);
			profileDAO.addContactInfoToProfile(userDAO.getUserId(email), profileDAO.getContactInfoId(address));
		}
	}

	public void updateContact_info(String email, String address, String email2, String phoneNumber_home, String phoneNumber_work,
			String phoneNumber_personal, String socialMedia1, String socialMedia2, String socialMedia3, String website)
			throws UserDAOException, ProfileDAOException {
		int co_id = profileDAO.getContactInfoId(email);
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.contactInfoExistInProfile(co_id)) {
			throw new ProfileDAOException("Contact Info does not exist in profile");
		} else {
			profileDAO.updateContactInfo(userDAO.getUserId(email), address, email2, phoneNumber_home, phoneNumber_work,
					phoneNumber_personal, socialMedia1, socialMedia2, socialMedia3, website);
		}
	}

	public void deleteContact_info(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.contactInfoExistInProfile(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Contact Info does not exist in profile");
		} else {
			profileDAO.removeContactInfoFromProfile(userDAO.getUserId(email));
		}
	}

	public String getprofile(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			return gson.toJson(profileDAO.getProfile(userDAO.getUserId(email)));
		}
	}

	public String getSchooling(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			return gson.toJson(profileDAO.getSchooling(userDAO.getUserId(email)));
		}
	}

	public String getJob_Statement(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			return gson.toJson(profileDAO.getJobStatement(userDAO.getUserId(email)));
		}
	}

	public String getContact_info(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			return gson.toJson(profileDAO.getContactInfo(userDAO.getUserId(email)));
		}
	}
	public String getAllUserData(String email) throws UserDAOException, ProfileDAOException {
		if (!userDAO.userExist(email)) {
			throw new ProfileDAOException("User does not exist");
		} else if (!profileDAO.profileExist(userDAO.getUserId(email))) {
			throw new ProfileDAOException("Profile does not exist");
		} else {
			return gson.toJson(profileDAO.getAllUserData(userDAO.getUserId(email)));
		}
	}
}
