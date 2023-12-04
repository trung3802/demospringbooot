package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.VanBan;
import vn.vnpt.hdg.api.payload.response.VanBanOutput;

public interface TraCuuVanBanService {
	List<VanBanOutput> getVanBanToanDonVis(VanBan vb);
}