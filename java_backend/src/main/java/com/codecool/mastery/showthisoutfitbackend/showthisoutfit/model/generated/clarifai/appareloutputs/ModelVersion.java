package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModelVersion{

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private Status status;

	@JsonProperty("worker_id")
	private String workerId;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public Status getStatus(){
		return status;
	}

	public String getWorkerId(){
		return workerId;
	}

	@Override
 	public String toString(){
		return 
			"ModelVersion{" + 
			"created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			",worker_id = '" + workerId + '\'' + 
			"}";
		}
}