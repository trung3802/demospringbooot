package vn.vnpt.hdg.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.HS_Gui_Nhan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.HS_Gui_NhanRepository;
import vn.vnpt.hdg.api.services.HS_Gui_NhanService;

@Service
public class HS_Gui_NhanServiceImpl implements HS_Gui_NhanService {
	
	@Autowired
	HS_Gui_NhanRepository rHS_Gui_Nhan;

	@Override
	public List<HS_Gui_Nhan> getHS_Gui_Nhans() {
		List<HS_Gui_Nhan> hss = rHS_Gui_Nhan.findAll();
		return hss;
	}

	@Override
	public AppResponse addHS_Gui_Nhan(HS_Gui_Nhan hs) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(hs.getMaHoSo())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(hs.getIdDonViGui())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu đơn vị gửi không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(hs.getIdDonViNhan())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu đơn vị nhận không được để trống");
		        return appResponse;
		    }
		    HS_Gui_Nhan existingHS_Gui_NhanByMa = rHS_Gui_Nhan.findByMaHoSo(hs.getMaHoSo());
	        if (existingHS_Gui_NhanByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }
		    // Insert data
		    rHS_Gui_Nhan.save(hs);
		    
		    return new AppResponse("Thêm Hồ sơ gửi nhận thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editHS_Gui_Nhan(HS_Gui_Nhan hs) {
	    try {        
	        Optional<HS_Gui_Nhan> existingHS_Gui_NhanOptional = rHS_Gui_Nhan.findById(hs.getId());
	        if (!existingHS_Gui_NhanOptional.isPresent()) {
	            return new AppResponse("Hồ sơ gửi nhận không tồn tại trong hệ thống", false);
	        }        

	        HS_Gui_Nhan existingHS_Gui_Nhan = existingHS_Gui_NhanOptional.get();
	        
	        if (!ObjectUtils.isEmpty(hs.getMaHoSo())) {
	            existingHS_Gui_Nhan.setMaHoSo(hs.getMaHoSo());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getIdDonViGui())) {
	            existingHS_Gui_Nhan.setIdDonViGui(hs.getIdDonViGui());
	        }
	        else {
	        	return new AppResponse("Đơn vị gửi không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getIdDonViNhan())) {
	            existingHS_Gui_Nhan.setIdDonViNhan(hs.getIdDonViNhan());
	        }
	        else {
	        	return new AppResponse("Đơn vị nhận không được để trống");
	        }
	        
	        if (hs.getTrangThai() != null) {
	            existingHS_Gui_Nhan.setTrangThai(hs.getTrangThai());
	        }
	        if (hs.getNguoiGui() != null) {
	            existingHS_Gui_Nhan.setNguoiGui(hs.getNguoiGui());
	        }
	        if (hs.getNguoiNhan() != null) {
	            existingHS_Gui_Nhan.setNguoiNhan(hs.getNguoiNhan());
	        }
	        if (hs.getNgayGui() != null) {
	            existingHS_Gui_Nhan.setNgayGui(hs.getNgayGui());
	        }
	        if (hs.getNgayNhan() != null) {
	            existingHS_Gui_Nhan.setNgayNhan(hs.getNgayNhan());
	        }
	        if (hs.getIdX() != null) {
	            existingHS_Gui_Nhan.setIdX(hs.getIdX());
	        }
	        
	        rHS_Gui_Nhan.save(existingHS_Gui_Nhan);
	        
	        return new AppResponse("Chỉnh sửa hồ sơ gửi nhận thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteHS_Gui_Nhan(Integer id) {
        try {
            HS_Gui_Nhan existingHS_Gui_Nhan = rHS_Gui_Nhan.findById(id).orElse(null);
            if (existingHS_Gui_Nhan == null) {
                return new AppResponse("Hồ sơ gửi nhận không tồn tại trong hệ thống", false);
            }
            
            rHS_Gui_Nhan.delete(existingHS_Gui_Nhan);
            
            return new AppResponse("Xóa Hồ sơ gửi nhận thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
