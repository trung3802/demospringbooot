package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.Quyen_VaiTro;
import vn.vnpt.hdg.api.models.Quyen;
import vn.vnpt.hdg.api.models.VaiTro;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.Quyen_VaiTroOutput;
import vn.vnpt.hdg.api.repository.QuyenRepository;
import vn.vnpt.hdg.api.repository.Quyen_VaiTroRepository;
import vn.vnpt.hdg.api.repository.VaiTroRepository;
import vn.vnpt.hdg.api.services.Quyen_VaiTroService;

@Service
public class Quyen_VaiTroServiceImpl implements Quyen_VaiTroService {
	
	@Autowired
	Quyen_VaiTroRepository rQuyen_VaiTro;
	
	@Autowired
	VaiTroRepository rVaiTro;
	
	@Autowired
	QuyenRepository rQuyen;

	@Override
	public List<Quyen_VaiTroOutput> getQuyen_VaiTros() {
	    List<Quyen_VaiTro> qvts = rQuyen_VaiTro.getQuyenVaiTros();
	    List<Quyen> quyens = rQuyen.findAll();
	    List<VaiTro> vaitros = rVaiTro.findAll();
	    List<Quyen_VaiTroOutput> dtos = new ArrayList<>();
	    for (Quyen_VaiTro qtvs : qvts) {
	    	Quyen_VaiTroOutput quyen_VaiTroOutput = new Quyen_VaiTroOutput();
			BeanUtils.copyProperties(qtvs, quyen_VaiTroOutput);
			Quyen quyen = quyens.stream().filter(item -> item.getId().equals(qtvs.getQuyenID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(quyen)) {
				quyen_VaiTroOutput.setTenQuyen(quyen.getTen());
			}
			VaiTro vaitro = vaitros.stream().filter(item -> item.getId().equals(qtvs.getVaiTroID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vaitro)) {
				quyen_VaiTroOutput.setTenVaiTro(vaitro.getTen());
			}
			dtos.add(quyen_VaiTroOutput);
	    }
	    return dtos;
	}
	
	@Override
	public AppResponse addQuyen_VaiTro(Quyen_VaiTro qvt) {
		AppResponse appResponse = new AppResponse();
		try {
		    // Validate data
		    if (ObjectUtils.isEmpty(qvt.getVaiTroID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu vai trò không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(qvt.getQuyenID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu quyền không được để trống");
		        return appResponse;
		    }
		    // Insert data
		    rQuyen_VaiTro.save(qvt);
		    
		    return new AppResponse("Thêm quyền vai trò thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}
	
	@Override
	public AppResponse editQuyen_VaiTro(Quyen_VaiTro vtnd) {
	    try {        
	        Optional<Quyen_VaiTro> existingQuyen_VaiTroOptional = rQuyen_VaiTro.findById(vtnd.getId());
	        if (!existingQuyen_VaiTroOptional.isPresent()) {
	            return new AppResponse("Quyền vai trò không tồn tại trong hệ thống", false);
	        }        

	        Quyen_VaiTro existingQuyen_VaiTro = existingQuyen_VaiTroOptional.get();
	        
	        if (vtnd.getVaiTroID() != null) {
	            existingQuyen_VaiTro.setVaiTroID(vtnd.getVaiTroID());
	        }
	        
	        if (vtnd.getQuyenID() != null) {
	            existingQuyen_VaiTro.setQuyenID(vtnd.getQuyenID());
	        }	        
	        
	        rQuyen_VaiTro.save(existingQuyen_VaiTro);
	        
	        return new AppResponse("Chỉnh sửa quyền vai trò thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteQuyen_VaiTro(Integer id) {
        try {
            Quyen_VaiTro existingQuyen_VaiTro = rQuyen_VaiTro.findById(id).orElse(null);
            if (existingQuyen_VaiTro == null) {
                return new AppResponse("Quyền vai trò không tồn tại trong hệ thống", false);
            }
            
            rQuyen_VaiTro.delete(existingQuyen_VaiTro);
            
            return new AppResponse("Xóa quyền vai trò thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
