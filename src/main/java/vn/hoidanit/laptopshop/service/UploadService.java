package vn.hoidanit.laptopshop.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

import java.io.*;

@Service
public class UploadService {
    private final ServletContext servletContext;
        public UploadService(ServletContext servletContext) {
           this.servletContext = servletContext;
        }
    public String handleSaveUploadFile(MultipartFile file , String targetFolder){
                    // relative paths : absolute paths
        String finalName = "";
        String rootPath = this.servletContext.getRealPath("/resources/images");
        try {
            byte[] bytes = file.getBytes();
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
            dir.mkdirs();
            // Create the file on server
             finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalName;
    }
}
