package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vaitro_nguoidung")
@Data
public class VaiTro_NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "VAITROID")
    private Integer vaiTroID;
    
    @Column(name = "NGUOIDUNGID")
    private Integer nguoiDungID;
}
