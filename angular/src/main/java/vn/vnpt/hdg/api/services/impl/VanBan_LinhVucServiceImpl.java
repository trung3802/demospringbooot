package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_LinhVuc;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_LinhVucRepository;
import vn.vnpt.hdg.api.services.VanBan_LinhVucService;

@Service
public class VanBan_LinhVucServiceImpl implements VanBan_LinhVucService {
	
	@Autowired
	VanBan_LinhVucRepository rVanBan_LinhVuc;

	@Override
	public List<VanBan_LinhVuc> getVanBan_LinhVucs() {
		List<VanBan_LinhVuc> cds = rVanBan_LinhVuc.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_LinhVuc(VanBan_LinhVuc vblv) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vblv.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vblv.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vblv.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VanBan_LinhVuc existingVanBan_LinhVucByMa = rVanBan_LinhVuc.findByMa(vblv.getMa());
	        if (existingVanBan_LinhVucByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_LinhVuc existingVanBan_LinhVucByTen = rVanBan_LinhVuc.findByTen(vblv.getTen());
	        if (existingVanBan_LinhVucByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_LinhVuc.save(vblv);
		    
		    return new AppResponse("Thêm văn bản lĩnh vực thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_LinhVuc(VanBan_LinhVuc vblv) {
	    try {        
	        Optional<VanBan_LinhVuc> existingVanBan_LinhVucOptional = rVanBan_LinhVuc.findById(vblv.getId());
	        if (!existingVanBan_LinhVucOptional.isPresent()) {
	            return new AppResponse("Văn bản lĩnh vực không tồn tại trong hệ thống", false);
	        }        

	        VanBan_LinhVuc existingVanBan_LinhVuc = existingVanBan_LinhVucOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vblv.getMa())) {
	            existingVanBan_LinhVuc.setMa(vblv.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vblv.getTen())) {
	            existingVanBan_LinhVuc.setTen(vblv.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (vblv.getThuTu() != null) {
	            existingVanBan_LinhVuc.setThuTu(vblv.getThuTu());
	        }
	        if (vblv.getTrangThai() != null) {
	            existingVanBan_LinhVuc.setTrangThai(vblv.getTrangThai());
	        }
	        
	        rVanBan_LinhVuc.save(existingVanBan_LinhVuc);
	        
	        return new AppResponse("Chỉnh sửa văn bản lĩnh vực thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_LinhVuc(Integer id) {
        try {
            VanBan_LinhVuc existingVanBan_LinhVuc = rVanBan_LinhVuc.findById(id).orElse(null);
            if (existingVanBan_LinhVuc == null) {
                return new AppResponse("Văn bản lĩnh vực không tồn tại trong hệ thống", false);
            }
            
            rVanBan_LinhVuc.delete(existingVanBan_LinhVuc);
            
            return new AppResponse("Xóa văn bản lĩnh vực thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
