package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutputsData {

	@JsonProperty("regions")
	private List<RegionsItem> regions;

	@JsonProperty("concepts")
	private List<ConceptsItem> concepts;

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"regions = '" + regions + '\'' + 
			",concepts = '" + concepts + '\'' + 
			"}";
		}
}