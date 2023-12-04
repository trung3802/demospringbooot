package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.models.DM_Kieu_Bao_Quan;
import vn.vnpt.hdg.api.models.TT_Ho_So;
import vn.vnpt.hdg.api.models.VanBan_DoMat;
import vn.vnpt.hdg.api.payload.response.HoSoOutput;
import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.repository.DM_Kieu_Bao_QuanRepository;
import vn.vnpt.hdg.api.repository.TT_Ho_SoRepository;
import vn.vnpt.hdg.api.repository.VanBan_DoMatRepository;
import vn.vnpt.hdg.api.services.TraCuuHoSoService;

@Service
public class TraCuuHoSoServiceImpl implements TraCuuHoSoService {
	
	@Autowired
	TT_Ho_SoRepository rTT_Ho_So;
	
	@Autowired
	VanBan_DoMatRepository rVanBan_DoMat;
	
	@Autowired
	DM_Kieu_Bao_QuanRepository rDM_Kieu_Bao_Quan;
	
	@Override
	public List<HoSoOutput> getDanhSachHoSos(HoSoRequest hs) {
		List<TT_Ho_So> hss = rTT_Ho_So.getDanhSachHoSos(hs.getMaHoSo(), hs.getTuKhoa(), hs.getTrangThaiNhan(), hs.getIdDoMat(), hs.getIdKieuBaoQuan(), hs.getTrangThai(), hs.getFromDate(), hs.getToDate());
	    List<VanBan_DoMat> vbLoais = rVanBan_DoMat.findAll();
	    List<DM_Kieu_Bao_Quan> kbqs = rDM_Kieu_Bao_Quan.findAll();
	    List<HoSoOutput> dtos = new ArrayList<>();
	    for (TT_Ho_So tt_Ho_So : hss) {
	    	HoSoOutput TT_Ho_SoOutput = new HoSoOutput();
			BeanUtils.copyProperties(tt_Ho_So, TT_Ho_SoOutput);
			VanBan_DoMat vanBan_DoMat = vbLoais.stream().filter(item -> item.getId().equals(tt_Ho_So.getIdDoMat())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan_DoMat)) {
				TT_Ho_SoOutput.setTenDoMat(vanBan_DoMat.getTen());
			}
			DM_Kieu_Bao_Quan dm_Kieu_Bao_Quan = kbqs.stream().filter(item -> item.getId().equals(tt_Ho_So.getIdKieuBaoQuan())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan_DoMat)) {
				TT_Ho_SoOutput.setTenKieuBaoQuan(dm_Kieu_Bao_Quan.getTenKieu());
			}
			dtos.add(TT_Ho_SoOutput);
	    }
	    return dtos;
	}
}
