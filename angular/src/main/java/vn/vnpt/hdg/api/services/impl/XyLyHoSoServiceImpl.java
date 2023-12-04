package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.models.ChucDanh;
import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.models.TT_Ho_So;
import vn.vnpt.hdg.api.models.VanBan_DoMat;
import vn.vnpt.hdg.api.payload.response.AppResponse;
import vn.vnpt.hdg.api.payload.response.HoSoOutput;
import vn.vnpt.hdg.api.payload.response.NguoiDungChucDanhOutput;
import vn.vnpt.hdg.api.repository.DM_Kieu_Bao_QuanRepository;
import vn.vnpt.hdg.api.repository.TT_Ho_SoRepository;
import vn.vnpt.hdg.api.repository.VanBan_DoMatRepository;
import vn.vnpt.hdg.api.services.XuLyHoSoService;

@Service
public class XyLyHoSoServiceImpl implements XuLyHoSoService{
	
	@Autowired
	TT_Ho_SoRepository rTT_Ho_So;
	
	@Autowired
	DM_Kieu_Bao_QuanRepository rDM_Kieu_Bao_Quan;
	
	@Autowired
	VanBan_DoMatRepository rVanBan_DoMat;
	
	@Override
	public List<HoSoOutput> getHoSoXuLys() {
		List<TT_Ho_So> dshs = rTT_Ho_So.getHoSoXyLys();
	    List<DM_Kieu_Bao_Quan> kbq = rDM_Kieu_Bao_Quan.findAll();
	    List<VanBan_DoMat> vbdm = rVanBan_DoMat.findAll();
	    List<HoSoOutput> dtos = new ArrayList<>();
	    for (TT_Ho_So hs : dshs) {
	    	HoSoOutput hoSoOutput = new HoSoOutput();
			BeanUtils.copyProperties(hs, hoSoOutput);
			DM_Kieu_Bao_Quan kieuBaoQuan = kbq.stream().filter(item -> item.getId().equals(hs.getIdKieuBaoQuan())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(kieuBaoQuan)) {
				hoSoOutput.setTenKieuBaoQuan(kieuBaoQuan.getTenKieu());
			}
			VanBan_DoMat doMat = vbdm.stream().filter(item -> item.getId().equals(hs.getIdDoMat())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(doMat)) {
				hoSoOutput.setTenDoMat(doMat.getTen());
			}
			dtos.add(hoSoOutput);
	    }
	    return dtos;
	}
	
