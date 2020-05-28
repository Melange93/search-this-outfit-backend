package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputInfo{

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private String type;

	public String getMessage(){
		return message;
	}

	public String getType(){
		return type;
	}
}