package vn.vnpt.hdg.api.services;

import java.util.List;

import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.payload.response.DonViNguoiDungOutput;

public interface DonViNguoiDungService {
	List<DonViNguoiDungOutput> getDonViByNguoiDung(NguoiDung nd);
}