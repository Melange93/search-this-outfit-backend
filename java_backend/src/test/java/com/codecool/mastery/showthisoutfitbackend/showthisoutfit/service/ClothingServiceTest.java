package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.service;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.ChosenItem;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.Label;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.appareloutputs.BoundingBox;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.model.generated.clarifai.inputs.InputsImage;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository.ClothingRepository;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util.ColorCategorizer;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
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
    void init() {
        colorCategorizer = mock(ColorCategorizer.class);
        clarifaiApiService = mock(ClarifaiApiService.class);
        clothingService = new ClothingService(clothingRepository, clarifaiApiService, colorCategorizer);
        image = new InputsImage();
    }

    @Test
    void getTop20ClothingByChosenLabelNameAndColorTest() {
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

    @Test
    void getImageLabelsTest() {
        Label label1 = new Label(Collections.singletonList("top"), new BoundingBox());
        Label label2 = new Label(Collections.singletonList("pants"), new BoundingBox());
        Label label3 = new Label(Collections.singletonList("dress"), new BoundingBox());
        Set<Label> labels = Sets.newHashSet(label1, label2, label3);

        when(clarifaiApiService.getImageApparelLabels(image)).thenReturn(labels);

        assertThat(labels).isEqualTo(clothingService.getImageLabels(image));

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