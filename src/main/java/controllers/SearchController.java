package controllers;

import DAO.ProfileDAO;
import DAO.UserDAO;
import exceptions.ProfileDAOException;
import models.Post;
import models.Profile;
import models.User;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.UserDAOException;
import DAO.SearchDAO;

public class SearchController {
	SearchDAO searchDAO;
	Gson gson;

	public SearchController() throws SQLException {
		this.searchDAO = new SearchDAO();
		this.gson = new Gson();
	}

	public String searchUser(String searchedWord) throws ProfileDAOException {
		return gson.toJson(searchDAO.searchUser(searchedWord));
	}

	public void addHashtag(String hashtag, int post_id) throws ProfileDAOException {
		searchDAO.addHashtag(hashtag, post_id);
	}

	public String searchHashtag(String hashtag) throws ProfileDAOException, UserDAOException {
		return gson.toJson(searchDAO.searchHashtag(hashtag));
	}
}
