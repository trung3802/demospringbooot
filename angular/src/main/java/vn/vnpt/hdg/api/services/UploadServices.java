package vn.vnpt.hdg.api.services;

import org.springframework.web.multipart.MultipartFile;

import vn.vnpt.hdg.api.models.UploadFile;

public interface UploadServices {
	String uploadFile(MultipartFile file) throws Exception;
	
    UploadFile downloadFile(Long id) throws Exception;
}