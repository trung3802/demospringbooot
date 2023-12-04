package vn.vnpt.hdg.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.ThamSo;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.repository.ThamSoRepository;
import vn.vnpt.hdg.api.services.ThamSoService;

@Service
public class ThamSoServiceImpl implements ThamSoService {
	
	@Autowired
	ThamSoRepository rThamSo;

	@Override
	public List<ThamSo> getThamSos() {
		List<ThamSo> cds = rThamSo.findAll();
		return cds;
	}
	
	@Override
	public AppResponse addThamSo(ThamSo ts) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(ts.getMa())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu mã không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(ts.getTen())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tên không được để trống");
		        return appResponse;
		    }
		    ThamSo existingThamSoByMa = rThamSo.findByMa(ts.getMa());
	        if (existingThamSoByMa != null) {
	            return new AppResponse("Mã đã có trong hệ thống", false);
	        }

		    // Insert data
		    rThamSo.save(ts);
		    
		    return new AppResponse("Thêm tham số thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editThamSo(ThamSo ts) {
	    try {        
	        Optional<ThamSo> existingThamSoOptional = rThamSo.findById(ts.getId());
	        if (!existingThamSoOptional.isPresent()) {
	            return new AppResponse("Tham số không tồn tại trong hệ thống", false);
	        }        

	        ThamSo existingThamSo = existingThamSoOptional.get();
	        
	        if (!ObjectUtils.isEmpty(ts.getMa())) {
	            existingThamSo.setMa(ts.getMa());
	        }
	        else {
	        	return new AppResponse("Mã không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(ts.getTen())) {
	            existingThamSo.setTen(ts.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (ts.getGiaTri() != null) {
	            existingThamSo.setGiaTri(ts.getGiaTri());
	        }
	        if (ts.getTrangThai() != null) {
	            existingThamSo.setTrangThai(ts.getTrangThai());
	        }
	        if (ts.getMoTa() != null) {
	            existingThamSo.setMoTa(ts.getMoTa());
	        }
	        
	        rThamSo.save(existingThamSo);
	        
	        return new AppResponse("Chỉnh sửa tham số thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteThamSo(Integer id) {
        try {
            ThamSo existingThamSo = rThamSo.findById(id).orElse(null);
            if (existingThamSo == null) {
                return new AppResponse("Tham số không tồn tại trong hệ thống", false);
            }
            
            rThamSo.delete(existingThamSo);
            
            return new AppResponse("Xóa Tham số thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
