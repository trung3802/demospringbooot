package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VaiTro;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
	VaiTro findByMa(String ma);
	VaiTro findByTen(String ten);
}