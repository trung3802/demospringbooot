package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.DonVi;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.DonViOutput;
import vn.vnpt.hdg.api.payload.response.TreeItem;

public interface DonViService {
	List<DonViOutput> getDonVis();
	
	List<DonViOutput> getDonViCons(Integer id);
	
	List<DonVi> getDonViTheoDois();
	
	List<TreeItem> getTreeDonVi();
	
	AppResponse addDonVi(DonVi dv);
	
	AppResponse editDonVi(DonVi dv);
	
	AppResponse deleteDonVi(Integer id);
}