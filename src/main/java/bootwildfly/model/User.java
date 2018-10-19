package bootwildfly.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "User")
public class User{

    @Id
    private String username;

    private String password;

    private List<String> authorities;


    public User() {
        authorities = new ArrayList<>();
    }

    public User(User userDetails){
        this.setUsername(userDetails.getUsername());
        this.setPassword(userDetails.getPassword());
        this.setAuthorities(userDetails.getAuthorities());
    }

    public User(String username, String password, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
