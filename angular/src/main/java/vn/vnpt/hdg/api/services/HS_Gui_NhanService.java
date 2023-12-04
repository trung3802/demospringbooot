package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.HS_Gui_Nhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface HS_Gui_NhanService {
	List<HS_Gui_Nhan> getHS_Gui_Nhans();
	
	AppResponse addHS_Gui_Nhan(HS_Gui_Nhan hs);
	
	AppResponse editHS_Gui_Nhan(HS_Gui_Nhan hs);
	
	AppResponse deleteHS_Gui_Nhan(Integer id);
}