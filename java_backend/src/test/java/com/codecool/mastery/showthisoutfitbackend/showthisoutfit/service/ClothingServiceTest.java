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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class ClothingServiceTest {

    @Mock
    private ClothingRepository clothingRepository;

    @Mock
    private ColorCategorizer colorCategorizer;

    @Mock
    private ClarifaiApiService clarifaiApiService;

    @InjectMocks
    private ClothingService clothingService;

    private InputsImage image;

    @BeforeEach
    void init() {
        image = new InputsImage();
    }

    @Test
    void getTop20ClothingByChosenLabelNameAndColorTest() {
        String clothClassificationENG = "top";
        String subColor = "firebrick";
        String colorGroup = "red";
        int piece = 20;
        Set<Clothing> testClothesSet = testClothesSet(clothClassificationENG, colorGroup, piece);

        ChosenItem item = new ChosenItem(clothClassificationENG, image);

        when(clarifaiApiService.getImageDominantColor(item.getBase64EncodePicture()))
                .thenReturn(subColor);
        when(colorCategorizer.getColorGroupNameFromColorCatalog(subColor))
                .thenReturn(colorGroup);
        when(clothingRepository.findTop20ByClassificationENGAndColor(clothClassificationENG, colorGroup))
                .thenReturn(testClothesSet);

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

    private Set<Clothing> testClothesSet(String clothingClassEng, String clothColor, int clothPiece) {
        Set<Clothing> test = new HashSet<>();
        for (int piece = 0; piece < clothPiece; piece++) {
            test.add(Clothing.builder()
                    .catalogId(Integer.toString(piece))
                    .classificationENG(clothingClassEng)
                    .color(clothColor)
                    .build());
        }
        return test;
    }

}