package vn.vnpt.hdg.api.dao;

import vn.vnpt.hdg.api.models.NguoiDung;

public interface NguoiDungDao {
	NguoiDung getNguoiDungTheoToken(String token);
}
