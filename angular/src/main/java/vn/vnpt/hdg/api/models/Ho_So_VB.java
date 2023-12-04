package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ho_so_vb")
@Data
public class Ho_So_VB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_HO_SO")
    private Integer idHoSo;
    
    @Column(name = "ID_VAN_BAN")
    private Integer idVanBan;
    
    @Column(name = "IDX")
    private Integer idX;
}
