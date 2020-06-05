package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.respository;

import com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClothingRepository extends JpaRepository<Clothing, Long> {

    Set<Clothing> findTop20ByClassificationENGAndColor(String classificationENG, String color);

}
