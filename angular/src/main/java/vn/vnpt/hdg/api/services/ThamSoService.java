package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.ThamSo;
import vn.vnpt.hdg.api.payload.response.AppResponse;

public interface ThamSoService {
	List<ThamSo> getThamSos();
	
	AppResponse addThamSo(ThamSo ts);
	
	AppResponse editThamSo(ThamSo ts);
	
	AppResponse deleteThamSo(Integer id);
}