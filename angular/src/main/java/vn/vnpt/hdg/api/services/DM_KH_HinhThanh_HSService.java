package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.DM_KH_HinhThanh_HS;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface DM_KH_HinhThanh_HSService {
	List<DM_KH_HinhThanh_HS> getDM_KH_HinhThanh_HSs();
	
	AppResponse addDM_KH_HinhThanh_HS(DM_KH_HinhThanh_HS dmkhhths);
	
	AppResponse editDM_KH_HinhThanh_HS(DM_KH_HinhThanh_HS dmkhhths);
	
	AppResponse deleteDM_KH_HinhThanh_HS(Integer id);
}