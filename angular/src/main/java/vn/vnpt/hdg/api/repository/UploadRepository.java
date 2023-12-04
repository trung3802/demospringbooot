package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.vnpt.hdg.api.models.UploadFile;

public interface UploadRepository extends JpaRepository<UploadFile, Long>{
}