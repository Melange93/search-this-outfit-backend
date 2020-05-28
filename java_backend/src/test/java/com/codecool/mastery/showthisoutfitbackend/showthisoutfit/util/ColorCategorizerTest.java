package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class ColorCategorizerTest {

    private DatasetFileReader datasetFileReader = new DatasetFileReader();

    private ColorCategorizer colorCategorizer = new ColorCategorizer(datasetFileReader);

    ColorCategorizerTest() throws IOException {
    }

    @Test
    void getColorGroupNameFromColorCatalogTestValidInput() {
        assertThat("gray").isEqualTo(colorCategorizer.getColorGroupNameFromColorCatalog("fekete"));
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