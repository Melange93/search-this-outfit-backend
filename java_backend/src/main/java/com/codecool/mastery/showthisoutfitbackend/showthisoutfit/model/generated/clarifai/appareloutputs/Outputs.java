package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Outputs{

	@JsonProperty("outputs")
	private List<OutputsItem> outputs;

	@JsonProperty("status")
	private Status status;

	@Override
 	public String toString(){
		return 
			"Outputs{" + 
			"outputs = '" + outputs + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}