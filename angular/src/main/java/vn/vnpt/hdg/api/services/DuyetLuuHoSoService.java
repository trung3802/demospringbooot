package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoOutput;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;

public interface DuyetLuuHoSoService {
	List<HoSoOutput> getDanhSachHoSos(HoSoRequest hs);
	
	AppResponse duyetLuuHoSoDuyetLuu(Integer id);
	
	AppResponse tuChoiHoSoDuyetLuu(Integer id);
}