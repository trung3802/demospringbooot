package vn.vnpt.hdg.api.models;

import lombok.Data;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "dm_kh_hinhthanh_hs")
@Data
public class DM_KH_HinhThanh_HS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEN_NHOM")
    private String tenNhom;

    @Column(name = "NGAY_TAO")
    private Date ngayTao;
    
    @Column(name = "NGAY_CAP_NHAT")
    private Date ngayCapNhat;
    
    @Column(name = "NGUOI_TAO")
    private Integer nguoiTao;
    
    @Column(name = "NGUOI_CAP_NHAT")
    private Integer nguoiCapNhat;
    
    @Column(name = "TRANG_THAI")
    private Integer trangThai;
    
    @Column(name = "KY_HIEU")
    private String kyHieu;
}