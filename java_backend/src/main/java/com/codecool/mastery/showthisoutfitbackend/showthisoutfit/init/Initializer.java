package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.init;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.ImageLink;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository.ClothingRepository;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository.ImageLinkRepository;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util.ColorCategorizer;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util.DatasetFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
@Profile("production")
public class Initializer {

    private ClothingRepository clothingRepository;
    private ImageLinkRepository imageLinkRepository;
    private DatasetFileReader datasetFileReader;
    private ColorCategorizer colorChanger;

    private static final String DATASET_MAIN_FOLDER_PATH = "src/main/resources/static/databasedata/";
    private String[] datasetFileNames = {"women_dress.csv",
            "women_jeans.csv",
            "women_skirt.csv",
            "women_tshirt.csv"};

    private static final int GENDER_ENG = 0;
    private static final int CLASSIFICATION_ENG = 1;
    private static final int SUB_CLASSIFICATION_ENG = 2;
    private static final int GENDER_HUN = 3;
    private static final int CLASSIFICATION_HUN = 4;
    private static final int SUB_CLASSIFICATION_HUN = 5;
    private static final int FASHION_DAYS_PRODUCT_ID = 6;
    private static final int SIMILAR_PRODUCT_LINK = 7;
    private static final int MAIN_PRODUCT_LINK = 8;
    private static final int FASHION_DAYS_PRODUCT_NAME = 9;
    private static final int PRICE_HUN = 10;
    private static final int PRICE_EUR = 11;
    private static final int BRAND = 12;
    private static final int STOCK_STATUS_ENG = 13;
    private static final int IMAGES = 14;
    private static final int COLORS = 15;
    private static final int MAIN_COLOR = 0;
    private static final int CATALOG_ID = 16;
    private static final int PRODUCT_DETAILS = 17;

    public Initializer(ClothingRepository clothingRepository, ImageLinkRepository imageLinkRepository, DatasetFileReader datasetFileReader, ColorCategorizer colorChanger) {
        this.clothingRepository = clothingRepository;
        this.imageLinkRepository = imageLinkRepository;
        this.datasetFileReader = datasetFileReader;
        this.colorChanger = colorChanger;
    }

    @Bean
    public CommandLineRunner afterInit() {

        return args -> {
            for (String fileName : datasetFileNames) {
                List<String> fileRows = datasetFileReader.readFile(DATASET_MAIN_FOLDER_PATH + fileName);
                for (String row : fileRows) {
                    String[] result = row.split(",");
                    Clothing clothing = new Clothing();
                    clothingRepository.save(clothing);

                    clothing.setGenderENG(result[GENDER_ENG]);
                    clothing.setClassificationENG(result[CLASSIFICATION_ENG]);
                    clothing.setSubclassificationENG(result[SUB_CLASSIFICATION_ENG]);
                    clothing.setGenderHUN(result[GENDER_HUN]);
                    clothing.setClassificationHUN(result[CLASSIFICATION_HUN]);
                    clothing.setSubclassificationHUN(result[SUB_CLASSIFICATION_HUN]);
                    clothing.setFashionDaysProductId(Long.parseLong(result[FASHION_DAYS_PRODUCT_ID]));
                    clothing.setSimilarProductsLink(result[SIMILAR_PRODUCT_LINK]);
                    clothing.setMainProductLink(result[MAIN_PRODUCT_LINK]);
                    clothing.setFashionDaysProductName(result[FASHION_DAYS_PRODUCT_NAME]);
                    clothing.setPriceHUF(Long.parseLong(result[PRICE_HUN]));
                    clothing.setPriceEUR(Float.parseFloat(result[PRICE_EUR]));
                    clothing.setBrand(result[BRAND]);
                    clothing.setStockStatusENG(result[STOCK_STATUS_ENG]);

                    List<ImageLink> imageLinks = new LinkedList<>();
                    String[] imgs = result[IMAGES].split(" ");
                    for (String i : imgs) {
                        imageLinks.add(ImageLink.builder()
                                .url(i)
                                .clothing(clothing)
                                .build());
                    }

                    clothing.setImages(imageLinks);
                    imageLinkRepository.saveAll(imageLinks);

                    String[] cols = result[COLORS].split("\\?");
                    clothing.setColor(colorChanger.getColorGroupNameFromColorCatalog(cols[MAIN_COLOR]));
                    clothing.setCatalogId(result[CATALOG_ID]);
                    clothing.setProductDetails(result[PRODUCT_DETAILS].replaceAll("\\$", ", "));

                    clothingRepository.save(clothing);
                }
            }
            log.info("The database upload is completed.");
        };
    }
}
