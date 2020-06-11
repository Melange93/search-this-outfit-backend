package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.util;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.Inputs;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsData;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsItem;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClarifaiApiServiceUtil {

    private static final String API_KEY = System.getenv("API_KEY");

    public HttpHeaders getCommonHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Key " + API_KEY);

        return httpHeaders;
    }

    public Inputs createApiInputs(InputsImage base64EncodePicture) {
        InputsData data = new InputsData();
        data.setImage(base64EncodePicture);

        InputsItem inputsItem = new InputsItem();
        inputsItem.setData(data);

        Inputs inputs = new Inputs();
        inputs.setInputs(Collections.singletonList(inputsItem));

        return inputs;
    }

    public Set<Label> createLabelSetFromOutputs(Outputs outputs) {
        Set<Label> labels = new HashSet<>();

        List<OutputsItem> outputsItems = outputs.getOutputs();
        for (OutputsItem item : outputsItems) {
            OutputsData itemData = item.getData();
            List<RegionsItem> regions = itemData.getRegions();
            for (RegionsItem regionsItem : regions) {
                RegionInfo regionInfo = regionsItem.getRegionInfo();
                BoundingBox boundingBox = regionInfo.getBoundingBox();

                OutputsData regionsItemData = regionsItem.getData();
                List<ConceptsItem> concepts = regionsItemData.getConcepts();

                List<String> itemNames = new LinkedList<>();
                concepts.forEach(conceptsItem -> itemNames.add(conceptsItem.getName()));

                Label label = new Label(itemNames, boundingBox);
                labels.add(label);
            }
        }

        return labels;
    }

    public String getHighestValueColorFromColorOutputs(ColorOutputs colorOutputs) {
        ColorsItem highestValueColor = ColorsItem.builder().build();

        List<ColorOutputsItem> outputsItems = colorOutputs.getOutputs();
        for (ColorOutputsItem colorOutputsItem : outputsItems) {
            Data data = colorOutputsItem.getData();
            List<ColorsItem> colorList = data.getColors();
            for (ColorsItem colorsItem : colorList) {
                if (colorsItem.getValue() > highestValueColor.getValue()) {
                    highestValueColor = colorsItem;
                }
            }
        }

        W3c highestValueColorW3c = highestValueColor.getW3c();

        return highestValueColorW3c.getName();
    }

}
