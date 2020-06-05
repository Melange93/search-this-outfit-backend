package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.ImageLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ImageLinkRepositoryTest {

    @Autowired
    private ImageLinkRepository imageLinkRepository;

    @Autowired
    private ClothingRepository clothingRepository;

    @Test
    void saveOneSimple() {
        ImageLink test = ImageLink.builder().url("test.com").build();
        imageLinkRepository.save(test);

        List<ImageLink> all = imageLinkRepository.findAll();

        assertThat(all)
                .hasSize(1)
                .allMatch(imageLink -> imageLink.equals(test));
    }

    @Test
    void fieldSave() {
        String testUrl = "test.com";
        ImageLink test = ImageLink.builder().url(testUrl).build();
        imageLinkRepository.save(test);

        List<ImageLink> all = imageLinkRepository.findAll();

        assertThat(all)
                .hasSize(1)
                .allMatch(imageLink -> imageLink.getUrl().equals(testUrl));

    }

    @Test
    void clothingConnection() {
        Clothing clothing = new Clothing();
        ImageLink test1 = ImageLink.builder().url("test1.com").clothing(clothing).build();
        ImageLink test2 = ImageLink.builder().url("test2.com").clothing(clothing).build();
        ImageLink test3 = ImageLink.builder().url("test3.com").clothing(clothing).build();

        clothing.setImages(Arrays.asList(test1, test2, test3));

        clothingRepository.save(clothing);
        imageLinkRepository.saveAll(Arrays.asList(test1, test2, test3));

        List<ImageLink> all = imageLinkRepository.findAll();

        assertThat(all)
                .hasSize(3)
                .allMatch(imageLink -> imageLink.getClothing().equals(clothing));

    }

}