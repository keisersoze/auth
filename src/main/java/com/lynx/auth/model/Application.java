package com.lynx.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Application")
public class Application extends ApplicationInfo {

    @Id
    @NotNull
    @Size(min=3, max=30)
    @JsonProperty("application_id")
    private String applicationId;

	public Application(@NotNull @Size(min = 3, max = 30) String applicationId,
			@NotNull @Size(min = 8) String secret, List<String> authorities, List<String> authorizedGrantTypes,
			int nRequests, long refreshInterval) {
		super(secret, authorities, authorizedGrantTypes, nRequests, refreshInterval);
		this.applicationId = applicationId;
	}

	public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String clientApplicationId) {
        this.applicationId = clientApplicationId;
    }
    
}

