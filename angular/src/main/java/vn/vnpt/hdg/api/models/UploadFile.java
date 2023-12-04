package vn.vnpt.hdg.api.models;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "uploaded_files")
@Data
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "DUONG_DAN")
    private String duongDan;

    @Column(name = "PHAN_MO_RONG")
    private String phanMoRong;

    @Column(name = "NGUOI_TAO")
    private String nguoiTao;

    @Column(name = "NHAN_TG")
    private Date nhanTg;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "TEN_FILE_HASH")
    private String tenFileHash;

    @Lob 
    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "ID_VAN_BAN")
    private Integer idVanBan;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}