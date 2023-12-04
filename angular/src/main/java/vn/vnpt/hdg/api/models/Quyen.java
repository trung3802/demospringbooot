package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "quyen")
@Data
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA")
    private String ma;
    
    @Column(name = "TEN")
    private String ten;
    
    @Column(name = "CHAID")
    private Integer chaID;
    
    @Column(name = "LOAI")
    private Integer loai;
    
    @Column(name = "CONTROLLER")
    private String controller;
    
    @Column(name = "ACTION")
    private String action;
    
    @Column(name = "FUNCTIONGROUP")
    private String functionGroup;
    
    @Column(name = "THUTU")
    private Integer thuTu;
    
    @Column(name = "TRANGTHAI")
    private Integer trangThai;
}
