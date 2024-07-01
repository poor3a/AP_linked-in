package DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UserDAOException;
import models.*;
import exceptions.ProfileDAOException;

public class ProfileDAO {
    //profileDAO is a class that is responsible for handling the profile data access object
    //and profile is related to the user in the database.

    Connection connection;
    Statement statement;
    UserDAO userDAO;

    public ProfileDAO() throws SQLException {
        this.connection = SQL.getConnection();//this is a method that is used to get the connection to the database.
        this.statement = connection.createStatement();//this is a method that is used to create a statement object that will be used to execute sql queries.
        this.userDAO = new UserDAO();
    }

    public void createSchooling(int id, String schoolName, String degree, String fieldOfStudy, String start, String end, double grade, String description, String activities) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO schooling(userId, schoolName, degree, fieldOfStudy, start, end ,grade ,description ,activities) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, schoolName);
            preparedStatement.setString(3, degree);
            preparedStatement.setString(4, fieldOfStudy);
            preparedStatement.setString(5, start);
            preparedStatement.setString(6, end);
            preparedStatement.setDouble(7, grade);
            preparedStatement.setString(8, description);
            preparedStatement.setString(9, activities);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating schooling");
        }
    }
    public void updateSchooling(int id, String schoolName, String degree, String fieldOfStudy, String start, String end, double grade, String description, String activities) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE schooling SET schoolName = ?, degree = ?, fieldOfStudy = ?, start = ?, end = ?, grade = ?, description = ?, activities = ? WHERE sc_id = ?"
            );
            preparedStatement.setString(1, schoolName);
            preparedStatement.setString(2, degree);
            preparedStatement.setString(3, fieldOfStudy);
            preparedStatement.setString(4, start);
            preparedStatement.setString(5, end);
            preparedStatement.setDouble(6, grade);
            preparedStatement.setString(7, description);
            preparedStatement.setString(8, activities);
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error updating schooling");
        }
    }

    public void deleteSchooling(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM schooling WHERE sc_id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error deleting schooling");
        }
    }
    public int getSchoolingId(String userId) throws ProfileDAOException, UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT sc_id FROM schooling WHERE sc_id = ?"
            );
            preparedStatement.setInt(1, userDAO.getUserId(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("sc_id");
            } else {
                throw new ProfileDAOException("Schooling does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting schooling id");
        }
    }
    public void createJobStatement(int id, String title,String workingState, String companyName, String companyAddress, String workingType, boolean isWorking, String start, String end, String description) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO jobstatement(userId, title ,workingState , companyName, companyAddress, workingType, isWorking, start, end, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, workingState);
            preparedStatement.setString(4, companyName);
            preparedStatement.setString(5, companyAddress);
            preparedStatement.setString(6, workingType);
            preparedStatement.setBoolean(7, isWorking);
            preparedStatement.setString(8, start);
            preparedStatement.setString(9, end);
            preparedStatement.setString(10, description);
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating job statement");
        }
    }
    public void updateJobStatement(int id, String title,String workingState, String companyName, String companyAddress, String workingType, boolean isWorking, String start, String end, String description) throws ProfileDAOException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE jobstatement SET title = ?, workingState = ?, companyName = ?, companyAddress = ?, workingType = ?, isWorking = ?, start = ?, end = ?, description = ? WHERE js_id = ?"
            );
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, workingState);
            preparedStatement.setString(3, companyName);
            preparedStatement.setString(4, companyAddress);
            preparedStatement.setString(5, workingType);
            preparedStatement.setBoolean(6, isWorking);
            preparedStatement.setString(7, start);
            preparedStatement.setString(8, end);
            preparedStatement.setString(9, description);
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error updating job statement");
        }
    }

    public int getJobStatementId(String userId) throws ProfileDAOException, UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT js_id FROM jobstatement WHERE js_id = ?"
            );
            preparedStatement.setInt(1, userDAO.getUserId(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("js_id");
            } else {
                throw new ProfileDAOException("Job statement does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting job statement id");
        }
    }
    public void deleteJobStatement(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM jobstatement WHERE js_id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error deleting job statement");
        }
    }
    public void createContactInfo(int id ,String address, String email, String phoneNumber_home, String phoneNumber_work, String phoneNumber_personal, String socialMedia1, String socialMedia2, String socialMedia3, String website) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO contactinfo(userId, address, email, phoneNumber_home, phoneNumber_work, phoneNumber_personal, socialMedia1, socialMedia2, socialMedia3, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber_home);
            preparedStatement.setString(5, phoneNumber_work);
            preparedStatement.setString(6, phoneNumber_personal);
            preparedStatement.setString(7, socialMedia1);
            preparedStatement.setString(8, socialMedia2);
            preparedStatement.setString(9, socialMedia3);
            preparedStatement.setString(10, website);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating contact info");
        }


    }
    public void updateContactInfo(int id ,String address, String email, String phoneNumber_home, String phoneNumber_work, String phoneNumber_personal, String socialMedia1, String socialMedia2, String socialMedia3, String website) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE contactinfo SET address = ?, email = ?, phoneNumber(home) = ?, phoneNumber(work) = ?, phoneNumber(personal) = ?, socialMedia1 = ?, socialMedia2 = ?, socialMedia3 = ?, website = ? WHERE ci_id = ?"
            );
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phoneNumber_home);
            preparedStatement.setString(4, phoneNumber_work);
            preparedStatement.setString(5, phoneNumber_personal);
            preparedStatement.setString(6, socialMedia1);
            preparedStatement.setString(7, socialMedia2);
            preparedStatement.setString(8, socialMedia3);
            preparedStatement.setString(9, website);
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error updating contact info");
        }
    }

    public void deleteContactInfo(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM contactinfo WHERE ci_id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error deleting contact info");
        }
    }
    public int getContactInfoId(String userId) throws ProfileDAOException, UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ci_id FROM contactinfo WHERE co_id = ?"
            );
            preparedStatement.setInt(1, userDAO.getUserId(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ci_id");
            } else {
                throw new ProfileDAOException("Contact info does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting contact info id");
        }
    }

    public void createProfile(int id, String firstName, String lastName, String additionalName, String birthDate, String profilePicture, String bg_picture, String title, String place, String career, String jobAiming) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO profile(profileId, name, lastname, additionalName, birthDate, profile-picture, bg_picture, title, place, career, job_aiming) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, additionalName);
            preparedStatement.setString(5, birthDate);
            preparedStatement.setString(6, profilePicture);
            preparedStatement.setString(7, bg_picture);
            preparedStatement.setString(8, title);
            preparedStatement.setString(9, place);
            preparedStatement.setString(10, career);
            preparedStatement.setString(11, jobAiming);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating profile");
        }
    }
    public void updateProfile(int id, String firstName, String lastName, String additionalName, String birthDate, String profilePicture, String bg_picture, String title, String place, String career, String jobAiming) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE profile SET name = ?, lastname = ?, additionalName = ?, birthDate = ?, profile-picture = ?, bg_picture = ?, title = ?, place = ?, career = ?, job_aiming = ? WHERE profileId = ?"
            );
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, additionalName);
            preparedStatement.setString(4, birthDate);
            preparedStatement.setString(5, profilePicture);
            preparedStatement.setString(6, bg_picture);
            preparedStatement.setString(7, title);
            preparedStatement.setString(8, place);
            preparedStatement.setString(9, career);
            preparedStatement.setString(10, jobAiming);
            preparedStatement.setInt(11, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error updating profile");
        }
    }
    public boolean profileExist(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM profile WHERE profileId = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if profile exists");
        }
    }
    public void deleteProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM profile WHERE profileId = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error deleting profile");
        }
    }
    public int getProfileId(String userId) throws ProfileDAOException, UserDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT profileId FROM profile WHERE profileId = ?"
            );
            preparedStatement.setInt(1, userDAO.getUserId(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("profileId");
            } else {
                throw new ProfileDAOException("Profile does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting profile id");
        }
    }
    public void addSchoolingToProfile(int id, int sc_id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set last_schooling = ?  where profileId = ?; "
            );
            preparedStatement.setInt(1, sc_id);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error adding schooling to profile");
        }
    }
    public void removeSchoolingFromProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set last_schooling = null  where profileId = ?; "
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error removing schooling from profile");
        }
    }
    public void addJobStatementToProfile(int id, int js_id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set current_job = ?  where profileId = ?; "
            );
            preparedStatement.setInt(1, js_id);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error adding job statement to profile");
        }
    }
    public void removeJobStatementFromProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set current_job = null  where profileId = ?; "
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error removing job statement from profile");
        }
    }
    public void addContactInfoToProfile(int id, int co_id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set contact_info = ?  where profileId = ?; "
            );
            preparedStatement.setInt(1, co_id);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error adding contact info to profile");
        }
    }
    public void removeContactInfoFromProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update profile set contact_info = null  where profileId = ?; "
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error removing contact info from profile");
        }
    }
    public boolean contactInfoExistInProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM profile WHERE contact_info = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if contact info exists in profile");
        }
    }
    public boolean schoolingExistInProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM profile WHERE last_schooling = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if schooling exists in profile");
        }
    }
    public boolean jobStatementExistInProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM profile WHERE current_job = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if job statement exists in profile");
        }
    }
    public Profile getProfile(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM profile WHERE profileId = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Profile(
                        resultSet.getInt("profileId"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getString("additionalName"),
                        resultSet.getString("birthDate"),
                        resultSet.getString("profile-picture"),
                        resultSet.getString("bg_picture"),
                        resultSet.getString("title"),
                        resultSet.getString("place"),
                        resultSet.getString("career"),
                        resultSet.getString("job_aiming")
                );
            } else {
                throw new ProfileDAOException("Profile does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting profile");
        }
    }
    public JobStatement getJobStatement(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM jobstatement WHERE js_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new JobStatement(
                        resultSet.getInt("js_id"),
                        resultSet.getString("title"),
                        resultSet.getString("companyName"),
                        resultSet.getString("companyAddress"),
                        resultSet.getString("workingType"),
                        resultSet.getString("workingState"),
                        resultSet.getBoolean("isWorking"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getString("description")
                );
            } else {
                throw new ProfileDAOException("Job statement does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting job statement");
        }
    }
    public ContactInfo getContactInfo(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM contactinfo WHERE ci_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ContactInfo(
                        resultSet.getInt("co_id"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber_home"),
                        resultSet.getString("phoneNumber_work"),
                        resultSet.getString("phoneNumber_personal"),
                        resultSet.getString("socialMedia1"),
                        resultSet.getString("socialMedia2"),
                        resultSet.getString("socialMedia3"),
                        resultSet.getString("website")
                );
            } else {
                throw new ProfileDAOException("Contact info does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting contact info");
        }
    }
    public Schooling getSchooling(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schooling WHERE sc_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Schooling(
                        resultSet.getInt("sc_id"),
                        resultSet.getString("schoolName"),
                        resultSet.getString("fieldOfStudy"),
                        resultSet.getString("degree"),
                        resultSet.getString("start"),
                        resultSet.getString("end"),
                        resultSet.getDouble("grade"),
                        resultSet.getString("description"),
                        resultSet.getString("activities")
                );
            } else {
                throw new ProfileDAOException("Schooling does not exist");
            }
        } catch (SQLException e) {
            throw new ProfileDAOException("Error getting schooling");
        }
    }
}
