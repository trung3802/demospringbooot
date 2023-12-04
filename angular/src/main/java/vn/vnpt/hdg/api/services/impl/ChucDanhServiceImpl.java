package vn.vnpt.hdg.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.ChucDanh;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.ChucDanhRepository;
import vn.vnpt.hdg.api.services.ChucDanhService;

@Service
public class ChucDanhServiceImpl implements ChucDanhService {
	
	@Autowired
	ChucDanhRepository rChucDanh;

	@Override
	public List<ChucDanh> getChucDanhs() {
		List<ChucDanh> cds = rChucDanh.findAll();
		return cds;
	}

	@Override
	public AppResponse addChucDanh(ChucDanh cd) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(cd.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(cd.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(cd.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    ChucDanh existingChucDanhByMa = rChucDanh.findByMa(cd.getMa());
	        if (existingChucDanhByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        ChucDanh existingChucDanhByTen = rChucDanh.findByTen(cd.getTen());
	        if (existingChucDanhByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rChucDanh.save(cd);
		    
		    return new AppResponse("Thêm chức danh thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editChucDanh(ChucDanh cd) {
	    try {        
	        Optional<ChucDanh> existingChucDanhOptional = rChucDanh.findById(cd.getId());
	        if (!existingChucDanhOptional.isPresent()) {
	            return new AppResponse("Chức danh không tồn tại trong hệ thống", false);
	        }        

	        ChucDanh existingChucDanh = existingChucDanhOptional.get();
	        
	        if (!ObjectUtils.isEmpty(cd.getMa())) {
	            existingChucDanh.setMa(cd.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(cd.getTen())) {
	            existingChucDanh.setTen(cd.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (cd.getThuTu() != null) {
	            existingChucDanh.setThuTu(cd.getThuTu());
	        }
	        if (cd.getTrangThai() != null) {
	            existingChucDanh.setTrangThai(cd.getTrangThai());
	        }
	        
	        rChucDanh.save(existingChucDanh);
	        
	        return new AppResponse("Chỉnh sửa chức danh thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteChucDanh(Integer id) {
        try {
            ChucDanh existingChucDanh = rChucDanh.findById(id).orElse(null);
            if (existingChucDanh == null) {
                return new AppResponse("Chức danh không tồn tại trong hệ thống", false);
            }
            
            rChucDanh.delete(existingChucDanh);
            
            return new AppResponse("Xóa chức danh thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
