package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.Ho_So_VB;
import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoVanBanOutput;
import vn.vnpt.hdg.api.repository.Ho_So_VBRepository;
import vn.vnpt.hdg.api.repository.VanBanRepository;
import vn.vnpt.hdg.api.services.HoSoVanBanService;

@Service
public class HoSoVanBanServiceImpl implements HoSoVanBanService {
	
	@Autowired
	Ho_So_VBRepository rHo_So_VB;

	@Autowired
	VanBanRepository rVanBan;

	@Override
	public List<HoSoVanBanOutput> getVanBanByHoSos(Ho_So_VB hsvb) {
		List<Ho_So_VB> hsvbs = rHo_So_VB.getVanBanByHoSos(hsvb.getIdHoSo());
	    List<VanBan> vbs = rVanBan.findAll();
	    List<HoSoVanBanOutput> dtos = new ArrayList<>();
	    for (Ho_So_VB hosovb : hsvbs) {
	    	HoSoVanBanOutput hoSoVanBanOutput = new HoSoVanBanOutput();
			BeanUtils.copyProperties(hosovb, hoSoVanBanOutput);
			VanBan vanBan = vbs.stream().filter(item -> item.getId().equals(hosovb.getIdVanBan())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan)) {
				hoSoVanBanOutput.setKyHieu(vanBan.getKyHieu());
				hoSoVanBanOutput.setCoQuanBanHanh(vanBan.getCoQuanBanHanh());
				hoSoVanBanOutput.setLanhDaoKy(vanBan.getLanhDaoKy());
			}
			dtos.add(hoSoVanBanOutput);
	    }
	    return dtos;
	}
	
	@Override
	public AppResponse addHoSoVanBan(Ho_So_VB hsvb) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(hsvb.getIdHoSo())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu hồ sơ không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(hsvb.getIdVanBan())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu văn bản không được để trống");
		        return appResponse;
		    }
		    
		    // Insert data
		    rHo_So_VB.save(hsvb);
		    
		    return new AppResponse("Thêm hồ sơ văn bản thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editHoSoVanBan(Ho_So_VB hsvb) {
	    try {        
	        Optional<Ho_So_VB> existingHo_So_VBOptional = rHo_So_VB.findById(hsvb.getId());
	        if (!existingHo_So_VBOptional.isPresent()) {
	            return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        Ho_So_VB existingHo_So_VB = existingHo_So_VBOptional.get();
	        
	        if (!ObjectUtils.isEmpty(hsvb.getIdHoSo())) {
	            existingHo_So_VB.setIdHoSo(hsvb.getIdHoSo());
	        }
	        else {
	        	return new AppResponse("Mã hồ sơ không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hsvb.getIdVanBan())) {
	            existingHo_So_VB.setIdVanBan(hsvb.getIdVanBan());
	        }
	        else {
	        	return new AppResponse("Mã văn bản không được để trống");
	        }
	        
	        if (hsvb.getIdX() != null) {
	            existingHo_So_VB.setIdX(hsvb.getIdX());
	        }
	        
	        rHo_So_VB.save(existingHo_So_VB);
	        
	        return new AppResponse("Chỉnh sửa hồ sơ văn bản thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteHoSoVanBan(Integer id) {
        try {
            Ho_So_VB existingHo_So_VB = rHo_So_VB.findById(id).orElse(null);
            if (existingHo_So_VB == null) {
                return new AppResponse("Hồ sơ văn bản không tồn tại trong hệ thống", false);
            }
            
            rHo_So_VB.delete(existingHo_So_VB);
            
            return new AppResponse("Xóa hồ sơ văn bản thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
