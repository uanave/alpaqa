package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class BackpackExtractor {
    Optional<Backpack> extractFromMultipartFile(MultipartFile file) {
        ObjectMapper mapper = new ObjectMapper();
        Resource translatedFile = file.getResource();
        InputStream inputStream = null;
        try {
            inputStream = translatedFile.getInputStream();
            Backpack backpack = mapper.readValue(inputStream, Backpack.class);
            return Optional.of(backpack);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
