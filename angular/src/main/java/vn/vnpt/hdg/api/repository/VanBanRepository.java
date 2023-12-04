package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.VanBan;

@Repository
public interface VanBanRepository extends JpaRepository<VanBan, Integer> {
	VanBan findByKyHieu(String kh);
	
	@Query("select vb "
			+ "from VanBan vb "
			+ "where vb.vanBanKieuID = 2")
	List<VanBan> getVanBanDens();
	
	@Query("select vb "
			+ "from VanBan vb "
			+ "where vb.vanBanKieuID = 1")
	List<VanBan> getVanBanDis();
	
	@Query("select vb "
	        + "from VanBan vb "
	        + "inner join VanBan_Loai vbl on vb.vanBanLoaiID = vbl.id "
	        + "where (:kyHieu is null or vb.kyHieu LIKE %:kyHieu%) "
	        + "and (:trichYeu is null or vb.trichYeu LIKE %:trichYeu%) "
	        + "and (:vanBanLoaiID is null or vb.vanBanLoaiID = :vanBanLoaiID) "
	        + "and (:vanBanLinhVucID is null or vb.vanBanLinhVucID = :vanBanLinhVucID) "
	        + "and (:vanBanDoKhanID is null or vb.vanBanDoKhanID = :vanBanDoKhanID) "
	        + "and (:vanBanDoMatID is null or vb.vanBanDoMatID = :vanBanDoMatID)")
	List<VanBan> getVanBanToanDonVis(
		@Param("kyHieu") String kyHieu,
	    @Param("trichYeu") String trichYeu,
	    @Param("vanBanLoaiID") Integer vanBanLoaiID,
	    @Param("vanBanLinhVucID") Integer vanBanLinhVucID,
	    @Param("vanBanDoKhanID") Integer vanBanDoKhanID,
	    @Param("vanBanDoMatID") Integer vanBanDoMatID);
}