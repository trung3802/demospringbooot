package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.VaiTroRepository;
import vn.vnpt.hdg.api.services.VaiTroService;

@Service
public class VaiTroServiceImpl implements VaiTroService {
	
	@Autowired
	VaiTroRepository rVaiTro;

	@Override
	public List<VaiTro> getVaiTros() {
		List<VaiTro> cds = rVaiTro.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addVaiTro(VaiTro vt) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vt.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vt.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vt.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    VaiTro existingVaiTroByMa = rVaiTro.findByMa(vt.getMa());
	        if (existingVaiTroByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        VaiTro existingVaiTroByTen = rVaiTro.findByTen(vt.getTen());
	        if (existingVaiTroByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rVaiTro.save(vt);
		    
		    return new AppResponse("Thêm vai trò thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVaiTro(VaiTro vt) {
	    try {        
	        Optional<VaiTro> existingVaiTroOptional = rVaiTro.findById(vt.getId());
	        if (!existingVaiTroOptional.isPresent()) {
	            return new AppResponse("Vai trò không tồn tại trong hệ thống", false);
	        }        

	        VaiTro existingVaiTro = existingVaiTroOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vt.getMa())) {
	            existingVaiTro.setMa(vt.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vt.getTen())) {
	            existingVaiTro.setMa(vt.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (vt.getThuTu() != null) {
	            existingVaiTro.setThuTu(vt.getThuTu());
	        }
	        if (vt.getCapDo() != null) {
	        	existingVaiTro.setCapDo(vt.getCapDo());
	        }
	        if (vt.getTrangThai() != null) {
	            existingVaiTro.setTrangThai(vt.getTrangThai());
	        }
	        if (vt.getAdmin() != null) {
	            existingVaiTro.setAdmin(vt.getAdmin());
	        }
	        
	        rVaiTro.save(existingVaiTro);
	        
	        return new AppResponse("Chỉnh sửa vai trò thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVaiTro(Integer id) {
        try {
            VaiTro existingVaiTro = rVaiTro.findById(id).orElse(null);
            if (existingVaiTro == null) {
                return new AppResponse("Vai trò không tồn tại trong hệ thống", false);
            }
            
            rVaiTro.delete(existingVaiTro);
            
            return new AppResponse("Xóa vai trò thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}