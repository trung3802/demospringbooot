package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_DoKhan;

@Repository
public interface VanBan_DoKhanRepository extends JpaRepository<VanBan_DoKhan, Integer> {
	VanBan_DoKhan findByMa(String ma);
	VanBan_DoKhan findByTen(String ten);
}