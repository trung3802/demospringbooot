package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "quyen_vaitro")
@Data
public class Quyen_VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "VAITROID")
    private Integer vaiTroID;
    
    @Column(name = "QUYENID")
    private Integer quyenID;
}
