package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputsImage {

	@JsonProperty("base64")
	private String base64;

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
}