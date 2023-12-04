package vn.vnpt.hdg.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnpt.hdg.api.models.AccessToken;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
}