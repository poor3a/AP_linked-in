package models;

public class Comment
{
    private int id;
    private String email;
    private String text;
    private int post_id;

    public Comment(int id, String email, String text, int post_id) {
        this.id = id;
        this.email = email;
        this.text = text;
        this.post_id = post_id;
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

    public int getPost_id() {
        return post_id;
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

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
