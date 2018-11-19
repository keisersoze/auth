package auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Application")
public class Application {

    @Id
    private String applicationId;
    private String secret;
    private List<String> authorities;

    public Application() {

    }

    public Application(String clientApplicationId, String clientSecret, List<String> authorities) {
        this.applicationId = clientApplicationId;
        this.secret = clientSecret;
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getSecret() {
        return secret;
    }

    public void setApplicationId(String clientApplicationId) {
        this.applicationId = clientApplicationId;
    }

    public void setSecret(String clientSecret) {
        this.secret = clientSecret;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

