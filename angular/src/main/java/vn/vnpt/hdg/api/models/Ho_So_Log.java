package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ho_so_log")
@Data
public class Ho_So_Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_HO_SO")
    private Integer idHoSo;

    @Column(name = "ID_NGUOI_DUNG")
    private Integer idNguoiDung;

    @Column(name = "NOI_DUNG")
    private String noiDung;

    @Column(name = "NGAY_TAO")
    private Date ngayTao;
}
