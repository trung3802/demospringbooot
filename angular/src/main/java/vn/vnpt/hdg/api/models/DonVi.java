package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "donvi")
@Data
public class DonVi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA")
    private String ma;

    @Column(name = "TEN")
    private String ten;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "DIENTHOAI")
    private String dienThoai;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "DONVI_LOAIID")
    private Integer donViLoaiID;
    
    @Column(name = "CAP")
    private Integer cap;
    
    @Column(name = "CHAID")
    private Integer chaID;
    
    @Column(name = "TRANGTHAI")
    private Integer trangThai;
    
    @Column(name = "THUTU")
    private Integer thuTu;
    
    @Column(name = "TENHIEU")
    private String tenHieu;
}
