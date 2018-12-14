package com.lynx.auth.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Application")
public class Application extends ApplicationInfo {

    @Id
    @NotNull
    @Size(min=3, max=30)
    @JsonProperty("application_id")
    private String applicationId;

    @JsonCreator
    @PersistenceConstructor
	public Application(String applicationId, String secret, List<String> authorities, List<String> authorizedGrantTypes,
			Integer numRequests, Long refreshInterval) {
		super(secret, authorities, authorizedGrantTypes, numRequests, refreshInterval);
		this.applicationId = applicationId;
	}
	
	public Application (String applicationId, ApplicationInfo appInfo) {
		super(appInfo.getSecret(),appInfo.getAuthorities(),appInfo.getAuthorizedGrantTypes(),
				appInfo.getNumRequests(),appInfo.getRefreshInterval());
		this.applicationId = applicationId;
	}

	public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String clientApplicationId) {
        this.applicationId = clientApplicationId;
    }

}

