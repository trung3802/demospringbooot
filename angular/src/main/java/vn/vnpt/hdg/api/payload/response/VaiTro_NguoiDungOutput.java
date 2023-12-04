package vn.vnpt.hdg.api.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaiTro_NguoiDungOutput {
    private Integer id;
    private String ma;
    private String ten;
    private String tenDonVi;
    private String tenVaiTro;
    private Integer donViID;
    private Integer vaiTroID;
    private Integer nguoiDungID;

    public VaiTro_NguoiDungOutput(Integer id, String ma, String ten, String tenDonVi, String tenVaiTro, Integer donViID, Integer vaiTroID, Integer nguoiDungID) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.tenDonVi = tenDonVi;
        this.tenVaiTro = tenVaiTro;
        this.donViID = donViID;
        this.vaiTroID = vaiTroID;
        this.nguoiDungID = nguoiDungID;
    }
}