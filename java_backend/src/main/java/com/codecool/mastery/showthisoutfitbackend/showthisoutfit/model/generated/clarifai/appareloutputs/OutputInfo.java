package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputInfo{

	@JsonProperty("output_config")
	private OutputConfig outputConfig;

	@JsonProperty("type_ext")
	private String typeExt;

	@JsonProperty("message")
	private String message;

	@JsonProperty("type")
	private String type;

	public OutputConfig getOutputConfig(){
		return outputConfig;
	}

	public String getTypeExt(){
		return typeExt;
	}

	public String getMessage(){
		return message;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"OutputInfo{" + 
			"output_config = '" + outputConfig + '\'' + 
			",type_ext = '" + typeExt + '\'' + 
			",message = '" + message + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}