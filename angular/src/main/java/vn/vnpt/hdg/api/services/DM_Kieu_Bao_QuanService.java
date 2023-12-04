package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface DM_Kieu_Bao_QuanService {
	List<DM_Kieu_Bao_Quan> getDM_Kieu_Bao_Quans();
	
	AppResponse addDM_Kieu_Bao_Quan(DM_Kieu_Bao_Quan dmkbq);
	
	AppResponse editDM_Kieu_Bao_Quan(DM_Kieu_Bao_Quan dmkbq);
	
	AppResponse deleteDM_Kieu_Bao_Quan(Integer id);
}