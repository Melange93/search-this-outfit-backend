package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChosenItem {
    @JsonProperty("itemName")
    private String itemName;
    @JsonProperty("image")
    private InputsImage base64EncodePicture;
}
