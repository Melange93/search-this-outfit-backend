package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.ImageLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ImageLinkRepositoryTest {

    @Autowired
    private ImageLinkRepository imageLinkRepository;

    @Test
    void saveOneSimple() {
        ImageLink test = ImageLink.builder().url("test.com").build();
        imageLinkRepository.save(test);

        List<ImageLink> all = imageLinkRepository.findAll();

        assertThat(all)
                .hasSize(1)
                .allMatch(imageLink -> imageLink.equals(test));
    }

}