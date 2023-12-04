package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_DoMat;

@Repository
public interface VanBan_DoMatRepository extends JpaRepository<VanBan_DoMat, Integer> {
	VanBan_DoMat findByMa(String ma);
	VanBan_DoMat findByTen(String ten);
}