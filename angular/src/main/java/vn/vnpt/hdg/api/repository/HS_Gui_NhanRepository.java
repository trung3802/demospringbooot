package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.HS_Gui_Nhan;

@Repository
public interface HS_Gui_NhanRepository extends JpaRepository<HS_Gui_Nhan, Integer> {
	HS_Gui_Nhan findByMaHoSo(String mahs);
}