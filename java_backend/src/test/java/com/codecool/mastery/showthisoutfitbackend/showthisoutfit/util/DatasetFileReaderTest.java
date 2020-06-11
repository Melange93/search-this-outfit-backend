package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DatasetFileReaderTest {

    @Autowired
    private DatasetFileReader datasetFileReader;

    @Test
    void emptyFilePath() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> datasetFileReader.readFile(" "));
    }

    @Test
    void fileNotFound() {
        assertThatExceptionOfType(FileNotFoundException.class)
                .isThrownBy(() -> datasetFileReader.readFile("/test/false/path"));
    }

//    @Test
//    void readLinesFromTestFileListWithoutHeader() throws Exception {
//        List<String> test = new ArrayList<>();
//        test.add("women,dress,dress,női,ruha,ruha,2440415,https://www.fashiondays.hu/v/2440415/n%C5%91i-/ruh%C3%A1zat?productSlug=ema-kiv%C3%A1gott-v%C3%A1ll%C3%BA-ruha-n%C5%91i-diesel&tagId=1,https://www.fashiondays.hu/p/ema-kiv%C3%A1gott-v%C3%A1ll%C3%BA-ruha-n%C5%91i-diesel-p2440415-1/,Ema kivágott vállú ruha,35099,98.28,Diesel,limited_quantity,https://fdcdn.akamaized.net/m/780x1132/products/27714/27713484/images/res_fcab13f48d2eefa00a21c6f95064caf7.jpg?s=vmfbOZiNF4pE https://fdcdn.akamaized.net/m/780x1132/products/27714/27713484/images/res_05bf89f02f0e4f16a752f02e5c738fa4.jpg?s=Xl0otTnuJYf6 https://fdcdn.akamaized.net/m/780x1132/products/27714/27713484/images/res_ddd195dfcf1e2596a3c8674f61b37042.jpg?s=S1sox4_FAiu_ https://fdcdn.akamaized.net/m/780x1132/products/27714/27713484/images/res_fb100cc052e7082db2d9c8e39ac67d93.jpg?s=0g_uEVuN6ga0,fekete,00SM7D-0JASE-9XX,Leírás Szín: fekete Stílus: hétköznapi Anyag: pamut Minta: nincs Szabás: egyenes Hossza: mini Nyakkivágás: kerek Ujjak hossza: hosszú Zsebek: kenguruzseb Összetétel Anyag: 100% pamut Modell információ: A modell S-es méretű terméket visel. Méretei: 176 cm$ mellbőség: 86 cm$ derékbőség: 65 cm$ csípő: 90 cm Termékszám 00SM7D-0JASE-9XX");
//        test.add("women,dress,dress,női,ruha,ruha,2684903,https://www.fashiondays.hu/v/2684903/n%C5%91i-/ruh%C3%A1zat?productSlug=mint%C3%A1s-ingruha-n%C5%91i-sense&tagId=1,https://www.fashiondays.hu/p/mint%C3%A1s-ingruha-n%C5%91i-sense-p2684903-1/,Mintás ingruha,9399,26.32,Sense,in_stock,https://fdcdn.akamaized.net/m/780x1132/products/30001/30000475/images/res_6e768ec428a14a24819976c42a2d8f6a.jpg?s=OFdMVRn9vdrB https://fdcdn.akamaized.net/m/780x1132/products/30001/30000475/images/res_b9600a108b857ff0e87b7790cfe8e8b9.jpg?s=OU5K1VHlauKf https://fdcdn.akamaized.net/m/780x1132/products/30001/30000475/images/res_88193c43c1549583a5a5ef8e83645774.jpg?s=jw9HmvZ6HrTz,fekete?krémszín,CA4823-CREM,Leírás Szín: fekete$ krémszín Stílus: hétköznapi Anyag: szintetikus anyag$ viszkóz Minta: van Szabás: bővülő Hossza: midi Nyakkivágás: V-hasítékkal Ujjak hossza: rövid Részletek: levehető megkötő a derékrészen Rögzítés: gombos Összetétel Anyag: 55% viszkóz$ 45% cupro Bélés: 100% viszkóz Termékszám CA4823-CREM");
//        test.add("women,dress,dress,női,ruha,ruha,2678749,https://www.fashiondays.hu/v/2678749/n%C5%91i-/ruh%C3%A1zat?productSlug=vir%C3%A1gmint%C3%A1s-lentartalm%C3%BA-ruha-n%C5%91i-gap&tagId=1,https://www.fashiondays.hu/p/vir%C3%A1gmint%C3%A1s-lentartalm%C3%BA-ruha-n%C5%91i-gap-p2678749-1/,Virágmintás lentartalmú ruha,15399,43.12,GAP,in_stock,https://fdcdn.akamaized.net/m/780x1132/products/29809/29808732/images/res_d8c4aa6a7664a54f8ccbff0589cb2cee.jpg?s=Y9NyKhML4ahc https://fdcdn.akamaized.net/m/780x1132/products/29809/29808732/images/res_94aa5ea3115e109c98295f63e7067c5c.jpg?s=iR0yTTxJwqjZ https://fdcdn.akamaized.net/m/780x1132/products/29809/29808732/images/res_70a009ba69a4a39053eda7b11916fb49.jpg?s=5YgQFqekU43_ https://fdcdn.akamaized.net/m/780x1132/products/29809/29808732/images/res_8ad568550c913bb2460c1b2c91738a1a.jpg?s=QCIhwkphEjea,fehér,000541670-094,Leírás Szín: fehér Stílus: hétköznapi Anyag: pamut$ lenvászon Minta: virágminta Szabás: bővülő Hossza: mini Nyakkivágás: V alakú Ujjak hossza: rövid Rögzítés: gombos Összetétel Anyag: 55% len$ 45% pamut Bélés: 100% pamut Modellinformáció A modell 4-es (=S) méretű terméket visel$ amerikai méretezés szerint. Méretei: magasság: 180 cm$ mellbőség: 86 cm$ derékbőség: 65 cm$ csípő: 94 cm Termékszám 000541670-094");
//        test.add("women,dress,dress,női,ruha,ruha,2678670,https://www.fashiondays.hu/v/2678670/n%C5%91i-/ruh%C3%A1zat?productSlug=v-nyak%C3%BA-vir%C3%A1gmint%C3%A1s-ruha-n%C5%91i-gap&tagId=1,https://www.fashiondays.hu/p/v-nyak%C3%BA-vir%C3%A1gmint%C3%A1s-ruha-n%C5%91i-gap-p2678670-1/,V-nyakú virágmintás ruha,11499,32.2,GAP,in_stock,https://fdcdn.akamaized.net/m/780x1132/products/29809/29808780/images/res_e754fbc0f5e7b4d4da3194ad5529a057.jpg?s=3BcH5w1kpnux https://fdcdn.akamaized.net/m/780x1132/products/29809/29808780/images/res_c26235fbaed46f1452434a38dd7bff3f.jpg?s=cb1VypEQdIf3 https://fdcdn.akamaized.net/m/780x1132/products/29809/29808780/images/res_fbe81a0a967a457cef8bf95e503a6304.jpg?s=AhMRjVB4SWMV https://fdcdn.akamaized.net/m/780x1132/products/29809/29808780/images/res_55257913725cf55ff0fbe7f4c4ef466c.jpg?s=Cp0cYvh0dBHK,rózsaszín,000541675-193,Leírás Szín: rózsaszín Stílus: hétköznapi Anyag: viszkóz Minta: virágminta Szabás: bővülő Hossza: mini Nyakkivágás: V alakú Pántok: 2 vékony Ujjak hossza: ujjatlan Összetétel Anyag: 100% viszkóz Bélés: 100% poliészter Modellinformáció A modell 4-es (=S) méretű terméket visel$ amerikai méretezés szerint. Méretei: magasság: 180 cm$ mellbőség: 86 cm$ derékbőség: 65 cm$ csípő: 94 cm Termékszám 000541675-193");
//        test.add("women,dress,dress,női,ruha,ruha,2685107,https://www.fashiondays.hu/v/2685107/n%C5%91i-/ruh%C3%A1zat?productSlug=%C3%81tlapol%C3%B3s-miniruha-n%C5%91i-sense&tagId=1,https://www.fashiondays.hu/p/%C3%81tlapol%C3%B3s-miniruha-n%C5%91i-sense-p2685107-1/,Átlapolós miniruha,4399,12.32,Sense,in_stock,https://fdcdn.akamaized.net/m/780x1132/products/30001/30000498/images/res_e303cb3a99aa9469f142776c0934db77.jpg?s=kFFnfe2sgp9Z https://fdcdn.akamaized.net/m/780x1132/products/30001/30000498/images/res_6d26a7b93d510f9ccaf96ed6bf8a2fa5.jpg?s=xX0G38-Nt9Nn https://fdcdn.akamaized.net/m/780x1132/products/30001/30000498/images/res_718199c4564d2b33e562428f69c07a87.jpg?s=-Jov2cYQEmMW,rózsaszín?piros,CA4868-ROZ,Leírás Szín: rózsaszín$ piros Stílus: hétköznapi Anyag: viszkóz$ szintetikus szálak Minta: nincs Fazon: átlapolós Hossza: mini Nyakkivágás: átlapolt Ujjak típusa: ejtett Ujjak hossza: rövid Részletek: rugalmas derékrész Összetétel Anyag: 95% viszkóz$ 5% elasztán Termékszám CA4868-ROZ");
//        List<String> readList = datasetFileReader.readFile("src/main/resources/static/testdata/test.csv");
//        assertThat(test).isEqualTo(readList);
//    }
}
