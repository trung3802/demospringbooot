package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_LinhVuc;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_LinhVucService {
	List<VanBan_LinhVuc> getVanBan_LinhVucs();
	
	AppResponse addVanBan_LinhVuc(VanBan_LinhVuc vblv);
	
	AppResponse editVanBan_LinhVuc(VanBan_LinhVuc vblv);
	
	AppResponse deleteVanBan_LinhVuc(Integer id);
}