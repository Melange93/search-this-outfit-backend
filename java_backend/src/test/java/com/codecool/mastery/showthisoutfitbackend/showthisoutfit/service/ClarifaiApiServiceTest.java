package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.*;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.util.ClarifaiApiServiceUtil;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClarifaiApiServiceTest {

    private ClarifaiApiService clarifaiApiService;
    private ClarifaiApiServiceUtil apiServiceUtil;
    private RestTemplate restTemplate;
    private InputsImage image;

    @BeforeEach
    public void init() {
        image = new InputsImage();
        apiServiceUtil = new ClarifaiApiServiceUtil();
        restTemplate = mock(RestTemplate.class);
        clarifaiApiService = new ClarifaiApiService(apiServiceUtil, restTemplate);
    }

    @Test
    void getImageApparelLabels() {
        BoundingBox boundingBox = new BoundingBox();
        Label label = new Label(Arrays.asList("test"), boundingBox);
        Set<Label> labels = Sets.newHashSet(label);

        ConceptsItem conceptsItem = new ConceptsItem();
        conceptsItem.setName("test");

        Outputs testOutput = createTestOutput(conceptsItem, boundingBox);

        ResponseEntity<Outputs> responseEntity = new ResponseEntity<>(testOutput, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Outputs>>any())).thenReturn(responseEntity);

        assertThat(labels).isEqualTo(clarifaiApiService.getImageApparelLabels(image));
    }

    @Test
    void getImageDominantColor() {
    }

    private Outputs createTestOutput(ConceptsItem conceptsItem, BoundingBox boundingBox) {

        OutputsData outputsData = new OutputsData();
        outputsData.setConcepts(Arrays.asList(conceptsItem));;

        RegionInfo regionInfo = new RegionInfo();
        regionInfo.setBoundingBox(boundingBox);

        RegionsItem regionsItem = new RegionsItem();
        regionsItem.setData(outputsData);
        regionsItem.setRegionInfo(regionInfo);

        OutputsData data = new OutputsData();
        data.setRegions(Arrays.asList(regionsItem));

        OutputsItem outputsItem = new OutputsItem();
        outputsItem.setData(data);

        Outputs outputs = new Outputs();
        outputs.setOutputs(Arrays.asList(outputsItem));

        return outputs;
    }
}