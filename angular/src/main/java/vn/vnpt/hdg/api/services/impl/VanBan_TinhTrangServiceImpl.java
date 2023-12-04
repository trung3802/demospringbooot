package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan_TinhTrang;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBan_TinhTrangRepository;
import vn.vnpt.hdg.api.services.VanBan_TinhTrangService;

@Service
public class VanBan_TinhTrangServiceImpl implements VanBan_TinhTrangService {
	
	@Autowired
	VanBan_TinhTrangRepository rVanBan_TinhTrang;

	@Override
	public List<VanBan_TinhTrang> getVanBan_TinhTrangs() {
		List<VanBan_TinhTrang> cds = rVanBan_TinhTrang.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVanBan_TinhTrang(VanBan_TinhTrang vbl) {
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
		    VanBan_TinhTrang existingVanBan_TinhTrangByMa = rVanBan_TinhTrang.findByMa(vbl.getMa());
	        if (existingVanBan_TinhTrangByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VanBan_TinhTrang existingVanBan_TinhTrangByTen = rVanBan_TinhTrang.findByTen(vbl.getTen());
	        if (existingVanBan_TinhTrangByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVanBan_TinhTrang.save(vbl);
		    
		    return new AppResponse("Thêm văn bản tình trạng thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVanBan_TinhTrang(VanBan_TinhTrang vbl) {
	    try {        
	        Optional<VanBan_TinhTrang> existingVanBan_TinhTrangOptional = rVanBan_TinhTrang.findById(vbl.getId());
	        if (!existingVanBan_TinhTrangOptional.isPresent()) {
	            return new AppResponse("Văn bản tình trạng không tồn tại trong hệ thống", false);
	        }        

	        VanBan_TinhTrang existingVanBan_TinhTrang = existingVanBan_TinhTrangOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vbl.getMa())) {
	            existingVanBan_TinhTrang.setMa(vbl.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vbl.getTen())) {
	            existingVanBan_TinhTrang.setTen(vbl.getTen());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (vbl.getThuTu() != null) {
	            existingVanBan_TinhTrang.setThuTu(vbl.getThuTu());
	        }
	        if (vbl.getTrangThai() != null) {
	            existingVanBan_TinhTrang.setTrangThai(vbl.getTrangThai());
	        }
	        
	        rVanBan_TinhTrang.save(existingVanBan_TinhTrang);
	        
	        return new AppResponse("Chỉnh sửa văn bản tình trạng thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan_TinhTrang(Integer id) {
        try {
            VanBan_TinhTrang existingVanBan_TinhTrang = rVanBan_TinhTrang.findById(id).orElse(null);
            if (existingVanBan_TinhTrang == null) {
                return new AppResponse("Văn bản tình trạng không tồn tại trong hệ thống", false);
            }
            
            rVanBan_TinhTrang.delete(existingVanBan_TinhTrang);
            
            return new AppResponse("Xóa văn bản tình trạng thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
