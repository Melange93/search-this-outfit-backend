package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConceptsItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("value")
	private double value;

	@JsonProperty("app_id")
	private String appId;

	@Override
 	public String toString(){
		return 
			"ConceptsItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",value = '" + value + '\'' + 
			",app_id = '" + appId + '\'' + 
			"}";
		}
}