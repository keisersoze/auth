package auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Application")
public class Application extends ApplicationInfo {

    @Id
    @NotNull
    @Size(min=3, max=30)
    private String applicationId;

	public Application(@NotNull @Size(min = 3, max = 30) String applicationId,
			@NotNull @Size(min = 8) String secret, List<String> authorities, List<String> authorizedGrantTypes) {
		super(secret, authorities, authorizedGrantTypes);
		this.applicationId = applicationId;
	}

	public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String clientApplicationId) {
        this.applicationId = clientApplicationId;
    }
    
}

