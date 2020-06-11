package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data{

	@JsonProperty("colors")
	private List<ColorsItem> colors;

	@JsonProperty("image")
	private Image image;


}