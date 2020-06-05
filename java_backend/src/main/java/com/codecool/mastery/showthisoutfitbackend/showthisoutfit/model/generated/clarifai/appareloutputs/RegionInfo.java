package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegionInfo{

	@JsonProperty("bounding_box")
	private BoundingBox boundingBox;

	@Override
 	public String toString(){
		return 
			"RegionInfo{" + 
			"bounding_box = '" + boundingBox + '\'' + 
			"}";
		}
}