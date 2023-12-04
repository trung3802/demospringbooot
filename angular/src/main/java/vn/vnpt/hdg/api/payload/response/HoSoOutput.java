package vn.vnpt.hdg.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpt.hdg.api.models.TT_Ho_So;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoSoOutput extends TT_Ho_So{  
    private String tenDoMat;
    private String tenKieuBaoQuan;
}