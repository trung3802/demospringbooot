package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_Kieu;

@Repository
public interface VanBan_KieuRepository extends JpaRepository<VanBan_Kieu, Integer> {
	VanBan_Kieu findByMa(String ma);
	VanBan_Kieu findByTen(String ten);
}