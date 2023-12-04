package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.Quyen_VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.Quyen_VaiTroOutput;

public interface Quyen_VaiTroService {
	List<Quyen_VaiTroOutput> getQuyen_VaiTros();
	
	AppResponse addQuyen_VaiTro(Quyen_VaiTro qvt);
	
	AppResponse editQuyen_VaiTro(Quyen_VaiTro qvt);
	
	AppResponse deleteQuyen_VaiTro(Integer id);
}