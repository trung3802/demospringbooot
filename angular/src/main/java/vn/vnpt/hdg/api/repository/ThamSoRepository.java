package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.ThamSo;

@Repository
public interface ThamSoRepository extends JpaRepository<ThamSo, Integer> {
	ThamSo findByMa(String ma);
}