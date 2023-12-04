package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_DoKhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_DoKhanRepository;
import vn.vnpt.hdg.api.services.VanBan_DoKhanService;

@Service
public class VanBan_DoKhanServiceImpl implements VanBan_DoKhanService {
	
	@Autowired
	VanBan_DoKhanRepository rVanBan_DoKhan;

	@Override
	public List<VanBan_DoKhan> getVanBan_DoKhans() {
		List<VanBan_DoKhan> cds = rVanBan_DoKhan.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_DoKhan(VanBan_DoKhan vbdk) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vbdk.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbdk.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbdk.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VanBan_DoKhan existingVanBan_DoKhanByMa = rVanBan_DoKhan.findByMa(vbdk.getMa());
	        if (existingVanBan_DoKhanByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_DoKhan existingVanBan_DoKhanByTen = rVanBan_DoKhan.findByTen(vbdk.getTen());
	        if (existingVanBan_DoKhanByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_DoKhan.save(vbdk);
		    
		    return new AppResponse("Thêm văn bản độ khẩn thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_DoKhan(VanBan_DoKhan vbdk) {
	    try {        
	        Optional<VanBan_DoKhan> existingVanBan_DoKhanOptional = rVanBan_DoKhan.findById(vbdk.getId());
	        if (!existingVanBan_DoKhanOptional.isPresent()) {
	            return new AppResponse("Văn bản độ khẩn không tồn tại trong hệ thống", false);
	        }        

	        VanBan_DoKhan existingVanBan_DoKhan = existingVanBan_DoKhanOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbdk.getMa())) {
	            existingVanBan_DoKhan.setMa(vbdk.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbdk.getTen())) {
	            existingVanBan_DoKhan.setTen(vbdk.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (vbdk.getThuTu() != null) {
	            existingVanBan_DoKhan.setThuTu(vbdk.getThuTu());
	        }
	        if (vbdk.getTrangThai() != null) {
	            existingVanBan_DoKhan.setTrangThai(vbdk.getTrangThai());
	        }
	        
	        rVanBan_DoKhan.save(existingVanBan_DoKhan);
	        
	        return new AppResponse("Chỉnh sửa văn bản độ khẩn thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_DoKhan(Integer id) {
        try {
            VanBan_DoKhan existingVanBan_DoKhan = rVanBan_DoKhan.findById(id).orElse(null);
            if (existingVanBan_DoKhan == null) {
                return new AppResponse("Văn bản độ khẩn không tồn tại trong hệ thống", false);
            }
            
            rVanBan_DoKhan.delete(existingVanBan_DoKhan);
            
            return new AppResponse("Xóa văn bản độ khẩn thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
