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
		// userDAO is a class that is used to interact with the database and help us to
		// access the users in the database.
		// it uses the connection object to interact with the database and gets
		// connection via the SQL class.
		this.gson = new Gson();
		// userController is a class that is used to interact with the UserDAO class and
		// help us to access the users in database.
		// for giving server some information about the user we use gson.
		// gson is a library that is used to convert java objects to json objects.

	}

	public void createUser(String email, String password) throws UserDAOException {

		if (userDAO.userExist(email)) {// if the email already exists in the database, we throw an exception.
			throw new UserDAOException("Email already exists");
		} else {
			userDAO.createUser(email, password);
		}
	}

    public String getUser(String email) throws UserDAOException {
        //this method is used to get the user from the database and return it as a json object.
        //if somewhere in the process an exception is thrown, we catch it and return the message of the exception.
        //the exception will be caught in the server class and the message will be sent to the client.
        User user = new User(userDAO.getUserId(email), email, userDAO.getUserPassword(email));
        return gson.toJson(user);
    }
    public boolean checkLogin(String email, String password) throws UserDAOException {
        //this method is used to check if the user exists in the database and if the password is correct.
        //if the user does not exist or the password is incorrect, we return false.
        //if the user exists and the password is correct, we return true.
        if (userDAO.userExist(email)) {
            return userDAO.checkUserPassword(email, password);
        } else {
            return false;
        }
    }
	public void deleteUser(String email, String password) throws UserDAOException {
		userDAO.deleteUser(email, password);
		userDAO.deleteAllFollowersAndFollowings(email);
		userDAO.deleteAllConnections(email);
	}

	public void updatePassword(String email, String password, String newPassword) throws UserDAOException {
		if (userDAO.userExist(email)) {
			if (userDAO.checkUserPassword(email, password)) {
				userDAO.updatePassword(email, newPassword);
			} else {
				throw new UserDAOException("Password is incorrect");
			}
		} else {
			throw new UserDAOException("User does not exist");
		}
	}

	public void updateEmail(String email, String password, String newEmail) throws UserDAOException {
		if (userDAO.userExist(email)) {
			if (userDAO.checkUserPassword(email, password)) {
				userDAO.updateEmail(email, newEmail);
			} else {
				throw new UserDAOException("Password is incorrect");
			}
		} else {
			throw new UserDAOException("User does not exist");
		}
	}

	public String getUserCreationDate(String email) throws UserDAOException {
		return gson.toJson(userDAO.getUserCreationDate(email));
	}

	public void followUser(String email, String userToFollow) throws UserDAOException {
		userDAO.followUser(email, userToFollow);
	}

	public void unfollowUser(String email, String userToUnfollow) throws UserDAOException {
		userDAO.unfollowUser(email, userToUnfollow);
	}

	public String getFollowers(String email) throws UserDAOException {
		return gson.toJson(userDAO.getFollowers(email));
	}

	public String getFollowings(String email) throws UserDAOException {
		return gson.toJson(userDAO.getFollowings(email));
	}

	public String getConnections(String email) throws UserDAOException {
		return gson.toJson(userDAO.getConnections(email));
	}

	public void addConnection(String email, String connectedUserEmail) throws UserDAOException {
		userDAO.createUserConnection(email, connectedUserEmail);
	}

	public void removeConnection(String email, String connectedUserEmail) throws UserDAOException {
		userDAO.deleteUserConnection(email, connectedUserEmail);
	}

}