	@Override
	public AppResponse editHoSoXuLy(TT_Ho_So hs) {
	    try {        
	        Optional<TT_Ho_So> existingTT_Ho_SoOptional = rTT_Ho_So.findById(hs.getId());
	        if (!existingTT_Ho_SoOptional.isPresent()) {
	            return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        TT_Ho_So existingTT_Ho_So = existingTT_Ho_SoOptional.get();
	        
	        if (!ObjectUtils.isEmpty(hs.getMaHoSo())) {
	            existingTT_Ho_So.setMaHoSo(hs.getMaHoSo());
	        }
	        else {
	        	return new AppResponse("Mã hồ sơ không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getTieuDe())) {
	            existingTT_Ho_So.setTieuDe(hs.getTieuDe());
	        }
	        else {
	        	return new AppResponse("Tiêu đề không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getTongSoTrang())) {
	            existingTT_Ho_So.setTongSoTrang(hs.getTongSoTrang());
	        }
	        else {
	        	return new AppResponse("Tổng số trang không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getTuKhoa())) {
	            existingTT_Ho_So.setTuKhoa(hs.getTuKhoa());
	        }
	        else {
	        	return new AppResponse("Từ khoá không được để trống");
	        }
	        
	        if (!ObjectUtils.isEmpty(hs.getKyHieuHS())) {
	            existingTT_Ho_So.setKyHieuHS(hs.getKyHieuHS());
	        }
	        else {
	        	return new AppResponse("Ký hiệu hồ sơ không được để trống");
	        }
	        
	        if (hs.getThoiHanBaoQuan() != null) {
	            existingTT_Ho_So.setThoiHanBaoQuan(hs.getThoiHanBaoQuan());
	        }
	        if (hs.getCheDoSuDung() != null) {
	            existingTT_Ho_So.setCheDoSuDung(hs.getCheDoSuDung());
	        }
	        if (hs.getTgBatDau() != null) {
	            existingTT_Ho_So.setTgBatDau(hs.getTgBatDau());
	        }
	        if (hs.getTgKetThuc() != null) {
	            existingTT_Ho_So.setTgKetThuc(hs.getTgKetThuc());
	        }
	        if (hs.getTongSoVB() != null) {
	            existingTT_Ho_So.setTongSoVB(hs.getTongSoVB());
	        }
	        if (hs.getGhiChu() != null) {
	            existingTT_Ho_So.setGhiChu(hs.getGhiChu());
	        }
	        if (hs.getTrangThai() != null) {
	            existingTT_Ho_So.setTrangThai(hs.getTrangThai());
	        }
	        if (hs.getIdDonVi() != null) {
	            existingTT_Ho_So.setIdDonVi(hs.getIdDonVi());
	        }
	        if (hs.getIdDoMat() != null) {
	            existingTT_Ho_So.setIdDoMat(hs.getIdDoMat());
	        }
	        if (hs.getIdKieuBaoQuan() != null) {
	            existingTT_Ho_So.setIdKieuBaoQuan(hs.getIdKieuBaoQuan());
	        }
	        if (hs.getIdDonViNhan() != null) {
	            existingTT_Ho_So.setIdDonViNhan(hs.getIdDonViNhan());
	        }
	        if (hs.getTrangThaiNhan() != null) {
	            existingTT_Ho_So.setTrangThaiNhan(hs.getTrangThaiNhan());
	        }
	        if (hs.getLoaiLuuTru() != null) {
	            existingTT_Ho_So.setLoaiLuuTru(hs.getLoaiLuuTru());
	        }
	        if (hs.getNgayTao() != null) {
	            existingTT_Ho_So.setNgayTao(hs.getNgayTao());
	        }
	        
	        rTT_Ho_So.save(existingTT_Ho_So);
	        
	        return new AppResponse("Chỉnh sửa hồ sơ thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
    public AppResponse deleteHoSoXuLy(Integer id) {
        try {
            TT_Ho_So existingTT_Ho_So = rTT_Ho_So.findById(id).orElse(null);
            if (existingTT_Ho_So == null) {
                return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
            }
            
            rTT_Ho_So.delete(existingTT_Ho_So);
            
            return new AppResponse("Xóa hồ sơ thành công", true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
	
	@Override
	public AppResponse moHoSoXuLy(Integer id) {
	    try {        
	        Optional<TT_Ho_So> existingTT_Ho_SoOptional = rTT_Ho_So.findById(id);
	        if (!existingTT_Ho_SoOptional.isPresent()) {
	            return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        TT_Ho_So existingTT_Ho_So = existingTT_Ho_SoOptional.get();
	        
	        existingTT_Ho_So.setTrangThai(0);
	        
	        rTT_Ho_So.save(existingTT_Ho_So);
	        
	        return new AppResponse("Mở hồ sơ thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
	public AppResponse dongHoSoXuLy(Integer id) {
	    try {        
	        Optional<TT_Ho_So> existingTT_Ho_SoOptional = rTT_Ho_So.findById(id);
	        if (!existingTT_Ho_SoOptional.isPresent()) {
	            return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        TT_Ho_So existingTT_Ho_So = existingTT_Ho_SoOptional.get();
	        
	        existingTT_Ho_So.setTrangThai(1);
	        
	        rTT_Ho_So.save(existingTT_Ho_So);
	        
	        return new AppResponse("Đóng hồ sơ thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
	
	@Override
	public AppResponse luuTruHoSoXuLy(Integer id) {
	    try {        
	        Optional<TT_Ho_So> existingTT_Ho_SoOptional = rTT_Ho_So.findById(id);
	        if (!existingTT_Ho_SoOptional.isPresent()) {
	            return new AppResponse("Hồ sơ không tồn tại trong hệ thống", false);
	        }        

	        TT_Ho_So existingTT_Ho_So = existingTT_Ho_SoOptional.get();
	        
	        existingTT_Ho_So.setTrangThai(2);
	        
	        rTT_Ho_So.save(existingTT_Ho_So);
	        
	        return new AppResponse("Lưu trữ hồ sơ thành công", true);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new AppResponse("Đã xảy ra lỗi", false);
	    }
	}
}
