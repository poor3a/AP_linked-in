package poorsa.org.frontend.models;

public class User {
    private String username;
    private String password;
    private String email;
    private String token;

    public User(String username, String password, String email, Stirng token) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getToken() {
		return token;
	}
}
