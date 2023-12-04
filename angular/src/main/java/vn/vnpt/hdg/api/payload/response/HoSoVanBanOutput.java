package vn.vnpt.hdg.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpt.hdg.api.models.Ho_So_VB;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoSoVanBanOutput extends Ho_So_VB{
    private String kyHieu;
    private String coQuanBanHanh;
    private String lanhDaoKy;
}