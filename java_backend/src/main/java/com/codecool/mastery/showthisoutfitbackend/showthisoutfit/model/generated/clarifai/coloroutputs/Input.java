package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Input{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("id")
	private String id;

	public Data getData(){
		return data;
	}

	public String getId(){
		return id;
	}
}