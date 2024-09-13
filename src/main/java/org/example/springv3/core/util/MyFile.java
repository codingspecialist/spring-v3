package org.example.springv3.core.util;

import org.example.springv3.core.error.ex.Exception500;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class MyFile {

    public static String 파일저장(MultipartFile file){
        UUID uuid = UUID.randomUUID(); // uuid
        String fileName = uuid+"_"+file.getOriginalFilename(); // 롤링

        Path imageFilePath = Paths.get("./images/"+fileName);
        try {
            Files.write(imageFilePath, file.getBytes());
        } catch (Exception e) {
            throw new Exception500("파일 저장 오류");
        }
        return fileName;
    }
}
