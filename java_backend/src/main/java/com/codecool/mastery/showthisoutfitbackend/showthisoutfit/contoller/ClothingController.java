package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.contoller;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.ChosenItem;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin
@RestController
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @PostMapping("/picture/upload")
    public Set<Label> getUploadImageLabels(@RequestBody InputsImage base64Image) {
        return clothingService.getImageLabels(base64Image);
    }

    @PostMapping("/result")
    public Set<Clothing> result(@RequestBody ChosenItem chosenItem) {
        return clothingService.getTop20ClothingByChosenLabelNameAndColor(chosenItem);
    }
}
