package vn.vnpt.hdg.api.payload.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDTO {
	private String duongDan;
    private String phanMoRong;
    private String nguoiTao;
    private Date nhanTg;
    private String filePath;
    private String tenFileHash;  
   
    private byte[] data;
    private Long idVanBan;
}