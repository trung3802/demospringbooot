package vn.vnpt.hdg.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.TT_Ho_So;

@Repository
public interface TT_Ho_SoRepository extends JpaRepository<TT_Ho_So, Integer> {	
	@Query("select hs "
		    + "from TT_Ho_So hs "
		    + "where (:maHoSo is null or hs.maHoSo LIKE %:maHoSo%) "
		    + "and (:tuKhoa is null or hs.tuKhoa LIKE %:tuKhoa%) "
		    + "and (:trangThaiNhan is null or hs.trangThaiNhan = :trangThaiNhan) "
		    + "and (:idDoMat is null or hs.idDoMat = :idDoMat) "
		    + "and (:idKieuBaoQuan is null or hs.idKieuBaoQuan = :idKieuBaoQuan) "
		    + "and (:trangThai is null or hs.trangThai = :trangThai) "
		    + "and (:fromDate is null or hs.tgBatDau >= :fromDate) "
		    + "and (:toDate is null or hs.tgKetThuc <= :toDate)")
		List<TT_Ho_So> getDanhSachHoSos(
		    @Param("maHoSo") String maHoSo,
		    @Param("tuKhoa") String tuKhoa,
		    @Param("trangThaiNhan") Integer trangThaiNhan,
		    @Param("idDoMat") Integer idDoMat,
		    @Param("idKieuBaoQuan") Integer idKieuBaoQuan,
		    @Param("trangThai") Integer trangThai,
		    @Param("fromDate") Date fromDate,
		    @Param("toDate") Date toDate);
	
	@Procedure(name = "getTheoDoiHS")
	List<Object[]> getTheoDoiHS(
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,
			@Param("idDonVi") Integer idDonVi);
	
	@Query("select hs "
			+ "from TT_Ho_So hs "
			+ "where trangThai = 0 or trangThai = 1")
	List<TT_Ho_So> getHoSoXyLys();
}