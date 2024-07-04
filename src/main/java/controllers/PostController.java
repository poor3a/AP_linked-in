package controllers;

import DAO.PostDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.UserDAO;
import com.google.gson.Gson;
import exceptions.UserDAOException;
import models.Post;
import models.User;

public class PostController
{
    UserDAO userDAO;
    PostDAO postDAO;
    Gson gson;
    public PostController() throws SQLException
    {
        this.userDAO = new UserDAO();
        this.postDAO = new PostDAO();
        this.gson = new Gson();
    }
    public void createPost(String email, String text, String image_path) throws SQLException, UserDAOException
    {
        postDAO.createPost(email, text, image_path);
    }
    public void editPost(String email, String text, String image_path) throws SQLException, UserDAOException
    {
        postDAO.editPost(email, text, image_path);
    }
    public void deletePost(int postId ,String userEmail) throws SQLException, UserDAOException
    {
        postDAO.deletePost(postId ,userEmail);
    }
    public String getPost(int postId) throws SQLException, UserDAOException
    {
        return gson.toJson(postDAO.getPost(postId));
    }
    public String getUserPosts(String email) throws SQLException, UserDAOException
    {
        return gson.toJson(postDAO.getUserPosts(email));
    }
    public void likePost(String email, int postId) throws SQLException, UserDAOException
    {
        postDAO.likePost(email, postId);
    }
    public void deleteLike(String email, int postId) throws SQLException, UserDAOException
    {
        postDAO.deleteLike(email, postId);
    }
    public void commentPost(String email, int postId, String text) throws SQLException, UserDAOException
    {
        postDAO.addComment(email, postId, text);
    }
    public void deleteComment(int commentId ,String userEmail) throws SQLException, UserDAOException
    {
        postDAO.deleteComment(commentId ,userEmail);
    }
    public String getComment(int commentId) throws SQLException, UserDAOException
    {
        return gson.toJson(postDAO.getComment(commentId));
    }
    public String getFeedPosts(String email) throws SQLException, UserDAOException
    {
        User[] connections = userDAO.getConnections(email);
        ArrayList<Post> posts = new ArrayList<>();
        for (User user : connections)
        {
            for (Post post : postDAO.getUserPosts(user.getEmail()))
            {
                posts.add(post);
            }
        }
        User[] following = userDAO.getFollowings(email);
        for (User user : following)
        {
            for (Post post : postDAO.getUserPosts(user.getEmail()))
            {
                posts.add(post);
            }
        }
        return gson.toJson(posts);
    }


}
