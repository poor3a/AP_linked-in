package models;

public class User
{
    private int id;
    private String password;
    private String email;

    public User(int id ,String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
