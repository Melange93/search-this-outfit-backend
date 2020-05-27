package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputConfig{

	@JsonProperty("closed_environment")
	private boolean closedEnvironment;

	@JsonProperty("min_value")
	private int minValue;

	@JsonProperty("concepts_mutually_exclusive")
	private boolean conceptsMutuallyExclusive;

	@JsonProperty("test_split_percent")
	private int testSplitPercent;

	@JsonProperty("max_concepts")
	private int maxConcepts;

	public boolean isClosedEnvironment(){
		return closedEnvironment;
	}

	public int getMinValue(){
		return minValue;
	}

	public boolean isConceptsMutuallyExclusive(){
		return conceptsMutuallyExclusive;
	}

	public int getTestSplitPercent(){
		return testSplitPercent;
	}

	public int getMaxConcepts(){
		return maxConcepts;
	}

	@Override
 	public String toString(){
		return 
			"OutputConfig{" + 
			"closed_environment = '" + closedEnvironment + '\'' + 
			",min_value = '" + minValue + '\'' + 
			",concepts_mutually_exclusive = '" + conceptsMutuallyExclusive + '\'' + 
			",test_split_percent = '" + testSplitPercent + '\'' + 
			",max_concepts = '" + maxConcepts + '\'' + 
			"}";
		}
}