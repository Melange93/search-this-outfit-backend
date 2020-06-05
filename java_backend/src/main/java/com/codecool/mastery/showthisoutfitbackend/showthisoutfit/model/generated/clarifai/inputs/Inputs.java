package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Inputs{

	@JsonProperty("inputs")
	private List<InputsItem> inputs;

	public List<InputsItem> getInputs(){
		return inputs;
	}

	public void setInputs(List<InputsItem> inputs) {
		this.inputs = inputs;
	}
}