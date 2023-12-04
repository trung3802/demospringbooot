package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vanban_linhvuc")
@Data
public class VanBan_LinhVuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA")
    private String ma;

    @Column(name = "TEN")
    private String ten;

    @Column(name = "THUTU")
    private Integer thuTu;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;
}
