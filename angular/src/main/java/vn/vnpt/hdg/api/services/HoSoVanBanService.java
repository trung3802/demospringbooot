package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.Ho_So_VB;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoVanBanOutput;

public interface HoSoVanBanService {
	List<HoSoVanBanOutput> getVanBanByHoSos(Ho_So_VB hsvb);
	
	AppResponse addHoSoVanBan(Ho_So_VB hsvb);
	
	AppResponse editHoSoVanBan(Ho_So_VB hsvb);
	
	AppResponse deleteHoSoVanBan(Integer id);
}