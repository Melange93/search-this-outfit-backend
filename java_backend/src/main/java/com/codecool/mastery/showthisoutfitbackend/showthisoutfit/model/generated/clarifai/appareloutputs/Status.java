package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status{

	@JsonProperty("req_id")
	private String reqId;

	@JsonProperty("code")
	private int code;

	@JsonProperty("description")
	private String description;

	public String getReqId(){
		return reqId;
	}

	public int getCode(){
		return code;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"Status{" + 
			"req_id = '" + reqId + '\'' + 
			",code = '" + code + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}