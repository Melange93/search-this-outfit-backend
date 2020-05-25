package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.ChosenItem;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository.ClothingRepository;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util.ColorCategorizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
class ClothingServiceTest {

    @Autowired
    private ClothingRepository clothingRepository;

    private ColorCategorizer colorCategorizer;
    private ClarifaiApiService clarifaiApiService;
    private ClothingService clothingService;
    private InputsImage image;

    @BeforeEach
    public void init() {
        colorCategorizer = mock(ColorCategorizer.class);
        clarifaiApiService = mock(ClarifaiApiService.class);
        clothingService = new ClothingService(clothingRepository, clarifaiApiService, colorCategorizer);
        image = new InputsImage();
    }

    @Test
    public void getTop20ClothingByChosenLabelNameAndColorTest() {
        String clothClassificationENG = "top";
        String subColor = "firebrick";
        String colorGroup = "red";
        int piece = 20;
        uploadDateBase(clothClassificationENG, colorGroup, piece);

        ChosenItem item = new ChosenItem(clothClassificationENG, image);
        when(clarifaiApiService.getImageDominantColor(item.getBase64EncodePicture())).thenReturn(subColor);
        when(colorCategorizer.getColorGroupNameFromColorCatalog(subColor)).thenReturn(colorGroup);

        Set<Clothing> clothingSet = clothingService.getTop20ClothingByChosenLabelNameAndColor(item);
        assertThat(clothingSet)
                .hasSize(20)
                .allMatch(clothing ->
                        colorGroup.equals(clothing.getColor())
                                && clothClassificationENG.equals(clothing.getClassificationENG()));
    }

    private void uploadDateBase(String clothingClassEng, String clothColor, int clothPiece) {
        for (int piece = 0; piece < clothPiece; piece++) {
            clothingRepository.save(Clothing.builder()
                    .classificationENG(clothingClassEng)
                    .color(clothColor)
                    .build());
        }
    }

}