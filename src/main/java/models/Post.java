package models;

public class Post
{
    private int id;
    private String email;
    private String text;
    private String image_path;
    private User[] likes;
    private Comment[] comments;


    public Post(int id, String email, String text, String image_path , User[] likes, Comment[] comments) {
        this.id = id;
        this.email = email;
        this.text = text;
        this.image_path = image_path;
        this.likes = likes;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }

    public String getImage_path() {
        return image_path;
    }
    public User[] getLikes() {
        return likes;
    }
    public  Comment[] getComments() {
        return comments;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    public void setLikes(User[] likes) {
        this.likes = likes;
    }
    public void setComments(Comment[] comments) {
        this.comments = comments;
    }


}
