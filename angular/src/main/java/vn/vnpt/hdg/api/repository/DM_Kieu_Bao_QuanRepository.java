package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;

@Repository
public interface DM_Kieu_Bao_QuanRepository extends JpaRepository<DM_Kieu_Bao_Quan, Integer> {
}