package vn.vnpt.hdg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vnpt.hdg.api.configs.FileConfig;
import vn.vnpt.hdg.api.models.UploadFile;
import vn.vnpt.hdg.api.payload.response.UploadDTO;
import vn.vnpt.hdg.api.services.UploadServices;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/uploaded-files")
public class UploadController {
	
    private final UploadServices sUpload;
    private final FileConfig fileConfig;
    @Value("${fileserver.rootFolderDev}")
    private String rootFolderDev; 

    @Autowired
    public UploadController(UploadServices sUpload, FileConfig fileConfig) {
        this.sUpload = sUpload;
        this.fileConfig = fileConfig;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute UploadDTO uploadDto) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("Tệp tải lên rỗng.", HttpStatus.BAD_REQUEST);
            }
            String rootFolder = fileConfig.getRootFolderDev();
            Path storagePath = Paths.get(rootFolder);
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
                }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(fileName).toLowerCase();
            
            if (!isValidFileExtension(fileExtension)) {
                return new ResponseEntity<>("Phần mở rộng tệp không hợp lệ.", HttpStatus.BAD_REQUEST);
            }
                    
            Path uploadPath = Paths.get(rootFolderDev);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            Path filePath = storagePath.resolve(fileName);
            Files.write(filePath, file.getBytes());
            String fileId = sUpload.uploadFile(file);
          
            return new ResponseEntity<>("Tải lên thành công. File ID là: " + fileId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Tải Lên FIle Thất Sờ Bại: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isValidFileExtension(String fileExtension) {
        List<String> validExtensions = Arrays.asList("pdf", "docx", "xlsx");

        return validExtensions.contains(fileExtension);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        try {
            UploadFile uploadedFile = sUpload.downloadFile(id);

            if (uploadedFile != null) {
                ByteArrayResource resource = new ByteArrayResource(uploadedFile.getData());

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", uploadedFile.getFileName());

                return ResponseEntity.ok().headers(headers).body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
