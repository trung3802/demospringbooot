package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_TinhTrang;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_TinhTrangService {
	List<VanBan_TinhTrang> getVanBan_TinhTrangs();
	
	AppResponse addVanBan_TinhTrang(VanBan_TinhTrang vbtt);
	
	AppResponse editVanBan_TinhTrang(VanBan_TinhTrang vbtt);
	
	AppResponse deleteVanBan_TinhTrang(Integer id);
}