package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status{

	@JsonProperty("code")
	private int code;

	@JsonProperty("description")
	private String description;

	public int getCode(){
		return code;
	}

	public String getDescription(){
		return description;
	}
}