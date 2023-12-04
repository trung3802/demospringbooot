package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vanban")
@Data
public class VanBan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "KYHIEU")
    private String kyHieu;

    @Column(name = "VBLIENQUAN")
    private String vbLienQuan;

    @Column(name = "VANBAN_LOAIID")
    private Integer vanBanLoaiID;

    @Column(name = "VANBAN_LINHVUCID")
    private Integer vanBanLinhVucID;

    @Column(name = "TRICHYEU")
    private String trichYeu;

    @Column(name = "VANBAN_DOKHANID")
    private Integer vanBanDoKhanID;

    @Column(name = "VANBAN_DOMATID")
    private Integer vanBanDoMatID;

    @Column(name = "VANBAN_KIEUID")
    private Integer vanBanKieuID;

    @Column(name = "NGAYDEN")
    private Date ngayDen;

    @Column(name = "NOIGUIDEN")
    private String noiGuiDen;

    @Column(name = "NOIGUIDENID")
    private Integer noiGuiDenID;

    @Column(name = "NOIGUIKHAC")
    private String noiGuiKhac;

    @Column(name = "COQUANBANHANH")
    private String coQuanBanHanh;

    @Column(name = "LANHDAOKY")
    private String lanhDaoKy;

    @Column(name = "CHUCVU")
    private String chucVu;

    @Column(name = "CHUCDANHID")
    private Integer chucDanhID;

    @Column(name = "NGAYGUIDI")
    private Date ngayGuiDi;

    @Column(name = "NOIGUIDIKHAC")
    private String noiGuiDiKhac;

    @Column(name = "NOIGUIDIIDS")
    private String noiGuiDiIDs;

    @Column(name = "NGAYPHATHANH")
    private Date ngayPhatHanh;

    @Column(name = "SOTRANG")
    private Integer soTrang;

    @Column(name = "DANHAN")
    private String daNhan;

    @Column(name = "DONVIID")
    private Integer donViID;
  
    @Column(name = "NGUOINHAPID")
    private Integer nguoiNhapID;

    @Column(name = "TGNHAP")
    private Date tgNhap;

    @Column(name = "GHICHU")
    private String ghiChu;

    @Column(name = "VANBAN_TINHTRANGID")
    private Integer vanBanTinhTrangID;

    @Column(name = "CHIASEDICHDANHIDS")
    private String chiaSeDichDanhIDS;

    @Column(name = "CONGBO")
    private Boolean congBo;

    @Column(name = "TUKHOA")
    private String tuKhoa;

    @Column(name = "NGAYCHIASE")
    private Date ngayChiaSe;

    @Column(name = "NGAYKETTHUCCHIASE")
    private Date ngayKetThucChiaSe;

    @Column(name = "MAVANBAN")
    private String maVanBan;

    
}
