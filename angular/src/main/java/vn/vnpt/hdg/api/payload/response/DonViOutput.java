package vn.vnpt.hdg.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vnpt.hdg.api.models.DonVi;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonViOutput extends DonVi{
    private String tenLoaiDonVi;
}