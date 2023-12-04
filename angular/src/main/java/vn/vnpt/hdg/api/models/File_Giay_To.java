package vn.vnpt.hdg.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file_giay_to")
@Data
public class File_Giay_To {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MA_HS_GIAY_TO")
    private String maHSGiayTo;

    @Column(name = "TEN_FILE")
    private String tenFile;

    @Column(name = "DUONG_DAN")
    private String duongDan;

    @Column(name = "FILE_PATH")
    private Integer filePath;
    
    @Column(name = "DINH_DANG")
    private String dinh_Dang;
    
    @Column(name = "NGAY_TAO")
    private Date ngayTao;
    
    @Column(name = "PHAN_MO_RONG")
    private String phanMoRong;
    
    @Column(name = "TEN_FILE_HASH")
    private String tenFileHash;
    
    @Column(name = "FILE_SIZE")
    private String fileSize;
    
    @Column(name = "UID")
    private String uID;
}
