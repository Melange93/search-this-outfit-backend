package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ColorCategorizerTest {

    @Autowired
    private ColorCategorizer colorCategorizer;

    @Test
    void getColorGroupNameFromColorCatalogTestValidInput() {
        assertThat("black").isEqualTo(colorCategorizer.getColorGroupNameFromColorCatalog("fekete"));
    }

    @Test
    void getColorGroupNameFromColorCatalogTestValidInputButNotInTheCategory() {
        assertThat("unknown").isEqualTo(colorCategorizer.getColorGroupNameFromColorCatalog("something"));
    }

    @Test
    void getColorGroupNameFromColorCatalogyTestInvalidInputEmptyString() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> colorCategorizer.getColorGroupNameFromColorCatalog(""));
    }

    @Test
    public void getColorGroupNameFromColorCatalogTestInvalidInputWhiteSpace() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> colorCategorizer.getColorGroupNameFromColorCatalog(" "));
    }

}