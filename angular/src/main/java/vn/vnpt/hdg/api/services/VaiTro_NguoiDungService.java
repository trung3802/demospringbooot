package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VaiTro_NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.VaiTro_NguoiDungOutput;

public interface VaiTro_NguoiDungService {
	List<VaiTro_NguoiDungOutput> getVaiTro_NguoiDungs();
	
	AppResponse addVaiTro_NguoiDung(VaiTro_NguoiDung vtnd);
	
	AppResponse editVaiTro_NguoiDung(VaiTro_NguoiDung vtnd);
	
	AppResponse deleteVaiTro_NguoiDung(Integer id);
}