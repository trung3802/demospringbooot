package vn.vnpt.hdg.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpt.hdg.api.models.NguoiDung;
import vn.vnpt.hdg.api.models.Quyen_VaiTro;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungChucDanhOutput extends NguoiDung{
    private String tenChucDanh;
}