package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.outputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image{

	@JsonProperty("base64")
	private String base64;

	@JsonProperty("url")
	private String url;

	public String getBase64(){
		return base64;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"base64 = '" + base64 + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}