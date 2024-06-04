package controllers;

import DAO.UserDAO;
import models.User;
import com.google.gson.Gson;
import exceptions.UserDAOException;
import java.sql.SQLException;

public class UserController {
    UserDAO userDAO;
    Gson gson;


    public UserController() throws SQLException {
        this.userDAO = new UserDAO();
        //userDAO is a class that is used to interact with the database and help us to access the users in the database.
        //it uses the connection object to interact with the database and gets connection via the SQL class.
        this.gson = new Gson();
        //userController is a class that is used to interact with the UserDAO class and help us to access the users in database.
        //for giving server some information about the user we use gson.
        //gson is a library that is used to convert java objects to json objects.
    }

    public void createUser(String username, String password, String email) throws UserDAOException {

        if (userDAO.userExist_username(username)) {//if the username already exists in the database, we throw an exception.
            throw new UserDAOException("Username already exists");
        } else if (userDAO.userExist_email(email)) {//if the email already exists in the database, we throw an exception.
            throw new UserDAOException("Email already exists");
        } else {
            userDAO.createUser(username, password, email);
        }
    }

    public String getUser(String username) throws UserDAOException {
        //this method is used to get the user from the database and return it as a json object.
        //if somewhere in the process an exception is thrown, we catch it and return the message of the exception.
        //the exception will be caught in the server class and the message will be sent to the client.
        User user = new User(userDAO.getUserId(username), username, userDAO.getUserPassword(username), userDAO.getUserEmail(username));
        return gson.toJson(user);
    }

    public void deleteUser(String username, String password) throws UserDAOException {
        userDAO.deleteUser(username, password);
    }

    public void updatePassword(String username, String password, String newPassword) throws UserDAOException {
        if (userDAO.userExist_username(username)) {
            if (userDAO.checkUserPassword(username, password)) {
                userDAO.updatePassword(username, newPassword);
            } else {
                throw new UserDAOException("Password is incorrect");
            }
        } else {
            throw new UserDAOException("User does not exist");
        }
    }

    public void updateEmail(String username, String password, String newEmail) throws UserDAOException {
        if (userDAO.userExist_username(username)) {
            if (userDAO.checkUserPassword(username, password)) {
                userDAO.updateEmail(username, newEmail);
            } else {
                throw new UserDAOException("Password is incorrect");
            }
        } else {
            throw new UserDAOException("User does not exist");
        }
    }
    public String getUserCreationDate(String username) throws UserDAOException {
        return gson.toJson(userDAO.getUserCreationDate(username));
    }

}