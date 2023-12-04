package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.VanBanOutput;

public interface LuuTruVanBanService {
	List<VanBanOutput> getVanBanDens();
	
	List<VanBanOutput> getVanBanDis();
	
	AppResponse addVanBan(VanBan vb);
	
	AppResponse editVanBan(VanBan vb);
	
	AppResponse deleteVanBan(Integer id);
}