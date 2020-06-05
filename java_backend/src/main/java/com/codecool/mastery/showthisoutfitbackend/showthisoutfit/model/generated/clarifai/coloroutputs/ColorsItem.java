package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorsItem{

	@JsonProperty("raw_hex")
	private String rawHex;

	@JsonProperty("w3c")
	private W3c w3c;

	@JsonProperty("value")
	private double value;

}