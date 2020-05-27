package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelVersion{

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private Status status;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public Status getStatus(){
		return status;
	}
}