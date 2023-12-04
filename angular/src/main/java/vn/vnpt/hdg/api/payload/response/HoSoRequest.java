package vn.vnpt.hdg.api.payload.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class HoSoRequest extends TT_Ho_So{
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date toDate;
}