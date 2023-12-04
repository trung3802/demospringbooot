package vn.vnpt.hdg.api.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheoDoiHSOutput {
	private String tenDonVi;
	private Object soLuongMo;
	private Object soLuongDong;
	private Object soLuongLuuTru;
	private Object soLuongVuaGui;
	private Object soLuongDuyet;
	private Object soLuongTraLai;
	
    public TheoDoiHSOutput(String tenDonVi, Object soLuongMo, Object soLuongDong, Object soLuongLuuTru, Object soLuongVuaGui, Object soLuongDuyet, Object soLuongTraLai) {
        this.tenDonVi = tenDonVi;
        this.soLuongMo = soLuongMo;
        this.soLuongDong = soLuongDong;
        this.soLuongLuuTru = soLuongLuuTru;
        this.soLuongVuaGui = soLuongVuaGui;
        this.soLuongDuyet = soLuongDuyet;
        this.soLuongTraLai = soLuongTraLai;
    }
}