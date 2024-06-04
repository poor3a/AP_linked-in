package DAO;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ProfileDAOException;

public class ProfileDAO {
    //profileDAO is a class that is responsible for handling the profile data access object
    //and profile is related to the user in the database.

    Connection connection;
    Statement statement;

    public ProfileDAO() throws SQLException {
        this.connection = SQL.getConnection();//this is a method that is used to get the connection to the database.
        this.statement = connection.createStatement();//this is a method that is used to create a statement object that will be used to execute sql queries.
    }

    public void createSchooling(int id, String schoolName, String degree, String fieldOfStudy, String start, String end, double grade, String description, String activities) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO schooling(sc_id, schoolName, degree, fieldOfStudy, start, end ,grade ,description ,activities) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?)"
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
    public boolean schoolingExist(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM schooling WHERE sc_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if schooling exists");
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
    public void createJobStatement(int id, String title,String workingState, String companyName, String companyAddress, String workongType, byte isWorking, String start, String end, String description) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO jobstatement(js_id, title ,workingState , companyName, companyAddress, workingType, isWorking, start, end, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, workingState);
            preparedStatement.setString(4, companyName);
            preparedStatement.setString(5, companyAddress);
            preparedStatement.setString(6, workongType);
            preparedStatement.setByte(7, isWorking);
            preparedStatement.setString(8, start);
            preparedStatement.setString(9, end);
            preparedStatement.setString(10, description);
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating job statement");
        }
    }
    public void updateJobStatement(int id, String title,String workingState, String companyName, String companyAddress, String workongType, byte isWorking, String start, String end, String description) throws ProfileDAOException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE jobstatement SET title = ?, workingState = ?, companyName = ?, companyAddress = ?, workingType = ?, isWorking = ?, start = ?, end = ?, description = ? WHERE js_id = ?"
            );
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, workingState);
            preparedStatement.setString(3, companyName);
            preparedStatement.setString(4, companyAddress);
            preparedStatement.setString(5, workongType);
            preparedStatement.setByte(6, isWorking);
            preparedStatement.setString(7, start);
            preparedStatement.setString(8, end);
            preparedStatement.setString(9, description);
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error updating job statement");
        }
    }
    public boolean jobStatementExist(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM jobstatement WHERE js_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if job statement exists");
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
    public void createContactInfo(int id ,String address, String email, String phoneNumber_home, String phoneNumber_mobile, String phoneNumber_work, String socialMedia1, String socialMedia2, String socialMedia3, String website) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO contactinfo(ci_id, address, email, phoneNumber_home, phoneNumber_mobile, phoneNumber_work, socialMedia1, socialMedia2, socialMedia3, website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber_home);
            preparedStatement.setString(5, phoneNumber_mobile);
            preparedStatement.setString(6, phoneNumber_work);
            preparedStatement.setString(7, socialMedia1);
            preparedStatement.setString(8, socialMedia2);
            preparedStatement.setString(9, socialMedia3);
            preparedStatement.setString(10, website);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error creating contact info");
        }


    }
    public void updateContactInfo(int id ,String address, String email, String phoneNumber_home, String phoneNumber_mobile, String phoneNumber_work, String socialMedia1, String socialMedia2, String socialMedia3, String website) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE contactinfo SET address = ?, email = ?, phoneNumber_home = ?, phoneNumber_mobile = ?, phoneNumber_work = ?, socialMedia1 = ?, socialMedia2 = ?, socialMedia3 = ?, website = ? WHERE ci_id = ?"
            );
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phoneNumber_home);
            preparedStatement.setString(4, phoneNumber_mobile);
            preparedStatement.setString(5, phoneNumber_work);
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
    public boolean contactInfoExist(int id) throws ProfileDAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM contactinfo WHERE ci_id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error checking if contact info exists");
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

}
