package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan_Kieu;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VanBan_KieuService {
	List<VanBan_Kieu> getVanBan_Kieus();
	
	AppResponse addVanBan_Kieu(VanBan_Kieu vbk);
	
	AppResponse editVanBan_Kieu(VanBan_Kieu vbk);
	
	AppResponse deleteVanBan_Kieu(Integer id);
}