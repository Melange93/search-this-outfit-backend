package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ColorOutputs {

	@JsonProperty("outputs")
	private List<ColorOutputsItem> outputs;

	@JsonProperty("status")
	private Status status;

}