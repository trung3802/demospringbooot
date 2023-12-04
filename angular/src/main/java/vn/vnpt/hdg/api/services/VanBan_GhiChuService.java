package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_GhiChu;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_GhiChuService {
	List<VanBan_GhiChu> getVanBan_GhiChus();
	
	AppResponse addVanBan_GhiChu(VanBan_GhiChu vbgc);
	
	AppResponse editVanBan_GhiChu(VanBan_GhiChu vbgc);
	
	AppResponse deleteVanBan_GhiChu(Integer id);
}