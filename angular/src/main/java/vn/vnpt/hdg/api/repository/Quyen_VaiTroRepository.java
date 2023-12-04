package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.Quyen_VaiTro;

@Repository
public interface Quyen_VaiTroRepository extends JpaRepository<Quyen_VaiTro, Integer> {
	@Query("select qvt "
			+ "from Quyen_VaiTro qvt")
	List<Quyen_VaiTro> getQuyenVaiTros();
}