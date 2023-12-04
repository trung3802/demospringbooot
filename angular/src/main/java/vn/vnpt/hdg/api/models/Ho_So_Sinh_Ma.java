package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ho_so_sinh_ma")
@Data
public class Ho_So_Sinh_Ma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Ma_DD_Don_Vi")
    private String maDDDonVi;
    
    @Column(name = "NAM")
    private String nam;
    
    @Column(name = "SO_HIEN_TAI")
    private Integer soHienTai;

    @Column(name = "KY_HIEU_HS")
    private String kyHieuHS;
}
