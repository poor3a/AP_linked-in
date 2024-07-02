package DAO;

import exceptions.UserDAOException;
import models.*;

import java.sql.*;

public class PostDAO
{
    Connection connection;
    Statement statement;
    UserDAO userDAO;

    public PostDAO() throws SQLException {
        this.connection = SQL.getConnection();
        this.statement = connection.createStatement();
        this.userDAO = new UserDAO();
    }

    public void createPost(String email, String text ,String image_path) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("INSERT INTO posts (user_id, text, image_path) VALUES (?, ?, ?)");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setString(2, text);
            ps.setString(3, image_path);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error creating post");
        }
    }
    public void editPost(String email, String text, String image_path) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("UPDATE posts SET text = ?, image_path = ? WHERE user_id = ?");
            ps.setString(1, text);
            ps.setString(2, image_path);
            ps.setInt(3, userDAO.getUserId(email));
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error editing post");
        }
    }
    public void deletePost(int postId) throws SQLException, UserDAOException {
        try
        {
            if (!(postExist(postId))) {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("DELETE FROM posts WHERE post_id = ?");
            ps.setInt(1, postId);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error deleting post");
        }
    }
    public boolean postExist(int postId) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM posts WHERE post_id = ?");
            ps.setInt(1, postId);
            return ps.executeQuery().next();
        }catch (SQLException e){
            throw new SQLException("Error checking if post exists");
        }
    }
    public void likePost(String email, int postId) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            } else if (!(postExist(postId)))
            {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("Insert into likes (user_id, post_id) VALUES (?, ?)");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setInt(2, postId);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error liking post");
        }
    }
    public boolean likeExist(String email, int postId) throws SQLException, UserDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM likes WHERE user_id = ? AND post_id = ?");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setInt(2, postId);
            return ps.executeQuery().next();
        }catch (SQLException e){
            throw new SQLException("Error checking if like exists");
        }
    }
    public void addComment(String email, int postId, String text) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            } else if (!(postExist(postId)))
            {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("Insert into comments (user_id, post_id, text) VALUES (?, ?, ?)");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setInt(2, postId);
            ps.setString(3, text);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error adding comment");
        }
    }
    public boolean commentExist(String email , int postId) throws SQLException, UserDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comments WHERE user_id = ? AND post_id = ?");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setInt(2, postId);
            return ps.executeQuery().next();
        }catch (SQLException e){
            throw new SQLException("Error checking if comment exists");
        }
    }
public void deleteComment(int commentId) throws SQLException, UserDAOException {
        try {
            if (!(commentExist(userDAO.getEmail(commentId), commentId))) {
                throw new UserDAOException("Comment does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("DELETE FROM comments WHERE comment_id = ?");
            ps.setInt(1, commentId);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error deleting comment");
        }
    }
    public void deleteLike(String email, int postId) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            } else if (!(postExist(postId)))
            {
                throw new UserDAOException("Post does not exist");
            } else if (!(likeExist(email, postId))){
                throw new UserDAOException("Like does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("DELETE FROM likes WHERE user_id = ? AND post_id = ?");
            ps.setInt(1, userDAO.getUserId(email));
            ps.setInt(2, postId);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error deleting like");
        }
    }
    public void editComment(String email, int postId, String text) throws SQLException, UserDAOException {
        try {
            if(userDAO.userExist(email)){
                throw new UserDAOException("User does not exist");
            } else if (!(postExist(postId)))
            {
                throw new UserDAOException("Post does not exist");
            }else if(!(commentExist(email, postId))){
                throw new UserDAOException("Comment does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("UPDATE comments SET text = ? WHERE user_id = ? AND post_id = ?");
            ps.setString(1, text);
            ps.setInt(2, userDAO.getUserId(email));
            ps.setInt(3, postId);
            ps.execute();
        }catch (SQLException e){
            throw new SQLException("Error editing comment");
        }
    }
    public Post getPost(int postId) throws SQLException, UserDAOException {
        try {
            if (!(postExist(postId))) {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM posts WHERE post_id = ?");
            ps.setInt(1, postId);
            ResultSet resultSet = ps.executeQuery();
            return new Post(resultSet.getInt("post_id"), userDAO.getEmail(resultSet.getInt("user_id")), resultSet.getString("text"), resultSet.getString("image_path"), getLikes(postId), getComments(postId));
        }catch (SQLException e){
            throw new SQLException("Error getting post");
        }
    }
    public Post[] getUserPosts(String email) throws SQLException, UserDAOException {
        try {
            if (!(userDAO.userExist(email))) {
                throw new UserDAOException("User does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM posts WHERE user_id = ?");
            ps.setInt(1, userDAO.getUserId(email));
            ResultSet resultSet = ps.executeQuery();
            Post[] posts = new Post[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                posts[i] = new Post(resultSet.getInt("post_id"), email, resultSet.getString("text"), resultSet.getString("image_path"), getLikes(resultSet.getInt("post_id")), getComments(resultSet.getInt("post_id")));
                i++;
            }
            return posts;
        }catch (SQLException e){
            throw new SQLException("Error getting user posts");
        }

    }

    public Comment[] getComments(int postId) throws SQLException, UserDAOException {
        try {
            if (!(postExist(postId))) {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("SELECT comment_id FROM comments WHERE post_id = ?");
            ps.setInt(1, postId);
            ResultSet resultSet = ps.executeQuery();
            Comment[] comments = new Comment[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                comments[i] = new Comment(resultSet.getInt("comment_id"), userDAO.getEmail(resultSet.getInt("user_id")), resultSet.getString("text"), postId);
                i++;
            }
            return comments;
        }catch (SQLException e){
            throw new SQLException("Error getting comment ids");
        }
    }
    public User[] getLikes(int postId) throws SQLException, UserDAOException {
        try {
            if (!(postExist(postId))) {
                throw new UserDAOException("Post does not exist");
            }
            PreparedStatement ps = connection.prepareStatement("SELECT user_id FROM likes WHERE post_id = ?");
            ps.setInt(1, postId);
            ResultSet resultSet = ps.executeQuery();
            User[] likes = new User[resultSet.getFetchSize()];
            int i = 0;
            while (resultSet.next()) {
                likes[i] = new User(resultSet.getInt("user_id"), userDAO.getEmail(resultSet.getInt("user_id")), userDAO.getUserPassword(userDAO.getEmail(resultSet.getInt("user_id"))));
                i++;
            }
            return likes;
        }catch (SQLException e){
            throw new SQLException("Error getting like ids");
        }
    }
    public Comment getComment(int commentId) throws SQLException, UserDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comments WHERE comment_id = ?");
            ps.setInt(1, commentId);
            ResultSet resultSet = ps.executeQuery();
            return new Comment(resultSet.getInt("comment_id"), userDAO.getEmail(resultSet.getInt("user_id")), resultSet.getString("text"), resultSet.getInt("post_id"));
        }catch (SQLException e){
            throw new SQLException("Error getting comment");
        }
    }

}
