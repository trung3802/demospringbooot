package vn.vnpt.hdg.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.NguoiDung;

@Repository
public interface DonViNguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
	@Query("select nd "
			+ "from NguoiDung nd "
			+ "where (:id is null or nd.id = :id)")
	List<NguoiDung> getDonViByNguoiDung(
		@Param("id") Integer id);
}