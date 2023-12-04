package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.ChucDanh;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.NguoiDungChucDanhOutput;
import vn.vnpt.hdg.api.repository.ChucDanhRepository;
import vn.vnpt.hdg.api.repository.NguoiDungRepository;
import vn.vnpt.hdg.api.services.NguoiDungService;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
	
	@Autowired
	NguoiDungRepository rNguoiDung;

	@Autowired
	ChucDanhRepository rChucDanh;

	@Override
	public List<NguoiDungChucDanhOutput> getNguoiDungs() {
	    List<NguoiDung> nds = rNguoiDung.getNguoiDungs();
	    List<ChucDanh> cds = rChucDanh.findAll();
	    List<NguoiDungChucDanhOutput> dtos = new ArrayList<>();
	    for (NguoiDung nd : nds) {
	    	NguoiDungChucDanhOutput nguoiDungOutput = new NguoiDungChucDanhOutput();
			BeanUtils.copyProperties(nd, nguoiDungOutput);
			ChucDanh chucDanh = cds.stream().filter(item -> item.getId().equals(nd.getChucDanhID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(chucDanh)) {
				nguoiDungOutput.setTenChucDanh(chucDanh.getTen());
			}
			dtos.add(nguoiDungOutput);
	    }
	    return dtos;
	}
	
	@Override
	public List<NguoiDungChucDanhOutput> getNguoiDungByDonVis(Integer id) {
	    List<NguoiDung> nds = rNguoiDung.getNguoiDungByDonVis(id);
	    List<ChucDanh> cds = rChucDanh.findAll();
	    List<NguoiDungChucDanhOutput> dtos = new ArrayList<>();
	    for (NguoiDung nd : nds) {
	    	NguoiDungChucDanhOutput nguoiDungOutput = new NguoiDungChucDanhOutput();
			BeanUtils.copyProperties(nd, nguoiDungOutput);
			ChucDanh chucDanh = cds.stream().filter(item -> item.getId().equals(nd.getChucDanhID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(chucDanh)) {
				nguoiDungOutput.setTenChucDanh(chucDanh.getTen());
			}
			dtos.add(nguoiDungOutput);
	    }
	    return dtos;
	}
	
	@Override
    public AppResponse addNguoiDung(NguoiDung nd) {
        AppResponse appResponse = new AppResponse();
        try {
            // Validate data
            if (ObjectUtils.isEmpty(nd.getMa())) {
                appResponse.setCode(AppError.INVALID_DATA);
                appResponse.setMessage("Dữ liệu mã không được để trống");
                return appResponse;
            }
            if (ObjectUtils.isEmpty(nd.getMatKhau())) {
                appResponse.setCode(AppError.INVALID_DATA);
                appResponse.setMessage("Dữ liệu mật khẩu không được để trống");
                return appResponse;
            }
            if (ObjectUtils.isEmpty(nd.getTen())) {
                appResponse.setCode(AppError.INVALID_DATA);
                appResponse.setMessage("Dữ liệu tên không được để trống");
                return appResponse;
            }
            if (ObjectUtils.isEmpty(nd.getDonViID())) {
                appResponse.setCode(AppError.INVALID_DATA);
                appResponse.setMessage("Dữ liệu đơn vị không được để trống");
                return appResponse;
            }

            // Encrypt the password before saving
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(nd.getMatKhau());
            nd.setMatKhau(hashedPassword);

            NguoiDung existingNguoiDungByMa = rNguoiDung.findByMa(nd.getMa());
            if (existingNguoiDungByMa != null) {
                return new AppResponse("Tài khoản đã có trong hệ thống", false);
            }

            // Insert data
            rNguoiDung.save(nd);

            return new AppResponse("Thêm người dùng thành công", true);

        } catch (Exception e) {
            appResponse.setCode(AppError.INTERNAL_ERROR);
            appResponse.setMessage("Đã xảy ra lỗi");
            e.printStackTrace();
            return appResponse;
        }
    }


	@Override
	public AppResponse editNguoiDung(NguoiDung nd) {
	    try {        
	        Optional<NguoiDung> existingNguoiDungOptional = rNguoiDung.findById(nd.getId());
	        if (!existingNguoiDungOptional.isPresent()) {
	            return new AppResponse("Người dùng không tồn tại trong hệ thống", false);
	        }        

	        NguoiDung existingNguoiDung = existingNguoiDungOptional.get();
	        
	        if (!ObjectUtils.isEmpty(nd.getMa())) {
	            existingNguoiDung.setMa(nd.getMa());
	        }
	        else {
	        	return new AppResponse("Tài khoản không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(nd.getMatKhau())) {
	        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            String hashedPassword = passwordEncoder.encode(nd.getMatKhau());
	            nd.setMatKhau(hashedPassword);
	            existingNguoiDung.setMatKhau(nd.getMatKhau());	        
	        }else {
	        	return new AppResponse("Mật khẩu không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(nd.getTen())) {
	            existingNguoiDung.setTen(nd.getTen());
	        }
	        else {
	        	return new AppResponse("Tên không được để trống");
	        }
	        
	        if (nd.getDienThoai() != null) {
	            existingNguoiDung.setDienThoai(nd.getDienThoai());
	        }
	        if (nd.getMail() != null) {
	            existingNguoiDung.setMail(nd.getMail());
	        }
	        if (nd.getDonViID() != null) {
	            existingNguoiDung.setDonViID(nd.getDonViID());
	        }
	        if (nd.getChucDanhID() != null) {
	            existingNguoiDung.setChucDanhID(nd.getChucDanhID());
	        }
	        if (nd.getThongTin() != null) {
	            existingNguoiDung.setThongTin(nd.getThongTin());
	        }
	        if (nd.getTrangThai() != null) {
	            existingNguoiDung.setTrangThai(nd.getTrangThai());
	        }
	        
	        rNguoiDung.save(existingNguoiDung);
	        
	        return new AppResponse("Chỉnh sửa người dùng thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteNguoiDung(Integer id) {
        try {
            NguoiDung existingNguoiDung = rNguoiDung.findById(id).orElse(null);
            if (existingNguoiDung == null) {
                return new AppResponse("Người dùng không tồn tại trong hệ thống", false);
            }
            
            rNguoiDung.delete(existingNguoiDung);
            
            return new AppResponse("Xóa người dùng thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
