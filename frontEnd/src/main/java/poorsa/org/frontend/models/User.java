package poorsa.org.frontend.models;

public class User {
    private String password;
    private String email;
    private String token;

    public User(String username, String password, String email, Stirng token) {
        this.password = password;
        this.email = email;
        this.token = token;
    }

    public User() {
    	
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setToken(String token) {
		this.token = token;
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
