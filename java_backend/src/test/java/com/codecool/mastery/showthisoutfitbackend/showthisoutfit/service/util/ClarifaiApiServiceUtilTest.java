package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.util;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.Inputs;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsData;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsItem;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ClarifaiApiServiceUtilTest {

    private ClarifaiApiServiceUtil util = new ClarifaiApiServiceUtil();

    @Test
    void getCommonHeadersTestContentTypeApplicationJson() {
        HttpHeaders test = util.getCommonHeaders();
        assertThat(test.getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }

    @Test
    void getCommonHeadersTestSetApiKey() {
        HttpHeaders test = util.getCommonHeaders();
        List<String> testList = new LinkedList<>();
        testList.add("Key " + System.getenv("API_KEY"));
        assertThat(test.get("Authorization")).isEqualTo(testList);
    }

    @Test
    void createApiInputsTest() {
        InputsImage testInputImage = new InputsImage();
        testInputImage.setBase64("base64");
        Inputs apiInputs = util.createApiInputs(testInputImage);
        List<InputsItem> inputs = apiInputs.getInputs();
        assertThat(inputs.stream()
                .map(InputsItem::getData)
                .map(InputsData::getImage)
                .map(InputsImage::getBase64))
                .allMatch(s -> s.equals("base64"));
    }

    @Test
    void createLabelSetFromOutputsTest() {
        ConceptsItem conceptsItem = new ConceptsItem();
        conceptsItem.setName("test");

        OutputsData outputsData = new OutputsData();
        outputsData.setConcepts(Collections.singletonList(conceptsItem));

        BoundingBox boundingBox = new BoundingBox();
        boundingBox.setLeftCol(0.1);
        boundingBox.setRightCol(0.2);
        boundingBox.setTopRow(0.3);
        boundingBox.setBottomRow(0.4);

        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setBoundingBox(boundingBox);

        RegionsItem regionsItem = new RegionsItem();
        regionsItem.setData(outputsData);
        regionsItem.setRegionInfo(regionInfo);

        OutputsData data = new OutputsData();
        data.setRegions(Collections.singletonList(regionsItem));

        OutputsItem outputsItem = new OutputsItem();
        outputsItem.setData(data);

        Outputs outputs = new Outputs();
        outputs.setOutputs(Collections.singletonList(outputsItem));

        Label label = new Label(Collections.singletonList("test"), boundingBox);
        Set<Label> labels = Sets.newHashSet(label);

        assertThat(labels).isEqualTo(util.createLabelSetFromOutputs(outputs));
    }

    @Test
    void getHighestValueColorFromColorOutputsTest() {
        W3c color1 = new W3c();
        color1.setName("testColor1");
        W3c color2 = new W3c();
        color2.setName("testColor2");
        W3c color3 = new W3c();
        color3.setName("testColor3");

        Data data = Data.builder()
                .colors(Arrays.asList(
                        ColorsItem
                                .builder()
                                .w3c(color1)
                                .value(1)
                                .build(),
                        ColorsItem
                                .builder()
                                .w3c(color2)
                                .value(2)
                                .build(),
                        ColorsItem
                                .builder()
                                .w3c(color3)
                                .value(3)
                                .build()
                        )
                )
                .build();

        ColorOutputsItem item = new ColorOutputsItem();
        item.setData(data);
        ColorOutputs colorOutputs = new ColorOutputs();
        colorOutputs.setOutputs(Collections.singletonList(item));

        assertThat("testColor3").isEqualTo(util.getHighestValueColorFromColorOutputs(colorOutputs));
    }
}