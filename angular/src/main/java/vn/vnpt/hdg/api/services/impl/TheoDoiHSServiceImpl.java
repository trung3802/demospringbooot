package vn.vnpt.hdg.api.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.payload.response.TheoDoiHSOutput;
import vn.vnpt.hdg.api.repository.TT_Ho_SoRepository;
import vn.vnpt.hdg.api.services.TheoDoiHSService;

@Service
public class TheoDoiHSServiceImpl implements TheoDoiHSService {
	
	@Autowired
	TT_Ho_SoRepository rTT_Ho_So;
	
	@Override
	public List<TheoDoiHSOutput> getTheoDoiHS(HoSoRequest tdhs) {
	    List<Object[]> results = rTT_Ho_So.getTheoDoiHS(tdhs.getFromDate(), tdhs.getToDate(), tdhs.getIdDonVi());
	    List<TheoDoiHSOutput> dtos = new ArrayList<>();

	    for (Object[] result : results) {
	    	String tenDonVi = (String) result[0];
	    	Object soLuongMo = (Object) result[1];
	    	Object soLuongDong = (Object) result[2];
	    	Object soLuongLuuTru = (Object) result[3];
	    	Object soLuongVuaGui = (Object) result[4];
	    	Object soLuongDuyet = (Object) result[5];
	    	Object soLuongTraLai = (Object) result[6];
	        TheoDoiHSOutput dto = new TheoDoiHSOutput(tenDonVi, soLuongMo, soLuongDong, soLuongLuuTru, soLuongVuaGui, soLuongDuyet, soLuongTraLai);
	        dtos.add(dto);
	    }
	    return dtos;
	}
}