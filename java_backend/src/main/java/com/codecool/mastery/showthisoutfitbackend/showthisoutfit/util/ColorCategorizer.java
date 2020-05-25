package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@Slf4j
public class ColorCategorizer {

    private DatasetFileReader fileReader;

    private static final int COLOR_NAME = 0;
    private static final int COLOR_GROUP = 1;
    private static final String COLOR_GROUP_FILE = "src/main/resources/static/colors/colors.csv";
    private static final String COLOR_HUN_FILE = "src/main/resources/static/colors/colorsHun.csv";
    private Map<String, String> colorsCatalog;

    public ColorCategorizer(DatasetFileReader fileReader) throws IOException {
        this.fileReader = fileReader;
        this.colorsCatalog = createColorsCatalog();
    }

    private Map<String, String> createColorsCatalog() throws IOException {
        Map<String, String> colorsCatalog = new HashMap<>();
        List<String> colorListRow = getColorsFromFile(COLOR_GROUP_FILE);
        uploadCatalog(colorsCatalog, colorListRow);
        colorsCatalog.put("colorFul", "colorFul");

        List<String> hunColorListRow = getColorsFromFile(COLOR_HUN_FILE);
        uploadCatalog(colorsCatalog, hunColorListRow);
        return colorsCatalog;
    }

    private void uploadCatalog(Map<String, String> catalog, List<String> colorList) {
        for (String row : colorList) {
            String[] rowElement = row.split(",");
            catalog.put(rowElement[COLOR_NAME].toLowerCase(), rowElement[COLOR_GROUP].toLowerCase());
        }
    }

    private List<String> getColorsFromFile(String filePath) throws IOException {
        return fileReader.readFile(filePath);
    }

    public String getColorGroupNameFromColorCatalog(String mainColor) {
        mainColor = mainColor.trim().toLowerCase();
        if (mainColor.isEmpty()) {
            throw new NullPointerException("The color to be categorized is empty.");
        }

        for (String color: colorsCatalog.keySet()) {
            if (color.equals(mainColor)) {
                return colorsCatalog.get(color);
            }
        }
        return "unknown";
    }

}
