package com.bordza.booking.bordzaBooking.services;


import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;


@Service
public class StorageService {

    private static final Logger log = LoggerFactory.getLogger("test Input");

    private static String UPLOADED_FOLDER = "/home/laetitia/bordza_pictures/images/";

    public String store(@RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            log.info("Storage : file Empty");
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return null;
        }

        try {

            log.info("Storage : Entrée dans Try");

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());


            log.info("Storage/path : " + path);

            Files.write(path, bytes);

            log.info("Storage : " + path.toString());

            return path.toString();

            //redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {

            log.info("Storage : ERREUR");

            e.printStackTrace();
        }
        return "error";
    }

    public void deleteFile(String urlPicture){

        log.info("urlPicture : " + urlPicture);

        try
        {
            Files.deleteIfExists(Paths.get(urlPicture));
        }
        catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }
    }

}
