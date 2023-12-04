package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vanban_ghichu")
@Data
public class VanBan_GhiChu {
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

 	@Column(name = "VANBANID")
    private Integer vanBanID;

    @Column(name = "NOIDUNG")
    private String noiDung;

    @Column(name = "NGUOIBUTPHE")
    private String nguoiButPhe;

    @Column(name = "CANHAN")
    private Boolean caNhan;

    @Column(name = "NGUOINHAPID")
    private Integer nguoiNhapID;

    @Column(name = "THOIGIANNHAP")
    private Date thoiGianNhap;
}