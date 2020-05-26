package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.ChosenItem;
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
        return clothingRepository.findTop20ByClassificationENGAndColor(chosenItem.getItemName(), colorGroup);
    }

}
