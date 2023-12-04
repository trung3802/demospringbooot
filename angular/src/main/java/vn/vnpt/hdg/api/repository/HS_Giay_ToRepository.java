package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.HS_Giay_To;

@Repository
public interface HS_Giay_ToRepository extends JpaRepository<HS_Giay_To, Integer> {
}