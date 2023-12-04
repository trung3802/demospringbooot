package vn.vnpt.hdg.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.DM_KH_HinhThanh_HS;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.DM_KH_HinhThanh_HSRepository;
import vn.vnpt.hdg.api.services.DM_KH_HinhThanh_HSService;

@Service
public class DM_KH_HinhThanh_HSServiceImpl implements DM_KH_HinhThanh_HSService {
	
	@Autowired
	DM_KH_HinhThanh_HSRepository rDM_KH_HinhThanh_HS;

	@Override
	public List<DM_KH_HinhThanh_HS> getDM_KH_HinhThanh_HSs() {
		List<DM_KH_HinhThanh_HS> dmkhhthss = rDM_KH_HinhThanh_HS.findAll();
		return dmkhhthss;
	}

	@Override
	public AppResponse addDM_KH_HinhThanh_HS(DM_KH_HinhThanh_HS dmkhhths) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(dmkhhths.getTenNhom())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(dmkhhths.getKyHieu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu ký hiệu không được để trống");
		        return appResponse;
		    }
		  
		    // Insert data
		    rDM_KH_HinhThanh_HS.save(dmkhhths);
		    
		    return new AppResponse("Thêm thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editDM_KH_HinhThanh_HS(DM_KH_HinhThanh_HS dmkhhths) {
	    try {        
	        Optional<DM_KH_HinhThanh_HS> existingDM_KH_HinhThanh_HSOptional = rDM_KH_HinhThanh_HS.findById(dmkhhths.getId());
	        if (!existingDM_KH_HinhThanh_HSOptional.isPresent()) {
	            return new AppResponse("Ký hiệu hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        DM_KH_HinhThanh_HS existingDM_KH_HinhThanh_HS = existingDM_KH_HinhThanh_HSOptional.get();
	        
	        if (!ObjectUtils.isEmpty(dmkhhths.getTenNhom())) {
	            existingDM_KH_HinhThanh_HS.setTenNhom(dmkhhths.getTenNhom());
	        }
	        else {
	        	return new AppResponse("Tên nhóm không được để trống");
	        }
	        if (dmkhhths.getNgayTao() != null) {
	            existingDM_KH_HinhThanh_HS.setNgayTao(dmkhhths.getNgayTao());
	        }
	        if (dmkhhths.getNgayCapNhat() != null) {
	            existingDM_KH_HinhThanh_HS.setNgayCapNhat(dmkhhths.getNgayCapNhat());
	        }
	        if (dmkhhths.getNguoiTao() != null) {
	            existingDM_KH_HinhThanh_HS.setNguoiTao(dmkhhths.getNguoiTao());
	        }
	        if (dmkhhths.getNguoiCapNhat() != null) {
	            existingDM_KH_HinhThanh_HS.setNguoiCapNhat(dmkhhths.getNguoiCapNhat());
	        }        
	        if (dmkhhths.getTrangThai() != null) {
	            existingDM_KH_HinhThanh_HS.setTrangThai(dmkhhths.getTrangThai());
	        }   
	        if (!ObjectUtils.isEmpty(dmkhhths.getKyHieu())) {
	            existingDM_KH_HinhThanh_HS.setKyHieu(dmkhhths.getKyHieu());
	        }
	        else {
	        	return new AppResponse("Ký hiệu không được để trống");
	        }   
	        
	        rDM_KH_HinhThanh_HS.save(existingDM_KH_HinhThanh_HS);
	        
	        return new AppResponse("Chỉnh sửa thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteDM_KH_HinhThanh_HS(Integer id) {
        try {
            DM_KH_HinhThanh_HS existingDM_KH_HinhThanh_HS = rDM_KH_HinhThanh_HS.findById(id).orElse(null);
            if (existingDM_KH_HinhThanh_HS == null) {
                return new AppResponse("Ký hiệu không tồn tại trong hệ thống", false);
            }
            
            rDM_KH_HinhThanh_HS.delete(existingDM_KH_HinhThanh_HS);
            
            return new AppResponse("Xóa ký hiệu thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
