package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_LoaiRepository;
import vn.vnpt.hdg.api.services.VanBan_LoaiService;

@Service
public class VanBan_LoaiServiceImpl implements VanBan_LoaiService {
	
	@Autowired
	VanBan_LoaiRepository rVanBan_Loai;

	@Override
	public List<VanBan_Loai> getVanBan_Loais() {
		List<VanBan_Loai> cds = rVanBan_Loai.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_Loai(VanBan_Loai vbl) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vbl.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbl.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vbl.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VanBan_Loai existingVanBan_LoaiByMa = rVanBan_Loai.findByMa(vbl.getMa());
	        if (existingVanBan_LoaiByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_Loai existingVanBan_LoaiByTen = rVanBan_Loai.findByTen(vbl.getTen());
	        if (existingVanBan_LoaiByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_Loai.save(vbl);
		    
		    return new AppResponse("Thêm văn bản loại thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_Loai(VanBan_Loai vbl) {
	    try {        
	        Optional<VanBan_Loai> existingVanBan_LoaiOptional = rVanBan_Loai.findById(vbl.getId());
	        if (!existingVanBan_LoaiOptional.isPresent()) {
	            return new AppResponse("Văn bản loại không tồn tại trong hệ thống", false);
	        }        

	        VanBan_Loai existingVanBan_Loai = existingVanBan_LoaiOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbl.getMa())) {
	            existingVanBan_Loai.setMa(vbl.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbl.getTen())) {
	            existingVanBan_Loai.setTen(vbl.getTen());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (vbl.getThuTu() != null) {
	            existingVanBan_Loai.setThuTu(vbl.getThuTu());
	        }
	        if (vbl.getTrangThai() != null) {
	            existingVanBan_Loai.setTrangThai(vbl.getTrangThai());
	        }
	        
	        rVanBan_Loai.save(existingVanBan_Loai);
	        
	        return new AppResponse("Chỉnh sửa văn bản loại thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_Loai(Integer id) {
        try {
            VanBan_Loai existingVanBan_Loai = rVanBan_Loai.findById(id).orElse(null);
            if (existingVanBan_Loai == null) {
                return new AppResponse("Văn bản loại không tồn tại trong hệ thống", false);
            }
            
            rVanBan_Loai.delete(existingVanBan_Loai);
            
            return new AppResponse("Xóa văn bản loại thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
