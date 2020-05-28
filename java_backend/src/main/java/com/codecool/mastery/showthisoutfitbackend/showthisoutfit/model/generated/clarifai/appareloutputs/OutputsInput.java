package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputsInput {

	@JsonProperty("data")
	private OutputsData data;

	@JsonProperty("id")
	private String id;

	public OutputsData getData(){
		return data;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Input{" + 
			"data = '" + data + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}