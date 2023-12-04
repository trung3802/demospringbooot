package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_DoKhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_DoKhanService {
	List<VanBan_DoKhan> getVanBan_DoKhans();
	
	AppResponse addVanBan_DoKhan(VanBan_DoKhan vbdk);
	
	AppResponse editVanBan_DoKhan(VanBan_DoKhan vbdk);
	
	AppResponse deleteVanBan_DoKhan(Integer id);
}