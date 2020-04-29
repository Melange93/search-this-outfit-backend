package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Prediction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Labeler {
    final ClarifaiClient client = new ClarifaiBuilder(System.getenv("API_KEY")).buildSync();

    public List<String> firstTry() {

        ConceptModel model = client.getDefaultModels().apparelModel();
        ClarifaiResponse<List<ClarifaiOutput<Prediction>>> response = client.predict(model.id())
                .withInputs(ClarifaiInput.forImage("https://fdcdn.akamaized.net/m/780x1132/products/29809/29808732/images/res_d8c4aa6a7664a54f8ccbff0589cb2cee.jpg?s=Y9NyKhML4ahc"))
                .withMinValue(0.35)
                .withMaxConcepts(3)
                .executeSync();

        List<String> result = new ArrayList<>();

        for (ClarifaiOutput<Prediction> resp : response.get()) {
            System.out.println(resp);
            for (Prediction r : resp.data()) {
                result.add(r.toString());
            }
        }
        return result;


        /*
        Model<Concept> generalModel = client.getDefaultModels().apparelModel();

        PredictRequest<Concept> request = generalModel.predict().withInputs(
                ClarifaiInput.forImage("https://fdcdn.akamaized.net/m/780x1132/products/29210/29209883/images/res_8f2dd1566bfdfb855d392659f7271d9e.jpg?s=eCGFCVDoJQp5")
        );
        List<ClarifaiOutput<Concept>> result = request.executeSync().get();

        for (ClarifaiOutput<Concept> res: result ){
            System.out.println(res);
            for (Concept r : res.data()) {
                System.out.println(r);
            }
        }

         */


    }
}