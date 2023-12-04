package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface VaiTroService {
	List<VaiTro> getVaiTros();
	
	AppResponse addVaiTro(VaiTro vt);
	
	AppResponse editVaiTro(VaiTro vt);
	
	AppResponse deleteVaiTro(Integer id);
}