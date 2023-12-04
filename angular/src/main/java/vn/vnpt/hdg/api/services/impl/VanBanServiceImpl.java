package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VanBanRepository;
import vn.vnpt.hdg.api.services.VanBanService;

@Service
public class VanBanServiceImpl implements VanBanService {
	
	@Autowired
	VanBanRepository rVanBan;
	
	@Override
	public List<VanBan> getVanBans() {
		List<VanBan> vbs = rVanBan.findAll();
		return vbs;
	}
	
	@Override
	public AppResponse addVanBan(VanBan vb) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vb.getKyHieu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Ký hiệu không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getNoiGuiDiKhac())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Nơi gửi đi khác không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getNoiGuiDen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Nơi gửi đến không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getChucVu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Chức vụ không được để trống");
		        return appResponse;
		    }
		    
	        VanBan existingVanBanByKieuHieu = rVanBan.findByKyHieu(vb.getKyHieu());
	        if (existingVanBanByKieuHieu != null) {
	            return new AppResponse("Ký hiệu đã có trong hệ thống", false);
	        }
	        
		    // Insert data
		    rVanBan.save(vb);
		    
		    return new AppResponse("Thêm văn bản thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	@Override
	public AppResponse editVanBan(VanBan vb) {
		try {        
	        Optional<VanBan> existingVanBanOptional = rVanBan.findById(vb.getId());
	        if (!existingVanBanOptional.isPresent()) {
	            return new AppResponse("Văn bản không tồn tại trong hệ thống", false);
	        }        

	        VanBan existingVanBan = existingVanBanOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vb.getKyHieu())) {
	            existingVanBan.setKyHieu(vb.getKyHieu());
	        }
	        else {
	        	return new AppResponse("Ký hiệu không được để trống");
	        }
	        
	        rVanBan.save(existingVanBan);
	        
	        return new AppResponse("Chỉnh sửa văn thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan(Integer id) {
        try {
            VanBan existingVanBan = rVanBan.findById(id).orElse(null);
            if (existingVanBan == null) {
                return new AppResponse("Văn Bản không tồn tại trong hệ thống", false);
            }
            
            rVanBan.delete(existingVanBan);
            
            return new AppResponse("Xóa văn bản thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

}
