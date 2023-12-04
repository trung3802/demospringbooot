package vn.vnpt.hdg.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.DonVi_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.DonVi_LoaiRepository;
import vn.vnpt.hdg.api.services.DonVi_LoaiService;

@Service
public class DonVi_LoaiServiceImpl implements DonVi_LoaiService {
	
	@Autowired
	DonVi_LoaiRepository rDonVi_Loai;

	@Override
	public List<DonVi_Loai> getDonVi_Loais() {
		List<DonVi_Loai> cds = rDonVi_Loai.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addDonVi_Loai(DonVi_Loai dvl) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(dvl.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(dvl.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(dvl.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    DonVi_Loai existingDonVi_LoaiByMa = rDonVi_Loai.findByMa(dvl.getMa());
	        if (existingDonVi_LoaiByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
	        DonVi_Loai existingDonVi_LoaiByTen = rDonVi_Loai.findByTen(dvl.getTen());
	        if (existingDonVi_LoaiByTen != null) {
	            return new AppResponse("Tên đã có trong hệ thống", false);
	        }
		    // Insert data
		    rDonVi_Loai.save(dvl);
		    
		    return new AppResponse("Thêm đơn vị loại thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editDonVi_Loai(DonVi_Loai dvl) {
	    try {        
	        Optional<DonVi_Loai> existingDonVi_LoaiOptional = rDonVi_Loai.findById(dvl.getId());
	        if (!existingDonVi_LoaiOptional.isPresent()) {
	            return new AppResponse("Đơn vị loại không tồn tại trong hệ thống", false);
	        }        

	        DonVi_Loai existingDonVi_Loai = existingDonVi_LoaiOptional.get();
	        
	        if (!ObjectUtils.isEmpty(dvl.getMa())) {
	            existingDonVi_Loai.setMa(dvl.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(dvl.getTen())) {
	            existingDonVi_Loai.setTen(dvl.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (dvl.getThuTu() != null) {
	            existingDonVi_Loai.setThuTu(dvl.getThuTu());
	        }
	        if (dvl.getTrangThai() != null) {
	            existingDonVi_Loai.setTrangThai(dvl.getTrangThai());
	        }
	        
	        rDonVi_Loai.save(existingDonVi_Loai);
	        
	        return new AppResponse("Chỉnh sửa đơn vị loại thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteDonVi_Loai(Integer id) {
        try {
            DonVi_Loai existingDonVi_Loai = rDonVi_Loai.findById(id).orElse(null);
            if (existingDonVi_Loai == null) {
                return new AppResponse("Đơn vị loại không tồn tại trong hệ thống", false);
            }
            
            rDonVi_Loai.delete(existingDonVi_Loai);
            
            return new AppResponse("Xóa đơn vị loại thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
