package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.constants.AppError;
import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.models.VanBan_Loai;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.VanBanOutput;
import vn.vnpt.hdg.api.repository.VanBanRepository;
import vn.vnpt.hdg.api.repository.VanBan_LoaiRepository;
import vn.vnpt.hdg.api.services.LuuTruVanBanService;

@Service
public class LuuTruVanBanServiceImpl implements LuuTruVanBanService {
	
	@Autowired
	VanBanRepository rVanBan;
	
	@Autowired
	VanBan_LoaiRepository rVbLoai;

	@Override
	public List<VanBanOutput> getVanBanDens() {
	    List<VanBan> vbs = rVanBan.getVanBanDens();
	    List<VanBan_Loai> vbLoais = rVbLoai.findAll();
	    List<VanBanOutput> dtos = new ArrayList<>();
	    for (VanBan vb : vbs) {
			VanBanOutput vanBanOutput = new VanBanOutput();
			BeanUtils.copyProperties(vb, vanBanOutput);
			VanBan_Loai vanBan_Loai = vbLoais.stream().filter(item -> item.getId().equals(vb.getVanBanLoaiID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan_Loai)) {
				vanBanOutput.setTenLoaiVB(vanBan_Loai.getTen());
			}
			dtos.add(vanBanOutput);
	    }
	    return dtos;
	}
	
	@Override
	public List<VanBanOutput> getVanBanDis() {
	    List<VanBan> vbs = rVanBan.getVanBanDis();
	    List<VanBan_Loai> vbLoais = rVbLoai.findAll();
	    List<VanBanOutput> dtos = new ArrayList<>();
	    for (VanBan vb : vbs) {
			VanBanOutput vanBanOutput = new VanBanOutput();
			BeanUtils.copyProperties(vb, vanBanOutput);
			VanBan_Loai vanBan_Loai = vbLoais.stream().filter(item -> item.getId().equals(vb.getVanBanLoaiID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan_Loai)) {
				vanBanOutput.setTenLoaiVB(vanBan_Loai.getTen());
			}
			dtos.add(vanBanOutput);
	    }
	    return dtos;
	}
	
