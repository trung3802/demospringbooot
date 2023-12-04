package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.TT_Ho_So;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoOutput;

public interface XuLyHoSoService {
	List<HoSoOutput> getHoSoXuLys();
	
	AppResponse editHoSoXuLy(TT_Ho_So hs);
	
	AppResponse deleteHoSoXuLy(Integer id);
	
	AppResponse moHoSoXuLy(Integer id);
	
	AppResponse dongHoSoXuLy(Integer id);
	
	AppResponse luuTruHoSoXuLy(Integer id);
}