package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dm_kieu_bao_quan")
@Data
public class DM_Kieu_Bao_Quan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEN_KIEU")
    private String tenKieu;

    @Column(name = "TRANG_THAI")
    private Integer trangThai;
}
