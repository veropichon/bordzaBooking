package com.bordza.booking.bordzaBooking.services;


import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;


@Service
public class StorageService {

    private static final Logger log = LoggerFactory.getLogger("StorageService");

    Environment env;

    private final Path internalPicturesPath;
    private final String externalPicturesDir;


    public StorageService(@Value("${bordza.pictures.external}") String externalPicturesDir,
                          @Value("${bordza.pictures.path}") String internalPicturesPath,
                          Environment env) {
        this.externalPicturesDir = externalPicturesDir;
        String home = env.getProperty("HOME");
        this.internalPicturesPath = Paths.get(home, internalPicturesPath);
    }

    public String store(MultipartFile file,
                        RedirectAttributes redirectAttributes,
                        UserEntity userEntity) {

        if (file.isEmpty()) {
            log.info("Storage : file Empty");
            return null;
        }

        try {

            log.info("File size : " + file.getSize());


            log.info("Storage : Entrée dans Try");

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            log.info("Storage/path : " + internalPicturesPath);

            Path path = Paths.get(internalPicturesPath.toString() + "/" + userEntity.getUsrId() + "_" + file.getOriginalFilename());

            Files.write(path, bytes);
            //Files.write(internalPicturesPath, bytes, , StandardOpenOption.TRUNCATE_EXISTING);


            log.info("Storage : " + internalPicturesPath.toString());

            //return path.toString();
            return file.getOriginalFilename();

        } catch (IOException e) {

            log.info("Storage : ERREUR");

            e.printStackTrace();
        }
        return "error";
    }

    public void deleteFile(String urlPicture) {

        log.info("urlPicture : " + urlPicture);

        try {
            Files.deleteIfExists(Paths.get(urlPicture));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
    }

    public String getExternalPicturesDir() {
        return externalPicturesDir;
    }
}
