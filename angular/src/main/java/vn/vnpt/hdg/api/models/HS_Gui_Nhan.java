package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hs_gui_nhan")
@Data
public class HS_Gui_Nhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA_HO_SO")
    private String maHoSo;
    
    @Column(name = "ID_DON_VI_GUI")
    private Integer idDonViGui;
    
    @Column(name = "ID_DON_VI_NHAN")
    private Integer idDonViNhan;
    
    @Column(name = "TRANG_THAI")
    private Integer trangThai;
    
    @Column(name = "NGUOI_GUI")
    private String nguoiGui;
    
    @Column(name = "NGUOI_NHAN")
    private String nguoiNhan;
    
    @Column(name = "NGAY_GUI")
    private Date ngayGui;
    
    @Column(name = "NGAY_NHAN")
    private Date ngayNhan;
    
    @Column(name = "IDX")
    private Integer idX;
}
