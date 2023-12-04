package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.Ho_So_VB;

@Repository
public interface Ho_So_VBRepository extends JpaRepository<Ho_So_VB, Integer> {
	@Query("select hsvb "
			+ "from Ho_So_VB hsvb "
			+ "where hsvb.idHoSo = :idHoSo")
	List<Ho_So_VB> getVanBanByHoSos(@Param("idHoSo") Integer idHoSo);
}