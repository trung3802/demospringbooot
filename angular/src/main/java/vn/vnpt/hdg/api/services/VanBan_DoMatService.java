package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_DoMat;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_DoMatService {
	List<VanBan_DoMat> getVanBan_DoMats();
	
	AppResponse addVanBan_DoMat(VanBan_DoMat vbdm);
	
	AppResponse editVanBan_DoMat(VanBan_DoMat vbdm);
	
	AppResponse deleteVanBan_DoMat(Integer id);
}