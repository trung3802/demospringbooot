package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.DonVi;

@Repository
public interface DonViRepository extends JpaRepository<DonVi, Integer> {
	DonVi findByMa(String ma);
	DonVi findByTen(String ten);
	
	@Query("select dv "
			+ "from DonVi dv")
	List<DonVi> getDonVis();
	
	@Query("select dv "
			+ "from DonVi dv "
			+ "where dv.chaID = :id")
	List<DonVi> getDonViCons(
		@Param("id") Integer id);
	
	@Query("select dv "
			+ "from DonVi dv "
			+ "where dv.chaID = 0 or dv.chaID = 1")
	List<DonVi> getDonViTheoDois();
}