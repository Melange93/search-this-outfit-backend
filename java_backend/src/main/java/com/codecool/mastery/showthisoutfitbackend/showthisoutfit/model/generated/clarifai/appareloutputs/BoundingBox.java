package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BoundingBox{

	@JsonProperty("top_row")
	private double topRow;

	@JsonProperty("right_col")
	private double rightCol;

	@JsonProperty("left_col")
	private double leftCol;

	@JsonProperty("bottom_row")
	private double bottomRow;

	@Override
 	public String toString(){
		return 
			"BoundingBox{" + 
			"top_row = '" + topRow + '\'' + 
			",right_col = '" + rightCol + '\'' + 
			",left_col = '" + leftCol + '\'' + 
			",bottom_row = '" + bottomRow + '\'' + 
			"}";
		}
}