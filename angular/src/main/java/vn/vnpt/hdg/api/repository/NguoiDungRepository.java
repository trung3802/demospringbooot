package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
	@Query("select nd from NguoiDung nd where nd.ma = :ma")
	NguoiDung getByTenDangNhap(@Param("ma") String ma);
	
	NguoiDung findByMa(String ma);
	
	@Query("select nd "
			+ "from NguoiDung nd")
	List<NguoiDung> getNguoiDungs();
	
	@Query("select nd "
			+ "from NguoiDung nd "
			+ "where nd.donViID = :id")
	List<NguoiDung> getNguoiDungByDonVis(
		@Param("id") Integer id);
}