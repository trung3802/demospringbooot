package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import vn.vnpt.hdg.api.models.DonVi;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.DonViNguoiDungOutput;
import vn.vnpt.hdg.api.repository.DonViNguoiDungRepository;
import vn.vnpt.hdg.api.repository.DonViRepository;
import vn.vnpt.hdg.api.services.DonViNguoiDungService;

@Service
public class DonViNguoiDungServiceImpl implements DonViNguoiDungService {
	
	@Autowired
	DonViNguoiDungRepository rDonViNguoiDung;

	@Autowired
	DonViRepository rDonVi;

	@Override
	public List<DonViNguoiDungOutput> getDonViByNguoiDung(NguoiDung nd) {
	    List<NguoiDung> nds = rDonViNguoiDung.getDonViByNguoiDung(nd.getId());
	    List<DonVi> dvs = rDonVi.findAll();
	    List<DonViNguoiDungOutput> dtos = new ArrayList<>();
	    for (NguoiDung nguoidung : nds) {
	    	DonViNguoiDungOutput donviNguoiDungOutput = new DonViNguoiDungOutput();
			BeanUtils.copyProperties(nguoidung, donviNguoiDungOutput);
			DonVi donVi = dvs.stream().filter(item -> item.getId().equals(nguoidung.getDonViID())).findAny().orElse(null);
			if(!ObjectUtils.isEmpty(donVi)) {
				donviNguoiDungOutput.setTenDonVi(donVi.getTen());
			}
			dtos.add(donviNguoiDungOutput);
	    }
	    return dtos;
	}
}
