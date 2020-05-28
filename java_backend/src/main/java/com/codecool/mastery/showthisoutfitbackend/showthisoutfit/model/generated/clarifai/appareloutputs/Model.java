package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

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

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("app_id")
	private String appId;

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

	public String getDisplayName(){
		return displayName;
	}

	public String getAppId(){
		return appId;
	}

	@Override
 	public String toString(){
		return 
			"Model{" + 
			"model_version = '" + modelVersion + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",output_info = '" + outputInfo + '\'' + 
			",display_name = '" + displayName + '\'' + 
			",app_id = '" + appId + '\'' + 
			"}";
		}
}