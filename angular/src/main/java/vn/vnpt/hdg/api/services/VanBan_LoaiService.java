package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_LoaiService {
	List<VanBan_Loai> getVanBan_Loais();
	
	AppResponse addVanBan_Loai(VanBan_Loai vbl);
	
	AppResponse editVanBan_Loai(VanBan_Loai vbl);
	
	AppResponse deleteVanBan_Loai(Integer id);
}