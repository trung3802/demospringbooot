package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan_GhiChu;

@Repository
public interface VanBan_GhiChuRepository extends JpaRepository<VanBan_GhiChu, Integer> {
	VanBan_GhiChu findByVanBanID(Integer ma);
}