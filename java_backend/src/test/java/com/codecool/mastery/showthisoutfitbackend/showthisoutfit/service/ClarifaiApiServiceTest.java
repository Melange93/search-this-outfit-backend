package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.util.ClarifaiApiServiceUtil;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClarifaiApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ClarifaiApiServiceUtil clarifaiApiServiceUtil;

    @InjectMocks
    private ClarifaiApiService clarifaiApiService;

    private InputsImage image;

    @BeforeEach
    public void init() {
        image = new InputsImage();
    }

    @Test
    void getImageApparelLabels() {
        BoundingBox boundingBox = new BoundingBox();
        Label label = new Label(Collections.singletonList("test"), boundingBox);
        Set<Label> labels = Sets.newHashSet(label);

        ConceptsItem conceptsItem = new ConceptsItem();
        conceptsItem.setName("test");

        Outputs testOutput = createTestOutput(conceptsItem, boundingBox);

        ResponseEntity<Outputs> responseEntity = new ResponseEntity<>(testOutput, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Outputs>>any())).thenReturn(responseEntity);

        when(clarifaiApiServiceUtil.createLabelSetFromOutputs(testOutput)).thenReturn(labels);

        assertThat(labels).isEqualTo(clarifaiApiService.getImageApparelLabels(image));
    }

    @Test
    void getImageDominantColorTest() {
        ColorOutputs colorOutputsTest = getColorOutputsTest();

        ResponseEntity<ColorOutputs> responseEntity = new ResponseEntity<>(colorOutputsTest, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ColorOutputs>>any())).thenReturn(responseEntity);

        when(clarifaiApiServiceUtil.getHighestValueColorFromColorOutputs(colorOutputsTest)).thenReturn("testColor3");

        assertThat("testColor3").isEqualTo(clarifaiApiService.getImageDominantColor(image));
    }

    private Outputs createTestOutput(ConceptsItem conceptsItem, BoundingBox boundingBox) {

        OutputsData outputsData = new OutputsData();
        outputsData.setConcepts(Collections.singletonList(conceptsItem));

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

        return outputs;
    }

    private ColorOutputs getColorOutputsTest() {
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
        return colorOutputs;
    }
}