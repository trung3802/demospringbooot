package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_Loai;

@Repository
public interface VanBan_LoaiRepository extends JpaRepository<VanBan_Loai, Integer> {
	VanBan_Loai findByMa(String ma);
	VanBan_Loai findByTen(String ten);
}