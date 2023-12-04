package vn.vnpt.hdg.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import vn.vnpt.hdg.api.configs.FileConfig;
import vn.vnpt.hdg.api.models.UploadFile;
import vn.vnpt.hdg.api.repository.UploadRepository;
import vn.vnpt.hdg.api.services.UploadServices;
import vn.vnpt.hdg.api.utils.EncryptUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadServices {
	
	private final UploadRepository rUpload;
    private final FileConfig fileConfig; 

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileId = UUID.randomUUID().toString();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String tenFileHash = EncryptUtils.md5(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(fileName).toLowerCase();
        String rootFolder = fileConfig.getRootFolderDev();
        String fileUrlDev = fileConfig.getFileUrlDev();
        
        UploadFile uploadedFile = new UploadFile();
        uploadedFile.setFileName(fileName);
        uploadedFile.setFileSize(file.getSize());
        uploadedFile.setFileType(file.getContentType());
        uploadedFile.setUploadDate(new Date());
        uploadedFile.setNhanTg(new Date());
        uploadedFile.setTenFileHash(tenFileHash+ "." + fileExtension);
        uploadedFile.setIdVanBan(1);            
        uploadedFile.setPhanMoRong("."+fileExtension);
        
        uploadedFile.setFilePath(rootFolder + fileName);
        uploadedFile.setDuongDan(fileUrlDev + fileName);
        uploadedFile.setData(file.getBytes());
        uploadedFile = rUpload.save(uploadedFile);

        return fileId;
    }

    @Override
    public UploadFile downloadFile(Long id) throws Exception {
        Optional<UploadFile> optionalUploadedFile = rUpload.findById(id);

        if (optionalUploadedFile.isPresent()) {
            return optionalUploadedFile.get();
        } else {
            throw new Exception("Không tìm thấy file có ID: " + id);
        }
    }
}