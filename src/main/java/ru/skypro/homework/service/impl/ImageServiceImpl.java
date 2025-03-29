package ru.skypro.homework.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Data
public class ImageServiceImpl {


    public ResponseEntity<byte[]> getImageAsBytes;

    private UserRepository userRepository;
    private AdRepository adRepository;

    @Value("${image.path}")
    private String path;

    public ImageServiceImpl(UserRepository userRepository, AdRepository adRepository) {
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    public String updateImage(MultipartFile image) {


        Path imagePath = Paths.get(path);
        try {
            createDirectoryIfNotExist(); //  create directory with replacing old image
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imageName = UUID.randomUUID() + "-" + image.getOriginalFilename(); //create name for new image

        Path filePath = Path.of(imagePath.toString(), imageName); // create final path with created image name
        try {
            image.transferTo(filePath); // copy image to created directory with replacing old image
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }

    public void deleteUserImage(User user) {
        Path imagePath = Paths.get(path);
        Path finalImagePath = Path.of(imagePath.toString(), user.getImage());
        try {
            Files.deleteIfExists(finalImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdImage(Ad ad) {
        Path imagePath = Paths.get(path);
        Path finalImagePath = Path.of(imagePath.toString(), ad.getImage());
        ;
        try {
            Files.deleteIfExists(finalImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getUserImage(User user) throws IOException {
        Path imagePath = Paths.get(path, userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getEmail())).getImage());
        return Files.readAllBytes(imagePath);
    }

    public byte[] getAdImage(Ad ad) throws IOException {
        Path imagePath = Paths.get(path, ad.getImage());
        return Files.readAllBytes(imagePath);
    }

    private void createDirectoryIfNotExist() throws IOException {
        if (Files.notExists(Paths.get(path))) {
            Files.createDirectory(Paths.get(path));
        }
    }

}