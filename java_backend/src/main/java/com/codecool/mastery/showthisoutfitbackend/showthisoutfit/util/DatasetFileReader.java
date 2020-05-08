package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class DatasetFileReader {

    public List<String> readFile(String filePath) {
        List<String> result = new LinkedList<>();
        String row = "";
        int headerRow = 1;
        int rowCounter = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((row = bufferedReader.readLine()) != null) {
                rowCounter++;
                if (rowCounter == headerRow) {
                    continue;
                }
                result.add(row);
            }

        } catch (FileNotFoundException e) {
            log.error("File path not found: " + filePath + ", " + e);
        } catch (IOException e) {
            log.error("File reading failed: " + e);
        }

        return result;
    }

}
