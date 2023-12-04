package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_LinhVuc;

@Repository
public interface VanBan_LinhVucRepository extends JpaRepository<VanBan_LinhVuc, Integer> {
	VanBan_LinhVuc findByMa(String ma);
	VanBan_LinhVuc findByTen(String ten);
}