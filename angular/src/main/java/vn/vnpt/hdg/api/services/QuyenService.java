package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.Quyen;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.TreeItem;

public interface QuyenService {
	List<Quyen> getQuyens();
	
	List<TreeItem> getTreeQuyen();
	
	AppResponse addQuyen(Quyen q);
	
	AppResponse editQuyen(Quyen q);
	
	AppResponse deleteQuyen(Integer id);
}