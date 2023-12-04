package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.NguoiDungChucDanhOutput;

public interface NguoiDungService {
	List<NguoiDungChucDanhOutput> getNguoiDungs();
	
	List<NguoiDungChucDanhOutput> getNguoiDungByDonVis(Integer id);
	
	AppResponse addNguoiDung(NguoiDung dv);
	
	AppResponse editNguoiDung(NguoiDung dv);
	
	AppResponse deleteNguoiDung(Integer id);
}