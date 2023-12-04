package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VaiTro_NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.VaiTro_NguoiDungOutput;
import vn.vnpt.hdg.api.repository.VaiTro_NguoiDungRepository;
import vn.vnpt.hdg.api.services.VaiTro_NguoiDungService;

@Service
public class VaiTro_NguoiDungServiceImpl implements VaiTro_NguoiDungService {
	
	@Autowired
	VaiTro_NguoiDungRepository rVaiTro_NguoiDung;

	@Override
	public List<VaiTro_NguoiDungOutput> getVaiTro_NguoiDungs() {
	    List<Object[]> results = rVaiTro_NguoiDung.getVaiTroNguoiDungs();
	    List<VaiTro_NguoiDungOutput> dtos = new ArrayList<>();

	    for (Object[] result : results) {
	        Integer id = (Integer) result[0];
	        String ma = (String) result[1];
	        String ten = (String) result[2];
	        String tenDonVi = (String) result[3];
	        String tenVaiTro = (String) result[4];
	        Integer donViID = (Integer) result[5];
	        Integer vaiTroID = (Integer) result[6];
	        Integer nguoiDungID = (Integer) result[7];
	        VaiTro_NguoiDungOutput dto = new VaiTro_NguoiDungOutput(id, ma, ten, tenDonVi, tenVaiTro, donViID, vaiTroID, nguoiDungID);
	        dtos.add(dto);
	    }
	    return dtos;
	}
	
	@Override
	public AppResponse addVaiTro_NguoiDung(VaiTro_NguoiDung vtnd) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(vtnd.getVaiTroID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu vai trò không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vtnd.getNguoiDungID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu người dùng không được để trống");
		        return appResponse;
		    }

		    // Insert data
		    rVaiTro_NguoiDung.save(vtnd);
		    
		    return new AppResponse("Thêm vai trò người dùng thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editVaiTro_NguoiDung(VaiTro_NguoiDung vtnd) {
	    try {        
	        Optional<VaiTro_NguoiDung> existingVaiTro_NguoiDungOptional = rVaiTro_NguoiDung.findById(vtnd.getId());
	        if (!existingVaiTro_NguoiDungOptional.isPresent()) {
	            return new AppResponse("Vai trò người dùng không tồn tại trong hệ thống", false);
	        }        

	        VaiTro_NguoiDung existingVaiTro_NguoiDung = existingVaiTro_NguoiDungOptional.get();
	        
	        if (vtnd.getVaiTroID() != null) {
	            existingVaiTro_NguoiDung.setVaiTroID(vtnd.getVaiTroID());
	        }
	        
	        if (vtnd.getNguoiDungID() != null) {
	            existingVaiTro_NguoiDung.setNguoiDungID(vtnd.getNguoiDungID());
	        }	        
	        
	        rVaiTro_NguoiDung.save(existingVaiTro_NguoiDung);
	        
	        return new AppResponse("Chỉnh sửa vai trò người dùng thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVaiTro_NguoiDung(Integer id) {
        try {
            VaiTro_NguoiDung existingVaiTro_NguoiDung = rVaiTro_NguoiDung.findById(id).orElse(null);
            if (existingVaiTro_NguoiDung == null) {
                return new AppResponse("Vai trò người dùng không tồn tại trong hệ thống", false);
            }
            
            rVaiTro_NguoiDung.delete(existingVaiTro_NguoiDung);
            
            return new AppResponse("Xóa vai trò người dùng thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
