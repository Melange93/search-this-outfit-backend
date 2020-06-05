package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@NoArgsConstructor
public class WebScraper {
    private static final String FASHIONDAYS_MAIN_PAGE = "https://www.fashiondays.hu/";
    private static final String WOMEN_ALL_CLOTHS = FASHIONDAYS_MAIN_PAGE + "g/női-/ruházat";
    private static final String MEN_ALL_CLOTHS = FASHIONDAYS_MAIN_PAGE + "g/férfi-/ruházat";
    private static final String GIRL_ALL_CLOTHS = FASHIONDAYS_MAIN_PAGE + "g/lány-/ruházat";
    private static final String BOY_ALL_CLOTHS = FASHIONDAYS_MAIN_PAGE + "g/fiú-/ruházat";

    public List<List<Map<String,String>>> startWebScraping() throws IOException, InterruptedException {
        String[] mainTags = {WOMEN_ALL_CLOTHS, MEN_ALL_CLOTHS, GIRL_ALL_CLOTHS, BOY_ALL_CLOTHS};
        List<List<Map<String,String>>> all = new ArrayList<>();


        //all.add(getPageAllProduct(Jsoup.connect(WOMEN_ALL_CLOTHS).get()));

        FileWriter writer = new FileWriter("test.csv");
        writer.append("genderENG");
        writer.append(",");
        writer.append("classificationENG");
        writer.append(",");
        writer.append("subclassificationENG");
        writer.append(",");
        writer.append("genderHUN");
        writer.append(",");
        writer.append("classificationHUN");
        writer.append(",");
        writer.append("subclassificationHUN");

        writer.append(",");
        writer.append("productId");
        writer.append(",");
        writer.append("similarProductsLink");
        writer.append(",");
        writer.append("mainProductsLink");
        writer.append(",");
        writer.append("productName");
        writer.append(",");
        writer.append("priceHUF");
        writer.append(",");
        writer.append("priceEUR");
        writer.append(",");
        writer.append("brand");
        writer.append(",");
        writer.append("stockStatusENG");
        writer.append(",");
        writer.append("images");
        writer.append(",");
        writer.append("color");
        writer.append(",");
        writer.append("catalogId");
        writer.append(",");
        writer.append("productDetails");
        writer.append("\n");
        writer.flush();
        writer.close();

        for (int j = 1; j < 3; j++) {
            all.add(getPageAllProduct(Jsoup.connect("https://www.fashiondays.hu/g/n%C5%91i-/ruh%C3%A1zat-ruha?page=" + j).get()));
            System.out.println(j);
            Thread.sleep(30000);
        }
        System.out.println("Finished");


        return all;
    }

    private int getAllClothPageNumberByTag(Document doc) {
        int pageNavigationBarLength = doc
                .selectFirst(".pagination")
                .select("li").size();

        int prosuctsPageNumber = Integer.parseInt(doc
                .selectFirst(".pagination")
                .select("li")
                .get(pageNavigationBarLength - 2)
                .selectFirst("a")
                .attr("data-page"));
        return Math.min(prosuctsPageNumber, 10);
    }

    private List<Map<String, String>> getPageAllProduct(Document doc) throws IOException {
        FileWriter writer = new FileWriter("test.csv", true);



        Element productsPage = doc.getElementById("products-listing");
        Elements productsLinkList = productsPage.select("ul li");
        List<Map<String, String>> productList = new ArrayList<>();

        for (Element productLink : productsLinkList) {
            writer.append("women");
            writer.append(",");
            writer.append("dress");
            writer.append(",");
            writer.append("dress");
            writer.append(",");
            writer.append("női");
            writer.append(",");
            writer.append("ruha");
            writer.append(",");
            writer.append("ruha");
            writer.append(",");

            writer.append(productLink.attributes().dataset().get("vrecom-productid"));
            writer.append(",");
            writer.append(productLink.attributes().dataset().get("vrecom-url"));
            writer.append(",");
            String fullProductLink = productLink.getElementsByTag("a").attr("href");
            writer.append(fullProductLink.substring(0, fullProductLink.indexOf("?")));
            writer.append(",");
            writer.append(productLink.getElementsByTag("a").attr("title"));
            writer.append(",");
            writer.append(productLink.getElementsByTag("a").attr("data-gtm-price"));
            writer.append(",");
            writer.append(productLink.getElementsByTag("a").attr("data-gtm-price-eur"));
            writer.append(",");
            writer.append(productLink.getElementsByTag("a").attr("data-gtm-brand-name"));
            writer.append(",");
            writer.append(productLink.getElementsByTag("a").attr("data-gtm-status"));
            writer.append(",");

            Document secondDoc = Jsoup.connect(fullProductLink.substring(0, fullProductLink.indexOf("?"))).get();
            Element imageListContainer = secondDoc.getElementById("carousel-thumb");
            Elements images = imageListContainer.select("li");
            StringBuilder imagesLinks = new StringBuilder();
            for (Element image : images) {
                int largeImgLinkStartIndex = image.selectFirst("a").attr("rel").lastIndexOf("https");
                int largeImgLinkEndIndex = image.selectFirst("a").attr("rel").lastIndexOf("'}");

                imagesLinks.append(" ");
                imagesLinks.append(image.selectFirst("a").attr("rel").substring(largeImgLinkStartIndex, largeImgLinkEndIndex));
            }
            writer.append(imagesLinks.toString().trim());
            writer.append(",");

            String pInfo = secondDoc.getElementById("product_details").text();
            int startColor = pInfo.lastIndexOf("Szín: ") + 6;
            String startColorName = pInfo.substring(startColor);
            int endColor = startColorName.lastIndexOf("Stílus");
            if (endColor == -1) {
                endColor = 0;
            }
            String color = startColorName.substring(0, endColor).replaceAll(", ", "?").trim();
            writer.append(color);
            writer.append(",");
            /*
            Elements bar = secondDoc.getElementsByAttribute("itemprop").select("span a span");

            String[] string = {"gederHUN", "class", "subclass", "brandHUn"};

            String hun = "";

            int i = 0;
            for (Element b : bar) {
                if (i == 0) {
                    hun = b.text().replaceAll("\n", " ").trim();
                }
                if (i < 3) {
                    writer.append(b.text().replaceAll("\n", "").replaceAll(",", "").trim());
                    writer.append(",");
                }
                i++;
            }

            String genderENG = getGenderByGenderHUN(hun);
            writer.append(genderENG);
            writer.append(",");

             */
            String productDetails = secondDoc.getElementById("product_details").text().trim();
            int catalogId = productDetails.lastIndexOf("Termékszám") + 10;
            writer.append(productDetails.substring(catalogId).trim());
            writer.append(",");
            writer.append(productDetails.replaceAll(",", "\\$"));

            writer.append("\n");
        }

        writer.flush();
        writer.close();
        return productList;
    }

    private String getGenderByGenderHUN(String genderHUN) {
        switch (genderHUN) {
            case "NŐI":
                return "WOMEN";
            case "FÉRFI":
                return "MAN";
            case "FIÚ":
                return "BOY";
            case "LÁNY":
                return "GIRL";
            default:
                return "UNISEX";
        }
    }

}
