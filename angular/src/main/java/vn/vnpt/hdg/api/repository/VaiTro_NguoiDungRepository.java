package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.vnpt.hdg.api.models.VaiTro_NguoiDung;

@Repository
public interface VaiTro_NguoiDungRepository extends JpaRepository<VaiTro_NguoiDung, Integer> {
	@Query("select vtnd.id, nd.ma, nd.ten, dv.ten as tenDonVi, vt.ten as tenVaiTro, dv.id as donViID, vt.id as vaiTroID, nd.id as nguoiDungID "
			+ "from NguoiDung nd "
			+ "inner join DonVi dv on nd.donViID = dv.id "
			+ "left join VaiTro_NguoiDung vtnd on vtnd.nguoiDungID = nd.id "
			+ "left join VaiTro vt on vtnd.vaiTroID = vt.id")
	List<Object[]> getVaiTroNguoiDungs();
}