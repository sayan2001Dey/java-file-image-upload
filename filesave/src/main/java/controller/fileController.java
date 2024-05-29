package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Image;
import repo.ImageRepository;

import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class fileController {
    
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Define the folder path where you want to save the image
            String folderPath = "C:\\Users\\BMH\\Desktop\\image";

            // Create the directory if it doesn't exist
            File directory = new File(folderPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Create parent directories if necessary
            }

            // Save the file to the folder
            String fileName = file.getOriginalFilename();
            String filePath = folderPath + File.separator + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            // Save the folder path to the database
            Image image = new Image();
            image.setPath(filePath);
            image.setFilename(fileName);
            imageRepository.save(image);

            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}
