package auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Application")
public class Application {

    @Id
    private String clientApplicationId;
    private String clientSecret;
    private List<String> authorities;

    public Application() {

    }

    public Application(String clientApplicationId, String clientSecret, List<String> authorities) {
        this.clientApplicationId = clientApplicationId;
        this.clientSecret = clientSecret;
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getClientApplicationId() {
        return clientApplicationId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientApplicationId(String clientApplicationId) {
        this.clientApplicationId = clientApplicationId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

