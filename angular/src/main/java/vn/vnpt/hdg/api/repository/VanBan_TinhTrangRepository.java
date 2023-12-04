package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.VanBan_TinhTrang;

@Repository
public interface VanBan_TinhTrangRepository extends JpaRepository<VanBan_TinhTrang, Integer> {
	VanBan_TinhTrang findByMa(String ma);
	VanBan_TinhTrang findByTen(String ten);
}