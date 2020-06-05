package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.ChosenItem;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository.ClothingRepository;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util.ColorCategorizer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private ClarifaiApiService clarifaiApiService;

    @Autowired
    private ColorCategorizer colorCategorizer;

    public Set<Clothing> getTop20ClothingByChosenLabelNameAndColor(ChosenItem chosenItem) {
        String color = clarifaiApiService.getImageDominantColor(chosenItem.getBase64EncodePicture());
        String colorGroup = colorCategorizer.getColorGroupNameFromColorCatalog(color);
        System.out.println(color);
        System.out.println(colorGroup);
        return clothingRepository.findTop20ByClassificationENGAndColor(chosenItem.getItemName(), colorGroup);
    }

    public Set<Label> getImageLabels(InputsImage base64Image) {
        return clarifaiApiService.getImageApparelLabels(base64Image);
    }

}
