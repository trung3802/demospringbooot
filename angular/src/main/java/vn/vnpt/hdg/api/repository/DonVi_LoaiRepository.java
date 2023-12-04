package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.DonVi_Loai;

@Repository
public interface DonVi_LoaiRepository extends JpaRepository<DonVi_Loai, Integer> {
	DonVi_Loai findByMa(String ma);
	DonVi_Loai findByTen(String ten);
}