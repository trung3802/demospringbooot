package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.models.VanBan_Loai;
import vn.vnpt.hdg.api.payload.response.VanBanOutput;
import vn.vnpt.hdg.api.repository.VanBanRepository;
import vn.vnpt.hdg.api.repository.VanBan_LoaiRepository;
import vn.vnpt.hdg.api.services.TraCuuVanBanService;

@Service
public class TraCuuVanBanServiceImpl implements TraCuuVanBanService {
	
	@Autowired
	VanBanRepository rVanBan;
	
	@Autowired
	VanBan_LoaiRepository rVbLoai;

	@Override
	public List<VanBanOutput> getVanBanToanDonVis(VanBan vb) {
	    List<VanBan> vbs = rVanBan.getVanBanToanDonVis(vb.getKyHieu(), vb.getTrichYeu(), vb.getVanBanLoaiID(), vb.getVanBanLinhVucID(), vb.getVanBanDoKhanID(), vb.getVanBanDoMatID());
	    List<VanBan_Loai> vbLoais = rVbLoai.findAll();
	    List<VanBanOutput> dtos = new ArrayList<>();
	    for (VanBan vanban : vbs) {
			VanBanOutput vanBanOutput = new VanBanOutput();
			BeanUtils.copyProperties(vanban, vanBanOutput);
			VanBan_Loai vanBan_Loai = vbLoais.stream().filter(item -> item.getId().equals(vanban.getVanBanLoaiID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(vanBan_Loai)) {
				vanBanOutput.setTenLoaiVB(vanBan_Loai.getTen());
			}
			dtos.add(vanBanOutput);
	    }
	    return dtos;
	}
}
