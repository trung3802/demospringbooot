package vn.vnpt.hdg.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.DM_Kieu_Bao_QuanRepository;
import vn.vnpt.hdg.api.services.DM_Kieu_Bao_QuanService;

@Service
public class DM_Kieu_Bao_QuanServiceImpl implements DM_Kieu_Bao_QuanService {
	
	@Autowired
	DM_Kieu_Bao_QuanRepository rDM_Kieu_Bao_Quan;

	@Override
	public List<DM_Kieu_Bao_Quan> getDM_Kieu_Bao_Quans() {
		List<DM_Kieu_Bao_Quan> dmkbqs = rDM_Kieu_Bao_Quan.findAll();
		return dmkbqs;
	}

	@Override
	public AppResponse addDM_Kieu_Bao_Quan(DM_Kieu_Bao_Quan dmkbq) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(dmkbq.getTenKieu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		  
		    // Insert data
		    rDM_Kieu_Bao_Quan.save(dmkbq);
		    
		    return new AppResponse("Thêm kiểu bảo quản thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editDM_Kieu_Bao_Quan(DM_Kieu_Bao_Quan dmkbq) {
	    try {        
	        Optional<DM_Kieu_Bao_Quan> existingDM_Kieu_Bao_QuanOptional = rDM_Kieu_Bao_Quan.findById(dmkbq.getId());
	        if (!existingDM_Kieu_Bao_QuanOptional.isPresent()) {
	            return new AppResponse("Kiểu bảo quản không tồn tại trong hệ thống", false);
	        }        

	        DM_Kieu_Bao_Quan existingDM_Kieu_Bao_Quan = existingDM_Kieu_Bao_QuanOptional.get();
	        
	        if (!ObjectUtils.isEmpty(dmkbq.getTenKieu())) {
	            existingDM_Kieu_Bao_Quan.setTenKieu(dmkbq.getTenKieu());
	        }
	        else {
	        	return new AppResponse("Tên kiểu không được để trống");
	        }  
	        
	        if (dmkbq.getTrangThai() != null) {
	        	existingDM_Kieu_Bao_Quan.setTrangThai(dmkbq.getTrangThai());
	        }  
	        
	        rDM_Kieu_Bao_Quan.save(existingDM_Kieu_Bao_Quan);
	        
	        return new AppResponse("Chỉnh sửa kiểu bảo quản thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteDM_Kieu_Bao_Quan(Integer id) {
        try {
            DM_Kieu_Bao_Quan existingDM_Kieu_Bao_Quan = rDM_Kieu_Bao_Quan.findById(id).orElse(null);
            if (existingDM_Kieu_Bao_Quan == null) {
                return new AppResponse("Kiểu bảo quản không tồn tại trong hệ thống", false);
            }
            
            rDM_Kieu_Bao_Quan.delete(existingDM_Kieu_Bao_Quan);
            
            return new AppResponse("Xóa kiểu bảo quản thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
