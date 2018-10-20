package bootwildfly.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ClientApplication")
public class ClientApplication {

    @Id
    private String clientId;
    private String clientSecret;
    private List<String> authorities;

    public ClientApplication() {

    }

    public ClientApplication(String clientId, String clientSecret, List<String> authorities) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