	@Override
	public AppResponse addVanBan(VanBan vb) {
		AppResponse appResponse = new AppResponse();
		try {
			// Validate data
			VanBan existingVanBanByKyHieu = rVanBan.findByKyHieu(vb.getKyHieu());
	        if (existingVanBanByKyHieu != null) {
	            return new AppResponse("Ký hiệu đã có trong hệ thống", false);
	        }		    
		    if (ObjectUtils.isEmpty(vb.getKyHieu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu ký hiệu không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getCoQuanBanHanh())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu cơ quan ban hành không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getTrichYeu())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu trích yếu không được để trống");
		        return appResponse;
		    }	
		    if (ObjectUtils.isEmpty(vb.getVanBanLoaiID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu loại không được để trống");
		        return appResponse;
		    }
		    if (ObjectUtils.isEmpty(vb.getVanBanLinhVucID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu lĩnh vực không được để trống");
		        return appResponse;
		    }	
		    if (ObjectUtils.isEmpty(vb.getVanBanTinhTrangID())) {
		        appResponse.setCode(AppError.INVALID_DATA);
		        appResponse.setMessage("Dữ liệu tình trạng không được để trống");
		        return appResponse;
		    }	
		    // Insert data
		    rVanBan.save(vb);
		    
		    return new AppResponse("Thêm văn bản thành công", true);
		    
		} catch (Exception e) {
		    appResponse.setCode(AppError.INTERNAL_ERROR);
		    appResponse.setMessage("Đã xảy ra lỗi");
		    e.printStackTrace();
		    return appResponse;
		}
	}

	@Override
	public AppResponse editVanBan(VanBan vb) {
	    try {        
	        Optional<VanBan> existingVanBanOptional = rVanBan.findById(vb.getId());
	        if (!existingVanBanOptional.isPresent()) {
	            return new AppResponse("Văn bản không tồn tại trong hệ thống", false);
	        }        

	        VanBan existingVanBan = existingVanBanOptional.get();
	        
	        if (!ObjectUtils.isEmpty(vb.getKyHieu())) {
	            existingVanBan.setKyHieu(vb.getKyHieu());
	        }
	        else {
	        	return new AppResponse("Ký hiệu không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vb.getCoQuanBanHanh())) {
	            existingVanBan.setCoQuanBanHanh(vb.getCoQuanBanHanh());
	        }
	        else {
	        	return new AppResponse("Cơ quan ban hành không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(vb.getTrichYeu())) {
	            existingVanBan.setTrichYeu(vb.getTrichYeu());
	        }
	        else {
	        	return new AppResponse("Trích yếu không được để trống");
	        }
	        
	        if (vb.getVbLienQuan() != null) {
	            existingVanBan.setVbLienQuan(vb.getVbLienQuan());
	        }
	        if (vb.getVanBanLoaiID() != null) {
	            existingVanBan.setVanBanLoaiID(vb.getVanBanLoaiID());
	        }	        
	        if (vb.getVanBanLinhVucID() != null) {
	            existingVanBan.setVanBanLinhVucID(vb.getVanBanLinhVucID());
	        }
	        if (vb.getVanBanDoKhanID() != null) {
	            existingVanBan.setVanBanDoKhanID(vb.getVanBanDoKhanID());
	        }
	        if (vb.getVanBanDoMatID() != null) {
	            existingVanBan.setVanBanDoMatID(vb.getVanBanDoMatID());
	        }
	        if (vb.getVanBanKieuID() != null) {
	            existingVanBan.setVanBanKieuID(vb.getVanBanKieuID());
	        }
	        if (vb.getNgayDen() != null) {
	            existingVanBan.setNgayDen(vb.getNgayDen());
	        }
	        if (vb.getNoiGuiDen() != null) {
	            existingVanBan.setNoiGuiDen(vb.getNoiGuiDen());
	        }
	        if (vb.getNoiGuiDenID() != null) {
	            existingVanBan.setNoiGuiDenID(vb.getNoiGuiDenID());
	        }
	        if (vb.getNoiGuiKhac() != null) {
	            existingVanBan.setNoiGuiKhac(vb.getNoiGuiKhac());
	        }
	        if (vb.getLanhDaoKy() != null) {
	            existingVanBan.setLanhDaoKy(vb.getLanhDaoKy());
	        }
	        if (vb.getChucVu() != null) {
	            existingVanBan.setChucVu(vb.getChucVu());
	        }
	        if (vb.getChucDanhID() != null) {
	            existingVanBan.setChucDanhID(vb.getChucDanhID());
	        }
	        if (vb.getNgayGuiDi() != null) {
	            existingVanBan.setNgayGuiDi(vb.getNgayGuiDi());
	        }
	        if (vb.getNoiGuiDiKhac() != null) {
	            existingVanBan.setNoiGuiDiKhac(vb.getNoiGuiDiKhac());
	        }
	        if (vb.getNoiGuiDiIDs() != null) {
	            existingVanBan.setNoiGuiDiIDs(vb.getNoiGuiDiIDs());
	        }
	        if (vb.getNgayPhatHanh() != null) {
	            existingVanBan.setNgayPhatHanh(vb.getNgayPhatHanh());
	        }
	        if (vb.getSoTrang() != null) {
	            existingVanBan.setSoTrang(vb.getSoTrang());
	        }
	        if (vb.getDaNhan() != null) {
	            existingVanBan.setDaNhan(vb.getDaNhan());
	        }
	        if (vb.getDonViID() != null) {
	            existingVanBan.setDonViID(vb.getDonViID());
	        }
	        if (vb.getNguoiNhapID() != null) {
	            existingVanBan.setNguoiNhapID(vb.getNguoiNhapID());
	        }
	        if (vb.getTgNhap() != null) {
	            existingVanBan.setTgNhap(vb.getTgNhap());
	        }
	        if (vb.getGhiChu() != null) {
	            existingVanBan.setGhiChu(vb.getGhiChu());
	        }
	        if (vb.getVanBanTinhTrangID() != null) {
	            existingVanBan.setVanBanTinhTrangID(vb.getVanBanTinhTrangID());
	        }
	        if (vb.getChiaSeDichDanhIDS() != null) {
	            existingVanBan.setChiaSeDichDanhIDS(vb.getChiaSeDichDanhIDS());
	        }
	        if (vb.getCongBo() != null) {
	            existingVanBan.setCongBo(vb.getCongBo());
	        }
	        if (vb.getTuKhoa() != null) {
	            existingVanBan.setTuKhoa(vb.getTuKhoa());
	        }
	        if (vb.getNgayChiaSe() != null) {
	            existingVanBan.setNgayChiaSe(vb.getNgayChiaSe());
	        }
	        if (vb.getNgayKetThucChiaSe() != null) {
	            existingVanBan.setNgayKetThucChiaSe(vb.getNgayKetThucChiaSe());
	        }
	        if (vb.getMaVanBan() != null) {
	            existingVanBan.setMaVanBan(vb.getMaVanBan());
	        }
	        
	        rVanBan.save(existingVanBan);
	        
	        return new AppResponse("Chỉnh sửa văn bản thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteVanBan(Integer id) {
        try {
            VanBan existingVanBan = rVanBan.findById(id).orElse(null);
            if (existingVanBan == null) {
                return new AppResponse("Văn bản không tồn tại trong hệ thống", false);
            }
            
            rVanBan.delete(existingVanBan);
            
            return new AppResponse("Xóa văn bản thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
