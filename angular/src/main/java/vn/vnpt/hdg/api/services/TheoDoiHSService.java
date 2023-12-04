package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.payload.response.HoSoRequest;
import vn.vnpt.hdg.api.payload.response.TheoDoiHSOutput;

public interface TheoDoiHSService {
	List<TheoDoiHSOutput> getTheoDoiHS(HoSoRequest tdhs);
}