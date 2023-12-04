package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_Kieu;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_KieuRepository;
import vn.vnpt.hdg.api.services.VanBan_KieuService;

@Service
public class VanBan_KieuServiceImpl implements VanBan_KieuService {
	
	@Autowired
	VanBan_KieuRepository rVanBan_Kieu;

	@Override
	public List<VanBan_Kieu> getVanBan_Kieus() {
		List<VanBan_Kieu> cds = rVanBan_Kieu.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_Kieu(VanBan_Kieu vbk) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vbk.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbk.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbk.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VanBan_Kieu existingVanBan_KieuByMa = rVanBan_Kieu.findByMa(vbk.getMa());
	        if (existingVanBan_KieuByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_Kieu existingVanBan_KieuByTen = rVanBan_Kieu.findByTen(vbk.getTen());
	        if (existingVanBan_KieuByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_Kieu.save(vbk);
		    
		    return new AppResponse("Thêm văn bản kiểu thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_Kieu(VanBan_Kieu vbk) {
	    try {        
	        Optional<VanBan_Kieu> existingVanBan_KieuOptional = rVanBan_Kieu.findById(vbk.getId());
	        if (!existingVanBan_KieuOptional.isPresent()) {
	            return new AppResponse("Văn bản kiểu không tồn tại trong hệ thống", false);
	        }        

	        VanBan_Kieu existingVanBan_Kieu = existingVanBan_KieuOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbk.getMa())) {
	            existingVanBan_Kieu.setMa(vbk.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbk.getTen())) {
	            existingVanBan_Kieu.setTen(vbk.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (vbk.getThuTu() != null) {
	            existingVanBan_Kieu.setThuTu(vbk.getThuTu());
	        }
	        if (vbk.getTrangThai() != null) {
	            existingVanBan_Kieu.setTrangThai(vbk.getTrangThai());
	        }
	        
	        rVanBan_Kieu.save(existingVanBan_Kieu);
	        
	        return new AppResponse("Chỉnh sửa văn bản kiểu thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_Kieu(Integer id) {
        try {
            VanBan_Kieu existingVanBan_Kieu = rVanBan_Kieu.findById(id).orElse(null);
            if (existingVanBan_Kieu == null) {
                return new AppResponse("Văn bản kiểu không tồn tại trong hệ thống", false);
            }
            
            rVanBan_Kieu.delete(existingVanBan_Kieu);
            
            return new AppResponse("Xóa văn bản kiểu thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
