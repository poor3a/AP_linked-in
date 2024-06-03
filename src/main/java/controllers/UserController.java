package controllers;

import DAO.*;
import com.google.gson.Gson;
import exceptions.UserDAOException;
import models.User;

import java.sql.SQLException;

public class UserController {
    UserDAO userDAO;
    Gson gson;


    public UserController() throws SQLException {
        this.userDAO = new UserDAO();
        this.gson = new Gson();
    }

    public void createUser(String username, String password, String email) throws UserDAOException {

        if (userDAO.userExist_username(username)) {
            throw new UserDAOException("Username already exists");
        } else if (userDAO.userExist_email(email)) {
            throw new UserDAOException("Email already exists");
        } else {
            userDAO.createUser(username, password, email);
        }
    }

    public String getUser(String username) throws UserDAOException {
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
    
}