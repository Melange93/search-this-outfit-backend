package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.coloroutputs.ColorOutputs;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.Inputs;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.Outputs;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.util.ClarifaiApiServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Set;

@Service
public class ClarifaiApiService {

    @Autowired
    private ClarifaiApiServiceUtil apiServiceUtil;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clarifaiapiservice.apparel.url}")
    private String clarifaiApparelDetection;

    @Value("${clarifaiapiservice.colordetection.url}")
    private String clarifaiColorDetection;

    public Set<Label> getImageApparelLabels(InputsImage base64EncodePicture) {
        HttpHeaders commonHeaders = apiServiceUtil.getCommonHeaders();
        Inputs inputs = apiServiceUtil.createApiInputs(base64EncodePicture);
        HttpEntity<Object> requestEntity = new HttpEntity<>(inputs, commonHeaders);

        ResponseEntity<Outputs> outputsResponseEntity =
                restTemplate.exchange(
                        clarifaiApparelDetection,
                        HttpMethod.POST,
                        requestEntity,
                        Outputs.class);

        return apiServiceUtil.createLabelSetFromOutputs(Objects.requireNonNull(outputsResponseEntity.getBody()));
    }

    public String getImageDominantColor(InputsImage base64EncodePicture) {
        HttpHeaders commonHeaders = apiServiceUtil.getCommonHeaders();
        Inputs inputs = apiServiceUtil.createApiInputs(base64EncodePicture);
        HttpEntity<Object> requestEntity = new HttpEntity<>(inputs, commonHeaders);

        ResponseEntity<ColorOutputs> outputsResponseEntity =
                restTemplate.exchange(
                        clarifaiColorDetection,
                        HttpMethod.POST,
                        requestEntity,
                        ColorOutputs.class);

        return apiServiceUtil.getHighestValueColorFromColorOutputs(Objects.requireNonNull(outputsResponseEntity.getBody()));
    }

}
