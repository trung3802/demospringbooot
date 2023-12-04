package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_GhiChu;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_GhiChuRepository;
import vn.vnpt.hdg.api.services.VanBan_GhiChuService;

@Service
public class VanBan_GhiChuServiceImpl implements VanBan_GhiChuService {
	
	@Autowired
	VanBan_GhiChuRepository rVanBan_GhiChu;

	@Override
	public List<VanBan_GhiChu> getVanBan_GhiChus() {
		List<VanBan_GhiChu> cds = rVanBan_GhiChu.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_GhiChu(VanBan_GhiChu vbgc) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vbgc.getVanBanID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu văn bản ID không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbgc.getNoiDung())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu nội dung không được để trống");
		        return appResponse;
		    }
		    
		    VanBan_GhiChu existingVanBan_GhiChuByVanBanID = rVanBan_GhiChu.findByVanBanID(vbgc.getVanBanID());
	        if (existingVanBan_GhiChuByVanBanID != null) {
	            return new AppResponse("Văn Bản ID đã có trong hệ thống", false);
	        }
	        
		    // Insert data
		    rVanBan_GhiChu.save(vbgc);
		    
		    return new AppResponse("Thêm văn bản ghi chú thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_GhiChu(VanBan_GhiChu vbgc) {
	    try {        
	        Optional<VanBan_GhiChu> existingVanBan_GhiChuOptional = rVanBan_GhiChu.findById(vbgc.getId());
	        if (!existingVanBan_GhiChuOptional.isPresent()) {
	            return new AppResponse("Văn bản ghi chú không tồn tại trong hệ thống", false);
	        }        

	        VanBan_GhiChu existingVanBan_GhiChu = existingVanBan_GhiChuOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbgc.getVanBanID())) {
	            existingVanBan_GhiChu.setVanBanID(vbgc.getVanBanID());
	        }
	        else {
	        	return new AppResponse("Văn bản ID không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbgc.getNoiDung())) {
	            existingVanBan_GhiChu.setNoiDung(vbgc.getNoiDung());
	        }
	        else {
	        	return new AppResponse("Nội Dung không được để trống");
	        }
	        
	        if (vbgc.getNguoiButPhe() != null) {
	            existingVanBan_GhiChu.setNguoiButPhe(vbgc.getNguoiButPhe());
	        }
	        if (vbgc.getCaNhan() != null) {
	            existingVanBan_GhiChu.setCaNhan(vbgc.getCaNhan());
	        }
	        if (vbgc.getNguoiNhapID() != null) {
	            existingVanBan_GhiChu.setNguoiNhapID(vbgc.getNguoiNhapID());
	        }
	        if (vbgc.getThoiGianNhap() != null) {
	            existingVanBan_GhiChu.setThoiGianNhap(vbgc.getThoiGianNhap());
	        }
	        
	        rVanBan_GhiChu.save(existingVanBan_GhiChu);
	        
	        return new AppResponse("Chỉnh sửa văn bản ghi chú thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_GhiChu(Integer id) {
        try {
            VanBan_GhiChu existingVanBan_GhiChu = rVanBan_GhiChu.findById(id).orElse(null);
            if (existingVanBan_GhiChu == null) {
                return new AppResponse("Văn bản ghi chú không tồn tại trong hệ thống", false);
            }
            
            rVanBan_GhiChu.delete(existingVanBan_GhiChu);
            
            return new AppResponse("Xóa văn bản ghi chú thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
