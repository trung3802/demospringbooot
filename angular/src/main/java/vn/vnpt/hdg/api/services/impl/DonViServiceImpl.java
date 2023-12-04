package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.DonVi;
import vn.vnpt.hdg.api.models.DonVi_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.DonViOutput;
import vn.vnpt.hdg.api.payload.response.TreeItem;
import vn.vnpt.hdg.api.repository.DonViRepository;
import vn.vnpt.hdg.api.repository.DonVi_LoaiRepository;
import vn.vnpt.hdg.api.services.DonViService;

@Service
public class DonViServiceImpl implements DonViService {
	
	@Autowired
	DonViRepository rDonVi;

	@Autowired
	DonVi_LoaiRepository rDonVi_Loai;

	@Override
	public List<DonViOutput> getDonVis() {
	    List<DonVi> dvs = rDonVi.getDonVis();
	    List<DonVi_Loai> dvls = rDonVi_Loai.findAll();
	    List<DonViOutput> dtos = new ArrayList<>();
	    for (DonVi dv : dvs) {
	    	DonViOutput donViOutput = new DonViOutput();
			BeanUtils.copyProperties(dv, donViOutput);
			DonVi_Loai donVi_Loai = dvls.stream().filter(item -> item.getId().equals(dv.getDonViLoaiID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(donVi_Loai)) {
				donViOutput.setTenLoaiDonVi(donVi_Loai.getTen());
			}
			dtos.add(donViOutput);
	    }
	    return dtos;
	}
	
	@Override
	public List<DonViOutput> getDonViCons(Integer id) {
	    List<DonVi> dvs = rDonVi.getDonViCons(id);
	    List<DonVi_Loai> dvls = rDonVi_Loai.findAll();
	    List<DonViOutput> dtos = new ArrayList<>();
	    for (DonVi dv : dvs) {
	    	DonViOutput donViOutput = new DonViOutput();
			BeanUtils.copyProperties(dv, donViOutput);
			DonVi_Loai donVi_Loai = dvls.stream().filter(item -> item.getId().equals(dv.getDonViLoaiID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(donVi_Loai)) {
				donViOutput.setTenLoaiDonVi(donVi_Loai.getTen());
			}
			dtos.add(donViOutput);
	    }
	    return dtos;
	}
	
	@Override
	public List<DonVi> getDonViTheoDois() {
		List<DonVi> cds = rDonVi.getDonViTheoDois();
		return cds;
	}
	
	@Override
    public List<TreeItem> getTreeDonVi() {
        List<DonVi> donVis = rDonVi.findAll();
        return buildTree(donVis, 0);
    }
	
    private List<TreeItem> buildTree(List<DonVi> allDonVis, Integer parentId) {
        List<TreeItem> treeItems = new ArrayList<>();
        for (DonVi donVi : allDonVis) {
            if (donVi.getChaID() == parentId) {
                List<TreeItem> children = buildTree(allDonVis, donVi.getId());
                TreeItem treeItem = new TreeItem(donVi.getId(), donVi.getTen(), false, false, children);
                treeItems.add(treeItem);
            }
        }
        return treeItems;
    }

	@Override
	public AppResponse addDonVi(DonVi dv) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(dv.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(dv.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(dv.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    DonVi existingDonViByMa = rDonVi.findByMa(dv.getMa());
	        if (existingDonViByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        DonVi existingDonViByTen = rDonVi.findByTen(dv.getTen());
	        if (existingDonViByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
	        if (ObjectUtils.isEmpty(dv.getChaID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu cha không được để trống");
		        return appResponse;
		    }
		    // Insert data
		    rDonVi.save(dv);
		    
		    return new AppResponse("Thêm đơn vị thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editDonVi(DonVi dv) {
	    try {        
	        Optional<DonVi> existingDonViOptional = rDonVi.findById(dv.getId());
	        if (!existingDonViOptional.isPresent()) {
	            return new AppResponse("Đơn vị không tồn tại trong hệ thống", false);
	        }        

	        DonVi existingDonVi = existingDonViOptional.get();
	        
	        if (!ObjectUtils.isEmpty(dv.getMa())) {
	            existingDonVi.setTen(dv.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(dv.getTen())) {
	            existingDonVi.setTen(dv.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (dv.getDiaChi() != null) {
	            existingDonVi.setDiaChi(dv.getDiaChi());
	        }
	        if (dv.getDienThoai() != null) {
	            existingDonVi.setDienThoai(dv.getDienThoai());
	        }
	        if (dv.getEmail() != null) {
	            existingDonVi.setEmail(dv.getEmail());
	        }
	        if (dv.getDonViLoaiID() != null) {
	            existingDonVi.setDonViLoaiID(dv.getDonViLoaiID());
	        }
	        if (dv.getCap() != null) {
	            existingDonVi.setCap(dv.getCap());
	        }
	        if (dv.getChaID() != null) {
	            existingDonVi.setChaID(dv.getChaID());
	        }
	        if (dv.getThuTu() != null) {
	            existingDonVi.setThuTu(dv.getThuTu());
	        }
	        if (dv.getTrangThai() != null) {
	            existingDonVi.setTrangThai(dv.getTrangThai());
	        }
	        if (dv.getTenHieu() != null) {
	            existingDonVi.setTenHieu(dv.getTenHieu());
	        }
	        
	        rDonVi.save(existingDonVi);
	        
	        return new AppResponse("Chỉnh sửa đơn vị thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteDonVi(Integer id) {
        try {
            DonVi existingDonVi = rDonVi.findById(id).orElse(null);
            if (existingDonVi == null) {
                return new AppResponse("Đơn vị không tồn tại trong hệ thống", false);
            }
            
            rDonVi.delete(existingDonVi);
            
            return new AppResponse("Xóa đơn vị thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
