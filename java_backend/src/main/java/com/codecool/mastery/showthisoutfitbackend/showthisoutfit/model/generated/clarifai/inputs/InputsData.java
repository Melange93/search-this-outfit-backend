package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputsData {

	@JsonProperty("image")
	private InputsImage image;

	public InputsImage getImage(){
		return image;
	}

	public void setImage(InputsImage image) {
		this.image = image;
	}
}