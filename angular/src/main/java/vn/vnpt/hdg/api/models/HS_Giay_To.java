package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hs_giay_to")
@Data
public class HS_Giay_To {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEN_GIAY_TO")
    private String tenGiayTo;
    
    @Column(name = "ID_HO_SO")
    private Integer idHoSo;
    
    @Column(name = "UID")
    private Integer uID;
}
