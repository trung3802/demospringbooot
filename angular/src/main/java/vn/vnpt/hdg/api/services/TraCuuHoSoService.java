package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.payload.response.HoSoOutput;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;

public interface TraCuuHoSoService {
	List<HoSoOutput> getDanhSachHoSos(HoSoRequest hs);
}