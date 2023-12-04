package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.Quyen;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {
	Quyen findByMa(String ma);
	Quyen findByTen(String ten);
}