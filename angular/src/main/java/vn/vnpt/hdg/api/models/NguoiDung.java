package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "nguoidung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA")
    private String ma;
    
    @Column(name = "MATKHAU")
    private String matKhau;
    
    @Column(name = "TEN")
    private String ten;
    
    @Column(name = "DIENTHOAI")
    private String dienThoai;
    
    @Column(name = "MAIL")
    private String mail;
    
    @Column(name = "DONVIID")
    private Integer donViID;
    
    @Column(name = "CHUCDANHID")
    private Integer chucDanhID;
    
    @Column(name = "THONGTIN")
    private String thongTin;
    
    @Column(name = "TRANGTHAI")
    private Integer trangThai;
}
