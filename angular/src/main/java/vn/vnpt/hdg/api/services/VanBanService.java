package vn.vnpt.hdg.api.services;

import java.util.List;


import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBanService {
	List<VanBan> getVanBans();
	
	AppResponse addVanBan(VanBan vb);
	
	AppResponse editVanBan(VanBan vb);
	
	AppResponse deleteVanBan(Integer id);
}