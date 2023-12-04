package vn.vnpt.hdg.api.models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "thamso")
@Data
public class ThamSo {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
 	
 	@Column(name = "MA")	    
    private String ma;
 	
 	@Column(name = "TEN")
    private String ten;

    @Column(name = "GIATRI")
    private String giaTri;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;
    
    @Column(name = "MOTA")
    private String moTa;
}