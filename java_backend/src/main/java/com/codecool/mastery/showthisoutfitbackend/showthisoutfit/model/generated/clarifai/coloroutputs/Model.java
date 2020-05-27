package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model{

	@JsonProperty("model_version")
	private ModelVersion modelVersion;

	@JsonProperty("name")
	private String name;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("output_info")
	private OutputInfo outputInfo;

	@JsonProperty("app_id")
	private Object appId;

	public ModelVersion getModelVersion(){
		return modelVersion;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getId(){
		return id;
	}

	public OutputInfo getOutputInfo(){
		return outputInfo;
	}

	public Object getAppId(){
		return appId;
	}
}