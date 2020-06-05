package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputsItem{

	@JsonProperty("data")
	private InputsData data;

	public InputsData getData(){
		return data;
	}

	public void setData(InputsData data) {
		this.data = data;
	}
}