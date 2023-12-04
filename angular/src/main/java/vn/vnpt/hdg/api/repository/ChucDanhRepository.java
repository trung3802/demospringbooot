package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.ChucDanh;

@Repository
public interface ChucDanhRepository extends JpaRepository<ChucDanh, Integer> {
	ChucDanh findByMa(String ma);
	ChucDanh findByTen(String ten);
}