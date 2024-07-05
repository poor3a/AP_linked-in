package DAO;

import exceptions.ProfileDAOException;
import exceptions.UserDAOException;
import models.Post;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class SearchDAO
{
    Connection connection;
    Statement statement;
    PostDAO postDAO;
    UserDAO userDAO;
    ProfileDAO profileDAO;

    public SearchDAO() throws SQLException {
        this.connection = SQL.getConnection();
        this.statement = connection.createStatement();
        this.postDAO = new PostDAO();
        this.userDAO = new UserDAO();
        this.profileDAO = new ProfileDAO();
    }

    public ArrayList<User> searchUser(String nameOrLastnameOrEmail) throws ProfileDAOException
    {
        return profileDAO.searchUsers(nameOrLastnameOrEmail);
    }
    public void addHashtag(String hashtag ,int post_id) throws ProfileDAOException {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO hashtags (hashtag, post_id) VALUES (?, ?)");
            ps.setString(1, hashtag);
            ps.setInt(2, post_id);
            ps.execute();
        } catch (SQLException e) {
            throw new ProfileDAOException("Error in adding hashtag");
        }
    }
    public ArrayList<Post> searchHashtag(String hashtag) throws ProfileDAOException, UserDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT post_id FROM hashtags WHERE hashtag = ?");
            ps.setString(1, hashtag);
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                posts.add(postDAO.getPost(resultSet.getInt("post_id")));
            }
            return posts;
        } catch (SQLException e) {
            throw new ProfileDAOException("Error in searching hashtag");
        }
    }
}
