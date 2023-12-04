package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.File_Giay_To;

@Repository
public interface File_Giay_ToRepository extends JpaRepository<File_Giay_To, Integer> {
}