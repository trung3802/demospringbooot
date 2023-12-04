package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tt_ho_so")
@Data
public class TT_Ho_So {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA_HO_SO")
    private String maHoSo;
    
    @Column(name = "TIEU_DE")
    private String tieuDe;
    
    @Column(name = "THOI_HAN_BAO_QUAN")
    private String thoiHanBaoQuan;
    
    @Column(name = "CHE_DO_SU_DUNG")
    private String cheDoSuDung;

    @Column(name = "TG_BAT_DAU")
    private Date tgBatDau;
    
    @Column(name = "TG_KET_THUC")
    private Date tgKetThuc;
    
    @Column(name = "TONG_SO_VB")
    private Integer tongSoVB;
    
    @Column(name = "TONG_SO_TRANG")
    private Integer tongSoTrang;
    
    @Column(name = "GHI_CHU")
    private String ghiChu;
    
    @Column(name = "TRANG_THAI")
    private Integer trangThai;
    
    @Column(name = "ID_DON_VI")
    private Integer idDonVi;
    
    @Column(name = "ID_DO_MAT")
    private Integer idDoMat;
    
    @Column(name = "TU_KHOA")
    private String tuKhoa;
    
    @Column(name = "ID_KIEU_BAO_QUAN")
    private Integer idKieuBaoQuan;
    
    @Column(name = "ID_DON_VI_NHAN")
    private Integer idDonViNhan;
    
    @Column(name = "TRANG_THAI_NHAN")
    private Integer trangThaiNhan;
    
    @Column(name = "LOAI_LUU_TRU")
    private Integer loaiLuuTru;
    
    @Column(name = "KY_HIEU_HS")
    private String kyHieuHS;
    
    @Column(name = "NGAY_TAO")
    private Date ngayTao;
}
