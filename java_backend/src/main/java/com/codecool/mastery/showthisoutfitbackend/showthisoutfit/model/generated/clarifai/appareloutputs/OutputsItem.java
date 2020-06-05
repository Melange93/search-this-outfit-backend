package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputsItem{

	@JsonProperty("input")
	private OutputsInput input;

	@JsonProperty("data")
	private OutputsData data;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("model")
	private Model model;

	@JsonProperty("id")
	private String id;

	@JsonProperty("status")
	private Status status;

	@Override
 	public String toString(){
		return 
			"OutputsItem{" + 
			"input = '" + input + '\'' + 
			",data = '" + data + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",model = '" + model + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}