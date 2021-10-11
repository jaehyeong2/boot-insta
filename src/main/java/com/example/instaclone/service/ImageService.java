package com.example.instaclone.service;

import com.example.instaclone.config.auth.PrincipalDetails;
import com.example.instaclone.domian.image.Image;
import com.example.instaclone.domian.image.ImageRepository;
import com.example.instaclone.dto.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

//    @Value("${file.path}")
    private String uploadFolder = "C:/Users/wogud2/upload/";

    public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch (Exception e){
            e.printStackTrace();
        }

        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);
    }
}
