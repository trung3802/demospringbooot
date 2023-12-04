package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_DoMat;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_DoMatRepository;
import vn.vnpt.hdg.api.services.VanBan_DoMatService;

@Service
public class VanBan_DoMatServiceImpl implements VanBan_DoMatService {
	
	@Autowired
	VanBan_DoMatRepository rVanBan_DoMat;

	@Override
	public List<VanBan_DoMat> getVanBan_DoMats() {
		List<VanBan_DoMat> cds = rVanBan_DoMat.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_DoMat(VanBan_DoMat vbdm) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vbdm.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbdm.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbdm.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VanBan_DoMat existingVanBan_DoMatByMa = rVanBan_DoMat.findByMa(vbdm.getMa());
	        if (existingVanBan_DoMatByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_DoMat existingVanBan_DoMatByTen = rVanBan_DoMat.findByTen(vbdm.getTen());
	        if (existingVanBan_DoMatByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_DoMat.save(vbdm);
		    
		    return new AppResponse("Thêm văn bản độ mật thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_DoMat(VanBan_DoMat vbdm) {
	    try {        
	        Optional<VanBan_DoMat> existingVanBan_DoMatOptional = rVanBan_DoMat.findById(vbdm.getId());
	        if (!existingVanBan_DoMatOptional.isPresent()) {
	            return new AppResponse("Văn bản độ mật không tồn tại trong hệ thống", false);
	        }        

	        VanBan_DoMat existingVanBan_DoMat = existingVanBan_DoMatOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbdm.getMa())) {
	            existingVanBan_DoMat.setMa(vbdm.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbdm.getTen())) {
	            existingVanBan_DoMat.setTen(vbdm.getTen());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (vbdm.getThuTu() != null) {
	            existingVanBan_DoMat.setThuTu(vbdm.getThuTu());
	        }
	        if (vbdm.getTrangThai() != null) {
	            existingVanBan_DoMat.setTrangThai(vbdm.getTrangThai());
	        }
	        
	        rVanBan_DoMat.save(existingVanBan_DoMat);
	        
	        return new AppResponse("Chỉnh sửa văn bản độ mật thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_DoMat(Integer id) {
        try {
            VanBan_DoMat existingVanBan_DoMat = rVanBan_DoMat.findById(id).orElse(null);
            if (existingVanBan_DoMat == null) {
                return new AppResponse("Văn bản độ mật không tồn tại trong hệ thống", false);
            }
            
            rVanBan_DoMat.delete(existingVanBan_DoMat);
            
            return new AppResponse("Xóa văn bản độ mật thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
