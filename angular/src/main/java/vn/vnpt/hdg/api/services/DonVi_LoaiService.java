package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.DonVi_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface DonVi_LoaiService {
	List<DonVi_Loai> getDonVi_Loais();
	
	AppResponse addDonVi_Loai(DonVi_Loai dvl);
	
	AppResponse editDonVi_Loai(DonVi_Loai dvl);
	
	AppResponse deleteDonVi_Loai(Integer id);
}