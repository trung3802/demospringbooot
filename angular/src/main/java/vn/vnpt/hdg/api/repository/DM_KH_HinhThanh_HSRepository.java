package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.DM_KH_HinhThanh_HS;

@Repository
public interface DM_KH_HinhThanh_HSRepository extends JpaRepository<DM_KH_HinhThanh_HS, Integer> {
}