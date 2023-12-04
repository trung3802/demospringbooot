package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.ChucDanh;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface ChucDanhService {
	List<ChucDanh> getChucDanhs();
	
	AppResponse addChucDanh(ChucDanh cd);
	
	AppResponse editChucDanh(ChucDanh cd);
	
	AppResponse deleteChucDanh(Integer id);
}